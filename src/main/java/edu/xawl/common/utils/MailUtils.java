package edu.xawl.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import com.sun.mail.smtp.SMTPSSLTransport;


//本类配合配置文件使用，配置文件在文件夹后面给出email_template.properties
public class MailUtils {
	
	/**
	 * 邮件服务主机
	 */
	private static String host = null;
	
	/**
	 * 管理员邮箱名
	 */
	private static String from = null;
	
	/**
	 * 管理员邮箱密码
	 */
	private static String emailpsd = null;
	
	/*加载配置文件*/
	static{
		//配置文件对象
		final Properties props = new Properties();
		
		String path = MailUtils.class.getClassLoader().getResource("email_template.properties").getPath();
		try {
			props.load(new FileInputStream(path));
			host = props.getProperty("host");
			from = props.getProperty("adminName");
			emailpsd = props.getProperty("adminPassword");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用配置文件中配置的管理员进行发邮件
	 * @param subject  邮件的主题
	 * @param content  邮件的内容
	 * @param toMail   邮件接受者
	 * @return  返回布尔值，表示是否发送成功
	 */
	public static boolean sendEmailFromAdmin(String subject,String content,String toMail){
		
		try {
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, emailpsd);
				}
			};
			
			Session session = Session.getInstance(new Properties(), authenticator);
			MimeMessage message = new MimeMessage(session);
			InternetAddress fromI = new InternetAddress(from.toString());
			message.setFrom(fromI);
			
			InternetAddress to  = new InternetAddress(toMail);
			message.setRecipient(RecipientType.TO, to);
			
			message.setSubject(subject);
			
			message.setContent(content, "text/html;charset=UTF-8");  
			
			Transport transport = new SMTPSSLTransport(session, null);  
		      //这个步很重要，不能省略  
			transport.connect(host,from, emailpsd);   
			transport.sendMessage(message, message.getAllRecipients());  
			transport.close();  
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * 用传入的用户给另一个用户发邮件
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param fromMailName  发件人地址
	 * @param fromMailPass  发件人密码
	 * @param toMail  收件人地址
	 * @return
	 */
	public static boolean sendEmail(String subject,String content,final String fromMailName,final String fromMailPass,String toMail){
		try {
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromMailName, fromMailPass);
				}
			};
			
			Session session = Session.getInstance(new Properties(), authenticator);
			MimeMessage message = new MimeMessage(session);
			InternetAddress fromI = new InternetAddress(fromMailName.toString());
			message.setFrom(fromI);
			
			InternetAddress to  = new InternetAddress(toMail);
			message.setRecipient(RecipientType.TO, to);
			
			message.setSubject(subject);
			
			message.setContent(content, "text/html;charset=UTF-8");  
			
			Transport transport = new SMTPSSLTransport(session, null);  
		      //这个步很重要，不能省略  
			transport.connect(host,fromMailName, fromMailPass);   
			transport.sendMessage(message, message.getAllRecipients());  
			transport.close();  
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
