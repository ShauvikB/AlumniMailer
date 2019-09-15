package com.bcrec.alumni.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.bcrec.alumni.model.Alumni;
import com.bcrec.alumni.service.CreateQRCodeForAlumni;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;
import net.glxn.qrgen.javase.QRCode;

public class CreateQRCodeForAlumniImpl implements CreateQRCodeForAlumni{
	
	public void createVCard(List<Alumni> alumniList) {
		 VCard vCard = null;
		 String vCardName = null;
		for (Alumni alumni : alumniList) {
			if (!org.apache.commons.lang3.StringUtils.isEmpty(alumni.getEmail())) {
				vCard = new VCard();
		        vCard.setName(alumni.getName());
		        vCard.setCompany("Ghoti Hara Inc.");
		        vCard.setPhoneNumber("+xx/xx.xx.xx");
		        vCard.setTitle("Confirmation Receipt for Resgistration");
		        vCard.setEmail(alumni.getEmail());
		        vCard.setWebsite("https://durBaal.com");
		        
		        ByteArrayOutputStream bout =
		                QRCode.from(vCard)
		                        .withSize(250, 250)
		                        .to(ImageType.PNG)
		                        .stream();
		        
		       vCardName = "qr-code-vcard-" + alumni.getName() + ".png";
	
		        try {
		            OutputStream out = new FileOutputStream("E:/BCREC Alumni/" + vCardName);
		            System.out.println("AFter reading file");
		            bout.writeTo(out);
		            out.flush();
		            out.close();
	
		        } catch (FileNotFoundException e){
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
		}
		
	}

}
