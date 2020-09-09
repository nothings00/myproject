package core2.net.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

/**
 * This program shows how to use JavaMail to send mail messages
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/8 4:04 PM
 * @version 1.0
 */
public class MailTest {
    public static void main(String[] args) throws IOException, MessagingException {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get(MailTest.class.getResource("/").getPath(),"mail","mail.properties"))){
            props.load(in);
        }
//        List<String> lines = Files.readAllLines(Paths.get(args[0]),Charset.forName("UTF-8"));




        String from = "3223007397@qq.com";
        String to = "625111833@qq.com";
        String subject = "测试邮件";

        StringBuilder builder = new StringBuilder();
        builder.append("测试内容\n");

        Scanner in = new Scanner(System.in);
        String password = in.nextLine();
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, new InternetAddress(to).getAddress());
        message.setSubject(subject);
        message.setText(builder.toString());
        Transport tr = mailSession.getTransport();
        try {
            tr.connect(null,password);
            tr.sendMessage(message,message.getAllRecipients());
        }finally {
            tr.close();
        }
    }
}
