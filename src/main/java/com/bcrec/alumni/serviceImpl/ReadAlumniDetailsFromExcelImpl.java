package com.bcrec.alumni.serviceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bcrec.alumni.model.Alumni;
import com.bcrec.alumni.service.ReadAlumniDetailsFromExcel;

public class ReadAlumniDetailsFromExcelImpl implements ReadAlumniDetailsFromExcel{
	
	public List<Alumni> getAlumniListFromExcel(String FILE_PATH) {
        List<Alumni> alumniList = new ArrayList<Alumni>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FILE_PATH);

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                    Alumni student = new Alumni();
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        //The Cell Containing String will is name.
                        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                        	 //Cell with index 0 contains Name
                        	 if (cell.getColumnIndex() == 0) {
                        		 student.setName(cell.getStringCellValue());
                        	 }
                        	//Cell with index 2 contains Stream
                        	 if (cell.getColumnIndex() == 2) {
                        		 student.setStream(cell.getStringCellValue());
                        	 }
                        	//Cell with index 5 contains email
                        	 if (cell.getColumnIndex() == 5) {
                        		 student.setEmail(cell.getStringCellValue());                 
                        	 }
                        	//Cell with index 7 contains email
                        	 if (cell.getColumnIndex() == 7) {
                        		 student.setMailSent(cell.getStringCellValue());                 
                        	 }

                            //The Cell Containing numeric value will contain marks
                        } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {

                            //Cell with index 1 contains Year of Grad
                            if (cell.getColumnIndex() == 1) {
                            	student.setYearOfGraduation((int)cell.getNumericCellValue());
                            }
                            //Cell with index 3 contains Amount Contributed
                            else if (cell.getColumnIndex() == 3) {
                                student.setAmount((int)cell.getNumericCellValue());
                            }
                        }
                    }
                    //end iterating a row, add all the elements of a row in list
                    alumniList.add(student);
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Remove the first entry which is header of the excel sheet
        alumniList.remove(0);
        System.out.println(alumniList);
        return alumniList;
    }

}
