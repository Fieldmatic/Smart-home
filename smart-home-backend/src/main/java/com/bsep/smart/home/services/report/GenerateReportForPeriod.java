package com.bsep.smart.home.services.report;

import com.bsep.smart.home.model.AlarmType;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.facts.Alarm;
import com.bsep.smart.home.repository.AlarmRepository;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GenerateReportForPeriod {
    private final GetLoggedInUser getLoggedInUser;
    private final AlarmRepository alarmRepository;

    public byte[] execute(UUID propertyId, LocalDate start, LocalDate end) throws IOException {
        Person user = getLoggedInUser.execute();
        List<Alarm> alarms = alarmRepository.findAllByDevicePropertyIdAndCreatedAtBetween(propertyId, LocalDateTime.of(start, LocalTime.of(0, 0)), LocalDateTime.of(end, LocalTime.of(23, 59)));
        alarms.sort(Comparator.comparing(Alarm::getTime));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);

        Document document = new Document(pdfDoc);

        ClassPathResource fontResource = new ClassPathResource("font/Montserrat.ttf");
        FontProgram fontProgram = FontProgramFactory.createFont(fontResource.getPath());
        PdfFont pdfFont = PdfFontFactory.createFont(fontProgram);
        document.setFont(pdfFont);

        ClassPathResource logoResource = new ClassPathResource("logo/logo.png");
        Image logoImage = new Image(ImageDataFactory.create(logoResource.getURL()));
        logoImage.setWidth(100);
        logoImage.setHeight(25);
        logoImage.setHorizontalAlignment(HorizontalAlignment.LEFT);
        document.add(logoImage);

        document.add(createTitle("Main Events Report"));
        document.add(createSubtitle("Property ID: " + propertyId, true));
        document.add(createSubtitle("Period: " + start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " + end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")), false));

        document.add(createContentParagraph("This report contains summary of main events that occurred in Smart Home system for period " + start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " + end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")) + " on the property registered by ID: " + propertyId + ".", true));
        document.add(createContentParagraph("Created at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")), false));
        document.add(createContentParagraph("Created by: " + user.getEmail(), false));
        if (alarms.size() > 0) {
            Paragraph p = createContentParagraph("Number of triggered alarms: " + alarms.size(), false);
            p.setMarginBottom(10);
            document.add(p);
            document.add(createMainTable(alarms));
            document.add(createTableDescription("Table of all alarms triggered by all devices"));
        } else {
            Paragraph p = createContentParagraph("There are no triggered alarms in the selected period.", true);
            p.setFontSize(10);
            p.setTextAlignment(TextAlignment.CENTER);
            p.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(p);
        }

        float[] columnWidths = {300F, 300F};
        Table table = new Table(columnWidths);

        Map<Map<String, AlarmType>, List<Alarm>> categorizedAlarms = categorizeAlarms(alarms);
        for (Map<String, AlarmType> category : categorizedAlarms.keySet()) {
            table.addCell(createBorderlessCell().add(createCategoryTable(categorizedAlarms.get(category), category)));
        }
        document.add(table);

        document.close();
        return outputStream.toByteArray();
    }

    private Paragraph createTitle(String text) {
        Paragraph title = new Paragraph(text);
        title.setFontSize(17);
        title.setBold();
        title.setTextAlignment(TextAlignment.CENTER);
        title.setHorizontalAlignment(HorizontalAlignment.CENTER);
        return title;
    }

    private Paragraph createSubtitle(String text, boolean addTopMargin) {
        Paragraph subtitle = new Paragraph(text);
        subtitle.setFontSize(10);
        subtitle.setBold();
        if (addTopMargin) {
            subtitle.setMarginTop(5);
        }
        subtitle.setFontColor(ColorConstants.DARK_GRAY);
        subtitle.setTextAlignment(TextAlignment.CENTER);
        subtitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        return subtitle;
    }

    private Paragraph createContentParagraph(String text, boolean addTopMargin) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFontSize(9);
        paragraph.setTextAlignment(TextAlignment.JUSTIFIED);
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        if (addTopMargin) {
            paragraph.setMarginTop(10);
        }
        return paragraph;
    }

    private Table createMainTable(List<Alarm> alarms) {
        float[] columnWidths = {300F, 300F, 300F, 300F};
        Table table = new Table(columnWidths);
        table.setBorder(Border.NO_BORDER);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setBorderBottom(new SolidBorder(ColorConstants.LIGHT_GRAY, 1f));

        table.addHeaderCell(createHeaderCell("Alarm type"));
        table.addHeaderCell(createHeaderCell("Device"));
        table.addHeaderCell(createHeaderCell("Value"));
        table.addHeaderCell(createHeaderCell("Time"));

        for (Alarm alarm : alarms) {
            table.addCell(createCell(reformatAlarmType(alarm.getAlarmType())));
            table.addCell(createCell(alarm.getDevice().getName()));
            table.addCell(createCell(String.valueOf(alarm.getValue())));
            table.addCell(createCell(alarm.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. hh:mm"))));
        }
        return table;
    }

    private Table createCategoryTable(List<Alarm> alarms, Map<String, AlarmType> category) {
        float[] columnWidths = {300F, 300F};
        Table table = new Table(columnWidths);
        table.setBorder(Border.NO_BORDER);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setBorderBottom(new SolidBorder(ColorConstants.LIGHT_GRAY, 1f));

        table.addHeaderCell(createHeaderCell("Value"));
        table.addHeaderCell(createHeaderCell("Time"));

        for (Alarm alarm : alarms) {
            table.addCell(createCell(String.valueOf(alarm.getValue())));
            table.addCell(createCell(alarm.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. hh:mm"))));
        }

        Table containerTable = new Table(1);
        containerTable.addCell(createBorderlessCell().add(table));
        final String deviceName = (String) category.keySet().toArray()[0];
        final AlarmType alarmType = category.get(deviceName);
        containerTable.addCell(createBorderlessCell().add(createTableDescription("Table of values occurred in " + deviceName + " categorized as " + reformatAlarmType(alarmType))));
        return containerTable;
    }

    private Cell createHeaderCell(String text) {
        Cell cell = createBorderlessCell();
        cell.add(new Paragraph(text));
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.setFontSize(10);
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    private Cell createCell(String text) {
        Cell cell = createBorderlessCell();
        cell.add(new Paragraph(text));
        cell.setFontSize(9);
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    private Paragraph createTableDescription(String text) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFontSize(7);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        paragraph.setMarginBottom(10);
        return paragraph;
    }

    private Map<Map<String, AlarmType>, List<Alarm>> categorizeAlarms(List<Alarm> alarms) {
        Map<Map<String, AlarmType>, List<Alarm>> categorizedAlarms = new HashMap<>();

        for (Alarm alarm : alarms) {
            Map<String, AlarmType> category = new HashMap<>();
            category.put(alarm.getDevice().getName(), alarm.getAlarmType());

            List<Alarm> categoryAlarms = categorizedAlarms.getOrDefault(category, new ArrayList<>());
            categoryAlarms.add(alarm);
            categorizedAlarms.put(category, categoryAlarms);
        }

        return categorizedAlarms;
    }

    private String reformatAlarmType(AlarmType alarmType) {
        return alarmType.name().replace("_", " ").toLowerCase();
    }

    private Cell createBorderlessCell() {
        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }
}
