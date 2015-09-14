package com.subscription.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.association.AssociationSessionType;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;

/**
 * OpenID utility class to handle the authentication requests for users with openId.
 */
public class OpenidUtil {
	public ConsumerManager manager;
	
	private String returnToUrl = " http://52.25.36.117:8080/appDirSubs/api/auth/openid/return";

    public OpenidUtil() {
        manager = new ConsumerManager();
        manager.setNonceVerifier(new InMemoryNonceVerifier(5000)); 
        manager.setMinAssocSessEnc(AssociationSessionType.DH_SHA256);
    }
    
    
    public String authRequest(String openIdUrl, HttpServletRequest request, HttpServletResponse response) {
        try
        {
            List discoveries = manager.discover(openIdUrl);

            DiscoveryInformation discovered = manager.associate(discoveries);

            // store the discovery information in the user's session
            request.getSession().setAttribute("openid-disc", discovered);

            // obtain a AuthRequest message to be sent to the OpenID provider
            AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

            try {
				response.sendRedirect(authReq.getDestinationUrl(true));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
            return null;

        }
        catch (OpenIDException e)
        {
        	e.printStackTrace();
        }

        return null;
    }
}
