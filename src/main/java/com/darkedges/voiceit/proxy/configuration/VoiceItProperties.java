package com.darkedges.voiceit.proxy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("voiceit")
public class VoiceItProperties {

	String developerId;

	public String getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}
	
}
