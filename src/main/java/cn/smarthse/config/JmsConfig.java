package cn.smarthse.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import cn.smarthse.business.service.jms.JmsMessageListener;

@Configuration
@EnableJms
public class JmsConfig implements JmsListenerConfigurer {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private JmsMessageListener jmsMessageListener;

	// 1. <!-- 配置连接ActiveMQ的ConnectionFactory -->
	// @Bean("activeMQConnectionFactory")
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory a = new ActiveMQConnectionFactory();
		a.setBrokerURL("tcp://localhost:61616");
		a.setTrustAllPackages(true); //所有序列化的对象都可以进入mq
		log.info("JMS step1： 配置连接ActiveMQ的ConnectionFactory ");
		return a;
	}

	// 2. <!--为了提高效率，配置一个连接池-->
	@Bean("cachingConnectionFactory")
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory a = new CachingConnectionFactory();
		a.setTargetConnectionFactory(activeMQConnectionFactory());
		a.setSessionCacheSize(10);
		log.info("JMS step2： ConnectionFactory的连接池 ");
		return a;
	}

	// 3.1 <!-- 配置broker的destination，点对点的消息-->
	@Bean
	public ActiveMQQueue activeMQQueue() {
		ActiveMQQueue a = new ActiveMQQueue("darcy.queue");
		log.info("JMS step3.1： 配置broker的destination");
		return a;
	}

	// 3.2 <!-- 配置broker的destination，订阅/发布的消息-->
	@Bean
	public ActiveMQTopic activeMQTopic() {
		ActiveMQTopic a = new ActiveMQTopic("darcy.topic");
		log.info("JMS step3.2： 配置broker的destination");
		return a;
	}

	// 4. <!-- 配置Spring的JmsTemplate -->
	@Bean
	public JmsTemplate jmsTemplate(CachingConnectionFactory cachedConnectionFactory, ActiveMQQueue destination) {
		JmsTemplate a = new JmsTemplate();
		a.setConnectionFactory(cachedConnectionFactory);
		a.setDefaultDestination(destination);
		log.info("JMS step4： 配置Spring的JmsTemplate");
		return a;
	}

	// 5. <!-- 配置Spring的JmsMessageListener -->
	@Bean
	public JmsMessageListener jmsMessageListener(CachingConnectionFactory cachedConnectionFactory,
			ActiveMQQueue destination) {
		JmsMessageListener a = new JmsMessageListener();
		log.info("JMS step5： 配置Spring的JmsMessageListener");
		return a;
	}

	// 6. <!-- 配置listener到listener-container当中 -->
	@Bean
	public DefaultMessageListenerContainer defaultMessageListenerContainer(
			CachingConnectionFactory cachedConnectionFactory, ActiveMQQueue destination, JmsMessageListener listener) {
		DefaultMessageListenerContainer a = new DefaultMessageListenerContainer();
		a.setConnectionFactory(cachedConnectionFactory);
		a.setDestination(destination);
		a.setMessageListener(listener);
		log.info("JMS step6： 配置listener到listener-container当中");
		return a;
	}

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("myJmsEndpoint");
		endpoint.setDestination("darcy.queue");
		endpoint.setMessageListener(jmsMessageListener);
		registrar.registerEndpoint(endpoint);
	}

}
