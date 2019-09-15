package com.bcrec.alumni.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bcrec.alumni.service.UpdateAlumniDetailsInExcel;

public class UpdateAlumniDetailsInExcelImpl implements UpdateAlumniDetailsInExcel {
	
	public void updateAlumni(int rowCount, int col, String FILE_PATH) throws IOException {
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = null;
            Row row = null;

            row = sheet.getRow(rowCount);
            row.createCell(col).setCellValue("Y");
            file.close();

            FileOutputStream outFile =new FileOutputStream(new File(FILE_PATH));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
