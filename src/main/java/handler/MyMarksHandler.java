package handler;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfChunk;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controller.Controller;
import data.Gesuch;
import data.Mark;
import data.Request;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import view.Color;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MyMarksHandler implements Initializable {

    @FXML
    private GridPane pane_school;

    @FXML
    private JFXButton btn_printPDF;

    @FXML
    private TableView<Mark> table_marks;

    @FXML
    private TableColumn<Mark, String> col_date;

    @FXML
    private TableColumn<Mark, String> col_theme;

    @FXML
    private TableColumn<Mark, String> col_subject;

    @FXML
    private TableColumn<Mark, String> col_semester;

    @FXML
    private TableColumn<Mark, String> col_mark;

    @FXML
    private TableColumn<Mark, String> col_avgClass;

    @FXML
    private JFXListView<?> lv_nextTests;


    private Controller controller;

    public MyMarksHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        table_marks.setItems(controller.getMarks());
    }

    /**
     * cellfactory of tableview.
     * create a custom cell for download column
     */
    private void initCol() {

        col_date.setCellValueFactory(new PropertyValueFactory<Mark, String>("date"));
        col_theme.setCellValueFactory(new PropertyValueFactory<Mark, String>("theme"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Mark, String>("subject"));
        col_semester.setCellValueFactory(new PropertyValueFactory<Mark, String>("semester"));
        col_mark.setCellValueFactory(new PropertyValueFactory<Mark, String>("mark"));
        col_avgClass.setCellValueFactory(new PropertyValueFactory<Mark, String>("avgClass"));


    }

    @FXML
    private void printPDF() {

        Document doc = new Document();
        //document header attributes
        doc.addAuthor("Schule");
        doc.addCreationDate();
        doc.addProducer();
        doc.addCreator("Schule");
        doc.addTitle("Notenblatt");
        doc.setPageSize(PageSize.LETTER);

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("notenblatt.pdf"));
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



            for (Mark mark : table_marks.getItems()) {
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
}
