package com.bsep.smart.home.services.report;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.facts.Alarm;
import com.bsep.smart.home.repository.AlarmRepository;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
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
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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

        String title = "Report for property with id: " + propertyId + " in period " + start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " + end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
        Paragraph titleParagraph = new Paragraph(title);
        titleParagraph.setFontSize(16);
        titleParagraph.setBold();
        titleParagraph.setTextAlignment(TextAlignment.CENTER);
        titleParagraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(titleParagraph);

        document.add(createContentParagraph("Created at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"))));
        document.add(createContentParagraph("Created by: " + user.getEmail()));
        document.add(createContentParagraph("Number of triggered alarms: " + alarms.size()));

        float[] columnWidths = {300F, 300F, 300F, 300f};
        Table table = new Table(columnWidths);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addHeaderCell(createHeaderCell("Alarm type"));
        table.addHeaderCell(createHeaderCell("Device"));
        table.addHeaderCell(createHeaderCell("Value"));
        table.addHeaderCell(createHeaderCell("Time"));

        for (Alarm alarm : alarms) {
            table.addCell(createCell(alarm.getAlarmType().name().replace("_", " ").toLowerCase()));
            table.addCell(createCell(alarm.getDevice().getName()));
            table.addCell(createCell(String.valueOf(alarm.getValue())));
            table.addCell(createCell(alarm.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. hh:mm"))));
        }

        document.add(table);

        document.close();
        return outputStream.toByteArray();
    }

    private Paragraph createContentParagraph(String text) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFontSize(9);
        paragraph.setTextAlignment(TextAlignment.LEFT);
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        return paragraph;
    }

    private Cell createHeaderCell(String text) {
        Cell cell = new Cell();
        cell.add(new Paragraph(text));
        cell.setFontSize(10);
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    private Cell createCell(String text) {
        Cell cell = new Cell();
        cell.add(new Paragraph(text));
        cell.setFontSize(9);
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }
}
