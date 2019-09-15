package com.bcrec.alumni;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bcrec.alumni.model.Alumni;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 23/10/14.
 */
public class WriteExcelFileExample {
    private static final String FILE_PATH = "/Users/anirudh/Projects/JCGExamples/JavaWriteReadExcelFileExample/testWriteStudents.xlsx";
    //We are making use of a single instance to prevent multiple write access to same file.
    private static final WriteExcelFileExample INSTANCE = new WriteExcelFileExample();

    public static WriteExcelFileExample getInstance() {
        return INSTANCE;
    }

    private WriteExcelFileExample() {
    }

    public static void main(String args[]){

        List<Alumni> studentList = new ArrayList<Alumni>();
        studentList.add(new Alumni("Magneto","90","100","80"));
        studentList.add(new Alumni("Wolverine","60","60","90"));
        studentList.add(new Alumni("ProfX","100","100","100"));

        writeStudentsListToExcel(studentList);

    }

    public static void writeStudentsListToExcel(List<Alumni> studentList){

        // Using XSSF for xlsx format, for xls use HSSF
        Workbook workbook = new XSSFWorkbook();

        Sheet studentsSheet = workbook.createSheet("Students");

        int rowIndex = 0;
        for(Alumni student : studentList){
            Row row = studentsSheet.createRow(rowIndex++);
            int cellIndex = 0;
            //first place in row is name
            row.createCell(cellIndex++).setCellValue(student.getName());

            //second place in row is marks in maths
            row.createCell(cellIndex++).setCellValue(student.getMaths());

            //third place in row is marks in Science
            row.createCell(cellIndex++).setCellValue(student.getScience());

            //fourth place in row is marks in English
            row.createCell(cellIndex++).setCellValue(student.getEnglish());

        }

        //write this workbook in excel file.
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            workbook.write(fos);
            fos.close();

            System.out.println(FILE_PATH + " is successfully written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
