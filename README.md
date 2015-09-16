# appDirSubscribe

To generate war of the project: mvn clean install

The service is hosted on AWS EC2 instance at: http://52.25.36.117:8080/appDirSubs/

# API endpoints
Home Page 				  - http://52.25.36.117:8080/appDirSubs/

Subscription create event - http://52.25.36.117:8080/appDirSubs/api/subscription/create/https://www.appdirect.com/rest/api/events/dummyOrder

OpenId login			  - http://52.25.36.117:8080/appDirSubs/api/auth/login/{openid}