package com.subscription.apis;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.filter.LoggingFilter;

import com.subscription.auth.OpenidUtil;

@Path("/auth")
public class Authentication {

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;


	public Authentication() {
	}

	/**
	 * This method is called to handle requests for openid authentication.
	 * 
	 * @param openid openId identifier of the user from the AppDirect marketplace.
	 * @return
	 */
	@GET
	@Path("/login/{openid}")
	@Produces(MediaType.TEXT_XML)
	public String openIdLogin(@PathParam("openid") String openId) {
		return new OpenidUtil().authRequest(openId, request, response);
	}

	/**
	 * This method will be called/redirected after processing the openId login requests.
	 */
	@GET
	@Path("/openid/return")
	public void openIdReturnUrl() {
		try {
			System.out.println("Redirecting to home");
			response.sendRedirect("/appDirSubs");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


} 