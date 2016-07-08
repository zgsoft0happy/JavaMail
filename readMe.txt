1.这是个人实现代码，主要是通过Java Mail API实现邮件的发送。

2.配置文件文件夹：conf
	在这里配置服务的主邮件地址以及pop、stmp等协议的地址。
	
	
3.只用配置两个文件
	1）configure.properties:主要是邮箱的用户名，密码等信息的配置，还有邮箱主机等。
		mail.transport.protocol=smtp  	#协议
		mail.smtp.host=smtp.126.com		#服务器主机
		mail.smtp.port=25				#端口
		mail.user=zgsoft_happy@126.com	#用户邮箱
		mail.smtp.auth=true				#验证结果
		mail.password=111111			#邮箱密码
	2）mailconf.properties:主要是邮件的内容等信息。
		from=zgsoft_happy@126.com					#发件人邮箱
		tos=zgsoft_happy@qq.com,1655707737@qq.com	#收件人邮箱，可多人，逗号（英文）隔开
		title=测试									#邮件主题，subject
		content=邮箱测试，多人齐发。					#邮件内容



4.邮件的功能还应该包括文件的传送，下一版本实验项目将加上添加附件的功能。

5.Friend若想使用，可以直接修改两个配置文件的内容，然后直接运行TestMail类的主函数即可。