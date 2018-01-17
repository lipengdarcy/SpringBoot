package cn.smarthse.service.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 方式二：写一个JmsMessageListener监听器异步接收消息
 * */

@Component
public class JmsMessageListener implements MessageListener{
	private final Log log = LogFactory.getLog(getClass());
	
    public void onMessage(Message message) {
        ObjectMessage msg = (ObjectMessage) message;
		log.info("Consumed message: " + msg.toString());
    }
}
