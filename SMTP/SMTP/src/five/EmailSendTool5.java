package five;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

import two.Email_Autherticator;

/*
 * 发送图片和附件
 */
public class EmailSendTool5 {
	// 邮箱服务器
	private String host;	
	private String mail_from;
	private String mail_to;
	private String mail_subject;
	private String mail_body;
	public EmailSendTool5(String host, String mail_from, String mail_to,
			String mail_subject, String mail_body) {
		this.host = host;
		this.mail_from = mail_from;
		this.mail_to = mail_to;
		this.mail_subject = mail_subject;
		this.mail_body = mail_body;
	}
	public EmailSendTool5() {
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getMail_from() {
		return mail_from;
	}
	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public String getMail_subject() {
		return mail_subject;
	}
	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}
	public String getMail_body() {
		return mail_body;
	}
	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}
	/** 
     * 此段代码用来发送图片和附件
     *  
     * @throws MessagingException 
     * @throws UnsupportedEncodingException 
     * @throws GeneralSecurityException  
     */  
	public void send() throws GeneralSecurityException, MessagingException {
		Properties props = new Properties();  
        //"xx@qq.com", "密码（此处密码为从QQ邮箱的【设置】->【账户】->【POP3/IMAP/SMTP...】中获取的授权码）"
        Authenticator auth = new Email_Autherticator("158759307@qq.com", "vqhvmadgfdbsbhjg"); // 进行邮件服务器用户认证  
        props.put("mail.smtp.host", host);  
        props.put("mail.smtp.auth", "true");  //true为需要身份验证 
        props.put("mail.transport.protocol", "smtp");  
        MailSSLSocketFactory sf = new MailSSLSocketFactory();//ssl(安全套接层)设置
        sf.setTrustAllHosts(true);  //信任所有的主机
        props.put("mail.smtp.ssl.enable", "true");  
        props.put("mail.smtp.ssl.socketFactory", sf);  
        Session session = Session.getInstance(props, auth);  
        // 设置session,和邮件服务器进行通讯。  
        MimeMessage message = new MimeMessage(session);          
        
        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是图片<img src='cid:1.jpg' width='300' height='300'>", "text/html;charset=UTF-8");// 设置邮件正文  
        
        //创建图片
        MimeBodyPart image = new MimeBodyPart();
        image.setDataHandler(new DataHandler(new FileDataSource("src\\1.jpg")));
        image.setContentID("1.jpg");
        
        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("src\\1.jpg"));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());
        
        //描述正文和图片的关系
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(image);
        
        //创建正文和数据的关系
        MimeMultipart mp2 = new MimeMultipart();
        mp2.addBodyPart(attach);
        
        //代表正文的bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mp1);
        
        mp2.addBodyPart(content);
        
        message.setContent(mp2);
        message.setSubject(mail_subject); // 设置邮件主题  
  
        message.setSentDate(new Date()); // 设置邮件发送日期  
        
        Address address = new InternetAddress(mail_from);  
        message.setFrom(address); // 设置邮件发送者的地址  
        Address toAddress = new InternetAddress(mail_to); // 设置邮件接收方的地址  
        message.addRecipient(Message.RecipientType.TO, toAddress);  
          
  
        Transport.send(message); // 发送邮件  
	}
	
	public static void main(String[] args) {
		EmailSendTool5 sendEmail = new EmailSendTool5("smtp.qq.com",  
                "158759307@qq.com", "1937138205@qq.com", "test", "邮件内容");  
        System.out.println("main");  
        try {  
            sendEmail.send();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}
