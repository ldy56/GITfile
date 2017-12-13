package two;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/*
 * 发送普通文本
 */
public class EmailSendTool2 {
	// 邮箱服务器
	private String host;	
	private String mail_from;
	private String mail_to;
	private String mail_subject;
	private String mail_body;
	public EmailSendTool2(String host, String mail_from, String mail_to,
			String mail_subject, String mail_body) {
		this.host = host;
		this.mail_from = mail_from;
		this.mail_to = mail_to;
		this.mail_subject = mail_subject;
		this.mail_body = mail_body;
	}
	public EmailSendTool2() {
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
     * 此段代码用来发送普通电子邮件 
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
        
        message.setContent(mail_body , "text/html;charset=utf-8");  // 设置邮件正文  
        message.setSubject(mail_subject); // 设置邮件主题  
        
  
        message.setSentDate(new Date()); // 设置邮件发送日期  
        
        Address address = new InternetAddress(mail_from);  
        message.setFrom(address); // 设置邮件发送者的地址  
        Address toAddress1 = new InternetAddress(mail_to); // 设置邮件接收方的地址  
        Address toAddress2 = new InternetAddress("beijingzhaoying@163.com");
        //发送多个收件人
        message.addRecipients(Message.RecipientType.TO, new Address[]{toAddress1 , toAddress2});  
          
        //抄送
        Address toAddress = new InternetAddress("1544338394@qq.com");
        message.addRecipient(Message.RecipientType.CC, toAddress);
  
        Transport.send(message); // 发送邮件  
	}
	
	
	public static void main(String[] args) {
		EmailSendTool2 sendEmail = new EmailSendTool2("smtp.qq.com",  
                "158759307@qq.com", "1937138205@qq.com", "test", "邮件内容");  
        System.out.println("main");  
        try {  
            sendEmail.send();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}
