package helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import data.Mark;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * PDF creator
 * 
 * @author Sandro Guerotto
 * @since 02.01.2017
 * @version 0.1
 */
public class MyMarksPDF {

    private ObservableList<Mark> list;
    private String path = "notenblatt.pdf";

    public MyMarksPDF(ObservableList<Mark> list) {
        this.list = list;
    }

    /**
     * create PDF document
     * sets document informations
     */
    public void create(){
        Document doc = new Document();
        //document header attributes
        doc.addAuthor("Schule");
        doc.addCreationDate();
        doc.addProducer();
        doc.addCreator("Schule");
        doc.addTitle("Notenblatt");
        doc.setPageSize(PageSize.LETTER);

        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            PdfWriter.getInstance(doc, outputStream);
            //special font sizes


            doc.open();
            //create a paragraph
            Paragraph paragraph = new Paragraph("iText ® is a library that allows you to create and " +
                    "manipulate PDF documents. It enables developers looking to enhance web and other " +
                    "applications with dynamic PDF document generation and/or manipulation. \n \n");
            doc.add(paragraph);


            //define Table
            PdfPTable table = new PdfPTable(5);
            //create a cell object
            PdfPCell table_cell = new PdfPCell();
            //insert column headings
            setheader(table);



            for (Mark mark : list) {
                table_cell = new PdfPCell(new Phrase(mark.getDate()));
                table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase(mark.getTheme()));
                table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase(mark.getSubject()));
                table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase(mark.getSemester()));
                table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase(mark.getMark()));
                table.addCell(table_cell);
            }

                    /* Attach report table to PDF */
            doc.add(table);
            doc.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * set header line of mark table
     * @param table mark table in the document
     */
    private void setheader(PdfPTable table){
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        PdfPCell table_cell = new PdfPCell(new Phrase("Datum", bfBold12));
        table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Thema", bfBold12));
        table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Fach", bfBold12));
        table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Semester", bfBold12));
        table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Note", bfBold12));
        table.addCell(table_cell);
        table.setHeaderRows(1);
    }

    public String getPath() {
        return path;
    }
}
