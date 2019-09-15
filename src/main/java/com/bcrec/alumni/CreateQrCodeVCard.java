package com.bcrec.alumni;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;
import net.glxn.qrgen.javase.QRCode;
import java.io.*;

public class CreateQrCodeVCard {

    public static void main(String... args){
        VCard vCard = new VCard();
        vCard.setName("albaalchal.com");
        vCard.setAddress("street 1, xxxx address");
        vCard.setCompany("Ghoti Hara Inc.");
        vCard.setPhoneNumber("+xx/xx.xx.xx");
        vCard.setTitle("title");
        vCard.setEmail("info@alumnimarachi.com");
        vCard.setWebsite("https://durBaal.com");


        ByteArrayOutputStream bout =
                QRCode.from(vCard)
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();

        try {
            OutputStream out = new FileOutputStream("E:/BCREC Alumni/qr-code-vcard.png");
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