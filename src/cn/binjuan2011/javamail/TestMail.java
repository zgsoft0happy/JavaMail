package cn.binjuan2011.javamail;

import java.io.Serializable;

/**
 * <br/>CSDN主页：<a href="http://my.csdn.net/y1193329479">CSDN主页</a>
 * <br/>Copyright (C), 2016-2017, YYB , Thomas
 * <br/>This program is protected by copyright laws.
 * <br/>Programe Name:
 * <br/>Date: 2016年6月24日  Time: 下午3:01:21   Locate:149
 * <br/>fileName: 
 * @author yyb zgsoft_happy@126.com
 * @version 1.0
 * description：测试邮箱
 */

public class TestMail implements Serializable {

	public static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new SendMail().send();
	}
}
