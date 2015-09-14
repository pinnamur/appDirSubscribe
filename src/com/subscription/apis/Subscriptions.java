package com.subscription.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.oauth1.signature.HmaSha1Method;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.subscription.dao.EventsDAO;
import com.subscription.event.models.Event;
import com.subscription.oauth.OAuthSigner;
import com.subscription.orm.HibernateManager;
import com.subscription.orm.common.EventSchema;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/subscription")
public class Subscriptions {

//	private Client client;
	private OAuthSigner oAuthSigner;
	
	public Subscriptions() {
//		client = ClientBuilder.newBuilder().build().register(oAuthSign()).register(LoggingFilter.class);
		oAuthSigner = new OAuthSigner();
	}

	/**
	 * This method is called if XML is request
	 * 
	 * @param eventUrl
	 * @return
	 */
	@GET
	@Path("/create/{eventUrl}")
	@Produces(MediaType.TEXT_XML)
	public String subscriptionCreated(@PathParam("eventUrl") String eventUrl) {
		
//		WebTarget target = client.target(eventUrl);
//		Event order = target.request()//.register(oAuthSign())
//		.accept(MediaType.APPLICATION_XML).get(Event.class);

		try
		{
			
			HttpURLConnection request = oAuthSigner.signUrl(eventUrl);
			if (request == null) {
				return fail();
			}
			request.setRequestMethod("GET");
			
			int responseCode = request.getResponseCode();
			if (responseCode != 200) {
				return fail(responseCode);
			}

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(request.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response.toString());
			JAXBContext jc = JAXBContext.newInstance(Event.class);

	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        Event event = (Event) unmarshaller.unmarshal(new StringReader(response.toString()));
			
			new EventsDAO().save(event);
			return "<?xml version=\"1.0\"?>" + "<hello> Hello AppDirect " + event.getCreator().getEmail() + " " + event.getPayload().getOrder().getEditionCode()+"</hello>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fail();
	}

	private String fail() {
		return "<?xml version=\"1.0\"?>" + "<hello> Unexpected error occured." +"</hello>";
	}

	private String fail(int responseCode) {
		return "<?xml version=\"1.0\"?>" + "<hello> Unexpected error occured with response code:" + responseCode +"</hello>";
	}
} 