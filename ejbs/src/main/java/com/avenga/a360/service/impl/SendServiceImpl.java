package com.avenga.a360.service.impl;

import com.avenga.a360.model.Email;
import com.avenga.a360.service.SendService;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Stateless
public class SendServiceImpl implements SendService {

    @Resource(mappedName = "java:/mail")
    Session session;

    public Session userSession = null;
    public static boolean userSessionInUse = false;
    public static String from = "";

    @Override
    public boolean resetSmtp() {
        userSessionInUse = false;
        userSession = null;
        from = "";
        return true;
    }

    @Override
    public boolean changeSmtpProperties(String smtp, Integer port, String senderEmail, String senderPassword) {

        userSession = createSession(smtp, port, senderEmail, senderPassword);
        userSessionInUse = true;
        from = senderEmail;

        return true;
    }

    private Session createSession(String smtp, Integer port, String senderEmail, String senderPassword) {
        Properties props = new Properties();
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.host", smtp); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", port);//Outgoing port

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }


    @Override
    public boolean sendEmail(Email email) {


        try {
            if (userSessionInUse) {
                MimeMessage message = new MimeMessage(userSession);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getSendTo()));
                message.setSubject(email.getSubject());
                message.setText(email.getBody());
                Transport.send(message);
            } else {
                MimeMessage message = new MimeMessage(session);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getSendTo()));
                message.setSubject(email.getSubject());
                message.setText(email.getBody());
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Asynchronous
    public void sendEmailsToAllParticipants(List<Email> emailList) {
        for (Email email : emailList) {
            sendEmail(email);
        }
    }

    @Override
    public boolean checkSmtpServer() {
        if (!userSessionInUse) {
            try {
                Transport transport = session.getTransport("smtp");
                transport.connect();
                transport.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        } else {
            try {
                Transport transport = userSession.getTransport("smtp");
                transport.connect();
                transport.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }


}