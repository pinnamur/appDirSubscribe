package com.subscription.apis;
/**
 * Copyright (C) 2015 Mathieu Carbou (mathieu@carbou.me)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.oauth1.signature.HmaSha1Method;

import javax.ws.rs.Priorities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class AppDirect {
	private static final String PROPERTY_CONSUMER_KEY = "appdirint-41352";
    private static final String PROPERTY_CONSUMER_SECRET = "zupD4qIs107zd0Tk";


    public static void main(String... args) {
        ConsumerCredentials consumerCredentials = new ConsumerCredentials(PROPERTY_CONSUMER_KEY, PROPERTY_CONSUMER_SECRET);
        Feature oauth = OAuth1ClientSupport.builder(consumerCredentials)
            .signatureMethod(HmaSha1Method.NAME)
            .feature()
            .build();
        Client client = ClientBuilder.newBuilder()
            .build()
            .register(oauth)
            .register(LoggingFilter.class, Priorities.USER);
        String eventXML = client.target("https://www.appdirect.com/api/integration/v1/events/dummyOrder")
            .request(MediaType.APPLICATION_XML_TYPE)
            .get()
            .readEntity(String.class);
        System.out.println(eventXML);
    }

}