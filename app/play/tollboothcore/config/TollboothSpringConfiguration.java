package play.tollboothcore.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import play.libs.ws.WSClient;

@Configuration
@ComponentScan(basePackages={"play.tollboothcore.controllers,play.tollboothcore.service"})
public class TollboothSpringConfiguration {
	
	@Inject
	private WSClient wsclient;

	@Bean
	public  WSClient getWsClient() {
		return wsclient;
	}
	
	@Autowired
	private WSClient wsClient;
	
	
}
