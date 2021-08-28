package TcWeb.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class Email {

    public Email(String userMailbox, String verificationCode) throws Exception {

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("zhanwang824876688@163.com", "BWGTNGGJRQOXKECQ");
            }
        });
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect("smtp.163.com", "zhanwang824876688@163.com", "BWGTNGGJRQOXKECQ");
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("zhanwang824876688@163.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMailbox));
        message.setSubject("你的验证码是");
        message.setContent(verificationCode, "text/html;charset=UTF-8");
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

}

