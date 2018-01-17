package test.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.smarthse.AppStart;
import cn.smarthse.core.model.mail.Mail;
import cn.smarthse.service.jms.JmsMessageProducer;
import cn.smarthse.service.jms.mail.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailTest {

	@Autowired
	private MailService MailService;
	
	@Autowired
	private JmsMessageProducer JmsMessageProducer;

	@Test
	public void send() {
		Mail mail = new Mail();
		mail.setTo("306623095@qq.com");
		mail.setSubject("异步发送邮件");
		mail.setContent("Hi,This is a message!");
		this.MailService.sendMail(mail);
		JmsMessageProducer.sendMessages(mail);
		System.out.println("发送成功..");

	}

}
