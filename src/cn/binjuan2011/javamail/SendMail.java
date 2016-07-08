package cn.binjuan2011.javamail;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * <br/>CSDN主页：<a href="http://my.csdn.net/y1193329479">CSDN主页</a>
 * <br/>Copyright (C), 2016-2017, YYB , Thomas
 * <br/>This program is protected by copyright laws.
 * <br/>Programe Name:
 * <br/>Date: 2016年6月24日  Time: 下午3:01:21   Locate:149
 * <br/>fileName: 
 * @author yyb zgsoft_happy@126.com
 * @version 1.0
 * description：
 */

public class SendMail implements Serializable {

	public static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
	{
//		new SendMail().sendMessage("ss", "222", "sss", "aaa");
		Properties userProp = new Properties();
		Properties mailProp = new Properties();
		try {
			userProp.load(new FileReader("conf/configure.properties"));
//			userProp.put("mail.transport.protocol", "smtp");
			mailProp.load(new FileReader("conf/mailconf.properties"));
		} catch (IOException e) {
			System.out.println("找不到文件");
			e.printStackTrace();
		}
		Enumeration em = userProp.propertyNames();
		while(em.hasMoreElements())
		{
			String e = (String) em.nextElement();
			System.out.println(e + ": " + userProp.getProperty(e));
		}
		em = mailProp.propertyNames();
		while(em.hasMoreElements())
		{
			String e = (String) em.nextElement();
			System.out.println(e + ": " + mailProp.getProperty(e));
		}
		new SendMail().sendMessage(userProp, mailProp);
	}
	
	public void sendMessage(Properties userProp, Properties mailProp)
	{
		 Auth authenticator=new Auth(userProp.getProperty("mail.user"), userProp.getProperty("mail.password"));
         try {
                
                //创建session实例
//                Session session=Session.getInstance(userProp, authenticator);
                Session session = Session.getInstance(userProp);
                 MimeMessage message=new MimeMessage(session);
                 session.setDebug(true);
                  
                 //设置from发件人邮箱地址
                 message.setFrom(new InternetAddress(mailProp.getProperty("from")));
                 //收件人to LIST
                  ArrayList<Address> toList=new ArrayList<Address>();
                  //收件人字符串通过,号分隔收件人
                  String[] toArray=mailProp.getProperty("tos").split(",");
                  for (int i = 0; i < toArray.length; i++)
                   {
                    String str = toArray[i];
                    toList.add(new InternetAddress(str));
                   }
                  //将收件人列表转换为收件人数组
                  Address[] addresses=new Address[toList.size()];
                  addresses=toList.toArray(addresses);
                 //设置to收件人地址
                 message.setRecipients(MimeMessage.RecipientType.TO,addresses);
                 //设置邮件标题
                 message.setSubject(mailProp.getProperty("title"));
                 //设置邮件内容
                 message.setContent(mailProp.getProperty("content"), "text/html;charset=UTF-8");
                 //Transport.send(message);
                 Transport transport=session.getTransport();
                 transport.connect(userProp.getProperty("mail.smtp.host"),
                		 userProp.getProperty("mail.user"), userProp.getProperty("mail.password"));
                 transport.sendMessage(message,addresses);
//                 log.info("发送邮件成功！");
                  
        } catch (Exception e) {
            // TODO: handle exception
//            log.error("发送邮件失败！");
            e.printStackTrace();
        }
	}

	public void send() {
		Properties userProp = new Properties();
		Properties mailProp = new Properties();
		try {
			userProp.load(new FileReader("conf/configure.properties"));
			mailProp.load(new FileReader("conf/mailconf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendMessage(userProp, mailProp);
	}
}

class Auth extends Authenticator
{
	Properties pwdProperties;
	
	public Auth(String username , String password)
	{
		pwdProperties = new Properties();
		try {
			pwdProperties.put(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PasswordAuthentication passwordAuthentication()
	{
		 String userName=getDefaultUserName();
	        //当前用户在密码表里
	        if (pwdProperties.containsKey(userName)) {
	            //取出密码，封装好后返回
	            return new PasswordAuthentication(userName, pwdProperties.getProperty(userName).toString());	             
	        }
	        else {
	            //如果当前用户不在密码表里就返回Null
	            System.out.println("当前用户不存在");
	            return null;
	         }
	}
}
