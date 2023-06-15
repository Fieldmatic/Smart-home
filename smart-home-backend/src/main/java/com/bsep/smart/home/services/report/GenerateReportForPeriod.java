package com.bsep.smart.home.services.report;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class GenerateReportForPeriod {
    private final GetLoggedInUser getLoggedInUser;

    public byte[] execute(LocalDate start, LocalDate end) throws IOException {
        Person user = getLoggedInUser.execute();
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

        String title = "Report for period " + start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " + end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
        Paragraph titleParagraph = new Paragraph(title);
        titleParagraph.setFontSize(16);
        titleParagraph.setBold();
        titleParagraph.setTextAlignment(TextAlignment.CENTER);
        titleParagraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(titleParagraph);

        String createdAt = "Created at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
        Paragraph createdAtParagraph = new Paragraph(createdAt);
        createdAtParagraph.setFontSize(8);
        createdAtParagraph.setMarginTop(15);
        createdAtParagraph.setTextAlignment(TextAlignment.LEFT);
        createdAtParagraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(createdAtParagraph);

        String createdBy = "Created by: " + user.getEmail();
        Paragraph createdByParagraph = new Paragraph(createdBy);
        createdByParagraph.setFontSize(8);
        createdByParagraph.setTextAlignment(TextAlignment.LEFT);
        createdByParagraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(createdByParagraph);

        document.close();
        return outputStream.toByteArray();
    }
}
