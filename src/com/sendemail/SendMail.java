/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sendemail;

/**
 *
 * @author kwany
 */
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public void sendMail(String receiver, String subject, String mailMessage, final String sender, final String pass) {

        // Email using gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object and set username and password
        try {
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(sender, pass);

                }

            });
//            session.setDebug(true);
                try {
                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);

                // Set sender: header field of the header.
                message.setFrom(new InternetAddress(sender));

                // Set receiver: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

                // Set Subject: header field
                message.setSubject(subject);

                // Set mail content
                message.setText(mailMessage);

                // Send message
                Transport.send(message);
                System.out.println("Mail has been sent.");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            } 
        } catch (Exception ex) {
            System.out.println("You have connection problem, please try again");
            //Show "You have connection problem, please try again"
        } 

        // Used to debug SMTP issues

        

    }
}
