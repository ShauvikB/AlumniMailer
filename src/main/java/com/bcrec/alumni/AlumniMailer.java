package com.bcrec.alumni;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.bcrec.alumni.model.Alumni;
import com.bcrec.alumni.service.CreateQRCodeForAlumni;
import com.bcrec.alumni.service.ReadAlumniDetailsFromExcel;
import com.bcrec.alumni.service.SendConfirmationMail;
import com.bcrec.alumni.service.UpdateAlumniDetailsInExcel;
import com.bcrec.alumni.serviceImpl.CreateQRCodeForAlumniImpl;
import com.bcrec.alumni.serviceImpl.ReadAlumniDetailsFromExcelImpl;
import com.bcrec.alumni.serviceImpl.SendConfirmationMailImpl;
import com.bcrec.alumni.serviceImpl.UpdateAlumniDetailsInExcelImpl;
import com.bcrec.utilties.PropertyLoader;

public class AlumniMailer {

	public static void main(String[] args) throws Exception {
		PropertyLoader propertyLoader = new PropertyLoader();
		Properties prop = propertyLoader.loadProperties();
		
		ReadAlumniDetailsFromExcel readAlumniDetailsFromExcel = new ReadAlumniDetailsFromExcelImpl();
		List<Alumni> alumniList = readAlumniDetailsFromExcel.getAlumniListFromExcel(prop.getProperty("file_path"));
        
		CreateQRCodeForAlumni createQRCodeForAlumni = new CreateQRCodeForAlumniImpl();
		createQRCodeForAlumni.createVCard(alumniList);
		
		SendConfirmationMail sendConfirmationMail = new SendConfirmationMailImpl(prop);
		UpdateAlumniDetailsInExcel updateAlumniDetailsInExcel = new UpdateAlumniDetailsInExcelImpl();
		
		for (int count = 0; count < alumniList.size(); count ++) {
			if (!org.apache.commons.lang3.StringUtils.isEmpty(alumniList.get(count).getEmail()) && !StringUtils.equalsIgnoreCase(alumniList.get(count).getMailSent(), "Y")) {
				sendConfirmationMail.sendEmail(alumniList.get(count).getEmail(), alumniList.get(count).getName());
				updateAlumniDetailsInExcel.updateAlumni(count + 1, 7, (prop.getProperty("file_path")));
			}
		}
	}
}
