package com.subscription.apis;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.subscription.auth.OpenidUtil;

@Path("/auth")
public class Authentication {

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	Logger logger;
	private OpenidUtil openidUtil;

	@PostConstruct
	public void initialize() {
		logger = Logger.getLogger(Authentication.class);
		openidUtil = new OpenidUtil();
	}

	/**
	 * This method is called to handle requests for openid authentication.
	 * 
	 * @param openid
	 *            openId identifier of the user from the AppDirect marketplace.
	 * @return
	 */
	@GET
	@Path("/login/{openid: (.+)?}")
	@Produces(MediaType.TEXT_XML)
	public String openIdLogin(@PathParam("openid") String openId) {
		logger.trace("PathParam Id: " + openId);
		return openidUtil.authRequest(openId, request, response);
	}

	/**
	 * This method will be called/redirected after processing the openId login
	 * requests.
	 */
	@GET
	@Path("/openid/return")
	public String openIdReturnUrl() {
		try {
			logger.info("Redirecting to home page after openId login processing");
			response.sendRedirect("/appDirSubs");
		} catch (IOException e) {
			logger.error(e);
		}
		return successMessage();
	}

	private String successMessage() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>true</success></result>";
	}

}