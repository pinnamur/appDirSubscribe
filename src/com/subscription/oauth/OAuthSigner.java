package com.subscription.oauth;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * Responsible for signing requests before making API calls to AppDirect's marketplace.
 */
public class OAuthSigner {

	//TODO: to be fetched from properties file.
	private static final String PROPERTY_CONSUMER_KEY = "appdirint-41352";
	private static final String PROPERTY_CONSUMER_SECRET = "zupD4qIs107zd0Tk";
	private OAuthConsumer consumer;

	public OAuthSigner() {
		consumer = new DefaultOAuthConsumer(PROPERTY_CONSUMER_KEY, PROPERTY_CONSUMER_SECRET);
	}

	/**
	 * Signs all outgoing requests to AppDirect marketplace.
	 * @param  eventUrl [description]
	 * @return          [description]
	 */
	public HttpURLConnection signUrl(String eventUrl) {
		try
		{
			URL url = new URL(eventUrl);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			consumer.sign(request);
			return request;
		}
		catch (IOException | OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
