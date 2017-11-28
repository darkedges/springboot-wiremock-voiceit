package com.darkedges.voiceit.proxy.services;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class VoiceItService {

	@Value("${voiceit.baseUrl:https://siv.voiceprintportal.com}")
	private String base;

	private final RestTemplate restTemplate;

	public VoiceItService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String go() {
		return this.restTemplate.getForEntity(this.base + "/resource", String.class).getBody();
	}

	private String getSHA256(String text) {
		return DigestUtils.sha256Hex(text);
	}

	public ResponseEntity<String> getUser(String userId, String password, String developerId) {
		return userOperation(HttpMethod.GET, userId, password, developerId);
	}

	public ResponseEntity<String> createUser(String userId, String password, String developerId) {
		return userOperation(HttpMethod.POST, userId, password, developerId);
	}

	public ResponseEntity<String> deleteUser(String userId, String password, String developerId) {
		return userOperation(HttpMethod.DELETE, userId, password, developerId);
	}

	public ResponseEntity<String> getEnrollments(String userId, String password, String developerId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/enrollments", HttpMethod.GET, entity,
				String.class);
	}

	public ResponseEntity<String> deleteEnrollment(String userId, String password, String enrolmentId,
			String developerId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/enrollments/" + enrolmentId, HttpMethod.DELETE,
				entity, String.class);
	}

	public ResponseEntity<String> createEnrollment(String userId, String password, MultipartFile file,
			String developerId) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		headers.add("Content-type", "audio/wav");
		headers.add("ContentLanguage", "en-GB");
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(file.getBytes(), headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/enrollments", HttpMethod.POST, entity,
				String.class);
	}

	public ResponseEntity<String> userOperation(HttpMethod operation, String userId, String password,
			String developerId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/users", operation, entity, String.class);
	}

	public ResponseEntity<String> createEnrollmentByWavURL(String userId, String password, URL url,
			String developerId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		headers.add("VsitwavURL", url.toString());
		headers.add("Content-type", "audio/wav");
		headers.add("ContentLanguage", "en-GB");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/enrollments/bywavurl", HttpMethod.POST, entity,
				String.class);
	}

	public ResponseEntity<String> authentication(String userId, String password, MultipartFile file, String developerId)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		headers.add("Content-type", "audio/wav");
		headers.add("ContentLanguage", "en-GB");
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(file.getBytes(), headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/authentications", HttpMethod.POST, entity,
				String.class);
	}

	public ResponseEntity<String> authenticationByWavURL(String userId, String password, URL url, String developerId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("UserId", userId);
		headers.add("VsitPassword", getSHA256(password));
		headers.add("VsitDeveloperId", developerId);
		headers.add("VsitwavURL", url.toString());
		headers.add("Content-type", "audio/wav");
		headers.add("ContentLanguage", "en-GB");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return this.restTemplate.exchange(this.base + "/sivservice/api/authentications/bywavurl", HttpMethod.POST,
				entity, String.class);
	}

}