package com.darkedges.voiceit.proxy.exceptions;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ProxyResponseErrorHandler implements ResponseErrorHandler  {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
