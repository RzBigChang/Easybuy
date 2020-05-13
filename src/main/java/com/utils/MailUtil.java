package com.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil implements Runnable{
	/*=========收件人的信息==========*/
	private String email;// 收件人邮箱
	private String codeUrl;// 激活码
	
	/*=========初始化===============*/
	public MailUtil(String email, String codeUrl) {
		this.email = email;
		this.codeUrl = codeUrl;
	}
	/*=========线程===============*/
	@Override
	public void run() {
		String from = "13955839036@163.com";// 发件人电子邮箱
		String host = "smtp.163.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
		Properties properties = System.getProperties();// 获取系统属性
		properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
		properties.setProperty("mail.smtp.auth", "true");// 打开认证
		try {
			//QQ邮箱配置语句
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);
			
			// 1.获取默认session对象（创建连接对象，连接到邮箱服务器）
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("13955839036@163.com", "changshuo22"); // 发件人邮箱账号,授权码
				}
			});
			// 2.创建邮件对象
			Message message = new MimeMessage(session);
			// 2.1设置发件人
			message.setFrom(new InternetAddress(from));
			// 2.2设置接收人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 2.3设置邮件主题
			message.setSubject("快来成为易买网的的正式会员吧 (๑•̀ㅂ•́)و✧");
			// 2.4设置邮件内容
			String content = 
			"<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接 (๑•̀ㅂ•́)و✧</h1><h3><a href='http://localhost:8080/EasyBuy/EasybuyUserServlet?action=ActiveServlet&codeUrl="
			+ codeUrl + "'>http://localhost:8080/EasyBuy/EasybuyUserServlet?action=ActiveServlet&codeUrl=" + codeUrl
			+ "</href></h3></body></html>";
			message.setContent(content, "text/html;charset=UTF-8");
			// 3.发送邮件
			Transport.send(message);
			System.out.println("邮件成功发送!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
