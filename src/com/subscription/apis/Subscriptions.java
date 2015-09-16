package com.subscription.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.subscription.dao.EventsDAO;
import com.subscription.event.models.Event;
import com.subscription.oauth.OAuthSigner;

/**
 * Handle the suscription events from the AppDirect or related MarketPlace.
 */
@Path("/subscription")
public class Subscriptions {

	private OAuthSigner oAuthSigner;
	Logger logger;
	
	@PostConstruct
	public void initialize()
	{
		oAuthSigner = new OAuthSigner();
		logger = Logger.getLogger(Subscriptions.class);
	}

	/**
	 * User purchasing a registered app from AppDirect, will trigger the create event. AppDirect notifies us through this API endpoint.
	 * It will fetch more information about the order from AppDirect, and persist to a data source.
	 * 
	 * @param eventUrl
	 * @return
	 */
	@GET
	@Path("/create/{eventUrl: (.+)?}")
	@Produces(MediaType.TEXT_XML)
	public String subscriptionCreated(@PathParam("eventUrl") String eventUrl) {
		logger.info("Received eventUrl: " + eventUrl);
		try
		{
			HttpURLConnection request = oAuthSigner.signUrl(eventUrl);
			if (request == null) {
				logger.error("Unexpected failure while signing the eventUrl: "+eventUrl);
				return fail();
			}
			request.setRequestMethod("GET");

			int responseCode = request.getResponseCode();
			if (responseCode != 200) {
				logger.error("Got invalid return code: "+responseCode+ " from eventUrl: "+eventUrl);
				return fail(responseCode);
			}

			StringBuffer response = readConnection(request);
			logger.trace("Response read from eventUrl: "+ response);

			JAXBContext jc = JAXBContext.newInstance(Event.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Event event = (Event) unmarshaller.unmarshal(new StringReader(response.toString()));

			logger.debug("Saving the event to database...");
			new EventsDAO().save(event);
			String accountIdentifier = getAccountIdentifier(eventUrl);
			if (accountIdentifier == null) {
				accountIdentifier = event.getPayload().getCompany().getUuid();
			}
			logger.info("Successfuly parsed the event and returning back the accountIdentifier: "+accountIdentifier);

			return successMessage(accountIdentifier);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return fail();
	}

	private StringBuffer readConnection(HttpURLConnection request) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(request.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response;
	}

	private String getAccountIdentifier(String eventUrl) {
		try {
			URI uri = new URI(eventUrl);
			String uriPath = uri.getPath();
			String[] paths = uriPath.split("/");
			if (paths != null && paths.length > 0)
			{
				return paths[paths.length-1];
			}
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	private String successMessage(String accountIdentifier) {
		String returnMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>true</success><message>Account creation successful</message><accountIdentifier>{0}</accountIdentifier></result>";
		return MessageFormat.format(returnMsg, accountIdentifier);
	}

	private String fail() {
		return "<?xml version=\"1.0\"?>" + "<hello> Unexpected error occured." +"</hello>";
	}

	private String fail(int responseCode) {
		return "<?xml version=\"1.0\"?>" + "<hello> Unexpected error occured with response code:" + responseCode +"</hello>";
	}
} 