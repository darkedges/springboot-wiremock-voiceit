package com.darkedges.spring.proxy.voiceit;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.darkedges.voiceit.proxy.configuration.VoiceItConfiguration;
import com.darkedges.voiceit.proxy.configuration.VoiceItProperties;
import com.darkedges.voiceit.proxy.controllers.VoiceItController;
import com.darkedges.voiceit.proxy.services.VoiceItService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "voiceit.baseUrl=http://localhost:6065", classes = { VoiceItConfiguration.class,
		VoiceItRestBase.Config.class })
@AutoConfigureWireMock(stubs = "classpath:/stubs/*.json", port = 6065)
public abstract class VoiceItRestBase {
	@Autowired
	VoiceItController voiceItController;

	@Before
	public void setup() {
		System.out.println(voiceItController);
		RestAssuredMockMvc.standaloneSetup(voiceItController);
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {
		@Bean
		VoiceItService voiceItService(RestTemplate restTemplate) {
			return new VoiceItService(restTemplate);
		}

		@Bean
		VoiceItController voiceItController(VoiceItService service, VoiceItProperties voiceItProperties) {
			return new VoiceItController(service, voiceItProperties);
		}
	}
}
