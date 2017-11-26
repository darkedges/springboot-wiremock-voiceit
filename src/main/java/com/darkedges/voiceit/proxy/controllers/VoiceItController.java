package com.darkedges.voiceit.proxy.controllers;

import java.io.IOException;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.darkedges.voiceit.proxy.configuration.VoiceItProperties;
import com.darkedges.voiceit.proxy.services.VoiceItService;

@RestController()
@RequestMapping("/voiceit")
public class VoiceItController {

	private final VoiceItService voiceItservice;
	private final VoiceItProperties voiceItProperties;

	public VoiceItController(VoiceItService voiceItservice, VoiceItProperties voiceItProperties) {
		this.voiceItservice = voiceItservice;
		this.voiceItProperties = voiceItProperties;
	}

	@RequestMapping(value = "/getUser/{userId}", method = {
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getUser(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password) {
		return this.voiceItservice.getUser(userId, password, voiceItProperties.getDeveloperId());
	}

	@RequestMapping(value = "/createUser/{userId}", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> createUser(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password) {
		return this.voiceItservice.createUser(userId, password, voiceItProperties.getDeveloperId());

	}

	@RequestMapping(value = "/deleteUser/{userId}", method = {
			RequestMethod.DELETE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password) {
		return this.voiceItservice.deleteUser(userId, password, voiceItProperties.getDeveloperId());

	}

	@RequestMapping(value = "/getEnrollments/{userId}", method = {
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getEnrollments(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password) {
		return this.voiceItservice.getEnrollments(userId, password, voiceItProperties.getDeveloperId());
	}

	@RequestMapping(value = "/createEnrollment/{userId}", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> createEnrollment(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password, @RequestParam("file") MultipartFile file)
			throws IOException {
		return this.voiceItservice.createEnrollment(userId, password, file, voiceItProperties.getDeveloperId());
	}

	@RequestMapping(value = "/deleteEnrollment/{userId}/{enrolmentId}", method = {
			RequestMethod.DELETE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteEnrollment(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password, @PathVariable String enrolmentId) {
		return this.voiceItservice.deleteEnrollment(userId, password, enrolmentId, voiceItProperties.getDeveloperId());
	}

	@RequestMapping(value = "/createEnrollmentByWavURL/{userId}", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> createEnrollmentByWavURL(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password, @RequestHeader("voiceit-wavurl") URL url)
			throws IOException {
		return this.voiceItservice.createEnrollmentByWavURL(userId, password, url, voiceItProperties.getDeveloperId());
	}

	@RequestMapping(value = "/authentication/{userId}", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> authentication(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password, @RequestParam("file") MultipartFile file)
			throws IOException {
		return this.voiceItservice.authentication(userId, password, file, voiceItProperties.getDeveloperId());

	}

	@RequestMapping(value = "/authenticationByWavURL/{userId}", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> authenticationByWavURL(@PathVariable String userId,
			@RequestHeader(name = "voiceit-password") String password, @RequestHeader("voiceit-wavurl") URL url) {
		return this.voiceItservice.authenticationByWavURL(userId, password, url, voiceItProperties.getDeveloperId());
	}

}
