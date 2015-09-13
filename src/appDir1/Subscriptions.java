package appDir1;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.subscription.event.models.Event;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/subscription")
public class Subscriptions {

	private Client client;

	public Subscriptions() {
		ClientConfig config = new ClientConfig();
		client = ClientBuilder.newClient(config);
	}

	private URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/appDir1").build();
	}

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Farsey";
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
		//REMOVE::
		eventUrl = "https://www.appdirect.com/rest/api/events/dummyOrder";

		WebTarget target = client.target(eventUrl);
		try
		{
			Event order = target.request()
					.accept(MediaType.APPLICATION_XML).get(Event.class);
			return "<?xml version=\"1.0\"?>" + "<hello> Hello AppDirect " + order.getCreator().getEmail() + " " + order.getPayload().getOrder().getEditionCode()+"</hello>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Boo! " +"</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Missy" + "</title>"
				+ "<body><h1>" + "Hello Hussey" + "</body></h1>" + "</html> ";
	}

} 