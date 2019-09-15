package com.bcrec.alumni;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bcrec.alumni.model.Alumni;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Shauvik on 19/08/19.
 */
public class ReadExcelFileExample {

    private static final String FILE_PATH = "E:/BCREC Alumni/Alumni_Contribution.xlsx";

    public static void main(String args[]) {

        List<Alumni> alumniList = getAlumniListFromExcel();
     //   List<String> emailList = getAlumniListFromExcel();
       // emailList.remove(0);
        
		/*
		 * List<String> emailList1 = new ArrayList<String>();
		 * emailList1.add("shauvik.bakshi@gmail.com");
		 * emailList1.add("shauvik_79@rediffmail.com");
		 */
        
        
        List<String> emailList = new ArrayList<String>();
        for (Alumni alumni : alumniList) {
			if (!StringUtils.equalsIgnoreCase(alumni.getEmail(), "Email Id") && !StringUtils.isEmpty(alumni.getEmail())) {
				emailList.add(alumni.getEmail());
			}
		}
        
        encrypDecryptPas("samson03");
        
     //   sendEmail(emailList);
        
        System.out.println(emailList);
    }

    private static List<Alumni> getAlumniListFromExcel() {
        List<Alumni> studentList = new ArrayList<Alumni>();
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
                    studentList.add(student);
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    private static void sendEmail(List<String> emailList) {
    	final String username = "shauvik.bakshi@gmail.com";
        final String password = "samson0312";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
        	
        	StringBuilder builder = new StringBuilder();
        	for (String value : emailList) {
        	    builder.append(value).append(",");
        	}
        	
        	String strList = builder.toString();
        	
        	 if( strList.length() > 0 )
                 strList = strList.substring(0, strList.length() - 1);
        	
        	 System.out.println(builder.toString());
        	
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("shauvik.bakshi@gmail.com"));
         
           
           
           
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(strList)
            );
            
           
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Alumni Gandus,"
                    + "\n\n taka collect kore onek chirecho eibar maal khao!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    private static void encrypDecryptPas(String password)  {
    	try{

		    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		    SecretKey myDesKey = keygenerator.generateKey();
		    
		    Cipher desCipher;

		    // Create the cipher 
		    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		    
		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

		    //sensitive information
		    byte[] text = password.getBytes();

		    System.out.println("Text [Byte Format] : " + text);
		    System.out.println("Text : " + new String(text));
		   
		    // Encrypt the text
		    byte[] textEncrypted = desCipher.doFinal(text);

		    System.out.println("Text Encryted : " + textEncrypted);
		    
		    // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

		    // Decrypt the text
		    byte[] textDecrypted = desCipher.doFinal(textEncrypted);
		    
		    System.out.println("Text Decryted : " + new String(textDecrypted));
		    
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
		}catch(InvalidKeyException e){
			e.printStackTrace();
		}catch(IllegalBlockSizeException e){
			e.printStackTrace();
		}catch(BadPaddingException e){
			e.printStackTrace();
		} 
	   
    }
}
