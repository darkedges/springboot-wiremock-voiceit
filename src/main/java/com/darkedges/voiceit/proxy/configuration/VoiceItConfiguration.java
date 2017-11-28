package com.darkedges.voiceit.proxy.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.darkedges.voiceit.proxy.exceptions.ProxyResponseErrorHandler;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Configuration
@EnableConfigurationProperties(VoiceItProperties.class)
public class VoiceItConfiguration {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.setConnectTimeout(60000).build();
		restTemplate.setErrorHandler(new ProxyResponseErrorHandler());
		restTemplate.setRequestFactory(okHttp3ClientHttpRequestFactory());
		return restTemplate;
	}

	@Bean
	public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
		return new OkHttp3ClientHttpRequestFactory(client);
	}
}
