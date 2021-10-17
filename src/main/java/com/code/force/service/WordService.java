package com.code.force.service;

import com.code.force.domain.Users;
import com.code.force.domain.UsersTime;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class WordService {


    public String downloadFolder = "D:\\CODING\\Spring Boot\\force\\src\\main\\webapp";


    public String convertTextFileToString(String fileName) {
        try (Stream<String> stream
                     = Files.lines(Paths.get("D:\\CODING\\Spring Boot\\force\\src\\main\\java\\com\\code\\force\\service\\" + fileName))) {

            return stream.collect(Collectors.joining(" "));
        } catch (IOException e) {

            return null;
        }
    }

    public ByteArrayInputStream setMicrosoftWordDocument(Users users, List<UsersTime> userTime, Integer present, Integer missed, Integer total) {
        try (XWPFDocument document = new XWPFDocument(); ByteArrayOutputStream bout = new ByteArrayOutputStream()) {

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String todayDate = dateFormat.format(Calendar.getInstance().getTime());

            XWPFParagraph paragraph;
            XWPFRun run;

            paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.RIGHT);

            run = paragraph.createRun();
            run.setText(todayDate);
            run.setFontSize(10);
            run.setUnderline(UnderlinePatterns.SINGLE);
            run.addBreak();


            paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            run = paragraph.createRun();
            run.setText("User Information");
            run.setFontSize(25);
            run.setBold(true);


            paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.RIGHT);

            run = paragraph.createRun();
            run.setTextPosition(20);
            File imageFile = new File(downloadFolder + users.getImagePath());
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            while (width > 500 | height > 500) {
                width = width / 2;
                height = height / 2;
            }
            Path imagePath = Paths.get(downloadFolder + users.getImagePath());
            run.addPicture(Files.newInputStream(imagePath),
                    XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),
                    Units.toEMU(width / 1), Units.toEMU(height / 1));


            paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);

            run = paragraph.createRun();
            run.setFontSize(18);
            run.setText("First name: " + users.getFirstName());
            run.addBreak();
            run.setText("Last name: " + users.getLastName());
            run.addBreak();
            run.setText("Username: " + users.getUserName());
            run.addBreak();
            run.setText("Password: " + users.getPassword());
            run.addBreak();
            run.setText("Role: " + users.getRole());
            run.addBreak();
            run.addBreak();
            run.setText("Present Days:  " + present);
            run.addBreak();
            run.setText("Missed Days:  " + missed);
            run.addBreak();
            run.setText("Total Hours:  " + total);


            XWPFTable table = document.createTable();
            table.setCellMargins(10, 10, 10, 10);
            table.setColBandSize(3000);
            table.setTableAlignment(TableRowAlign.CENTER);
            table.setWidth(3000);
            XWPFTableRow tableRow = table.getRow(0);


            tableRow.getCell(0).setText("  #  ");
            tableRow.addNewTableCell().setText("Date");
            tableRow.addNewTableCell().setText("Coming time");
            tableRow.addNewTableCell().setText("Leaving time");

            int counter = 1;
            for (UsersTime ut : userTime) {
                tableRow = table.createRow();
                tableRow.getCell(0).setText("  " + (counter++) + "  ");
                tableRow.getCell(1).setText(ut.getDate());
                tableRow.getCell(2).setText(ut.getArrivalTime());
                tableRow.getCell(3).setText(ut.getLeavingTime());
            }

            document.write(bout);
            return new ByteArrayInputStream(bout.toByteArray());
        } catch (IOException | InvalidFormatException e) {
            System.out.println("Error in Word Creating");
        }
        return null;
    }


    public ByteArrayInputStream setPdfDocument(Users users, List<UsersTime> userTime, Integer present, Integer missed, Integer total) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            try (PDPageContentStream cont = new PDPageContentStream(document, page)) {

                cont.beginText();

                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 700);
                String line1 = "First name: " + users.getFirstName();
                cont.showText(line1);

                cont.newLine();

                String line2 = "Last name: " + users.getLastName();
                cont.showText(line2);
                cont.newLine();

                String line3 = "Username: " + users.getLastName();
                cont.showText(line3);
                cont.newLine();

                String line4 = "Password : " + users.getPassword();
                cont.showText(line4);
                cont.newLine();
                cont.endText();
            }


            document.save("D:\\CODING\\Spring Boot\\force\\src\\main\\webapp\\resource\\files\\blank.pdf");
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            Path path=Paths.get("D:\\CODING\\Spring Boot\\force\\src\\main\\webapp\\resource\\files\\blank.pdf");

            ByteArrayInputStream in=new ByteArrayInputStream(Files.readAllBytes(path));
            return in;
        } catch (IOException e) {

        }
        return null;
    }
}



