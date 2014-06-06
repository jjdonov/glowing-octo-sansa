#App Direct Integration Project

##About
Simple "Dummy Application" handling OpenId Authentication and Susbcription Management Endpoints for use on the Google App Engine platform. 

You will need to provide your own OAuth Consumer Key and Consumer Secret. Specify those values in _/ad-integration-war/src/main/resources/oauth.properties_

In a real production the application's own /event endpoints would need to be OAuth protected. Likewise, the rest of the pages would need to be protected by OpenId. For ease of testing these have been left open.

Once authenticating via OpenId with AppDirect, the user's full name will be displayed next to _Welcome_ on the root page (index.jsp). 

The application persists some data to the Data Store, just to show some sample behavior when reacting to events.

##Building and Running The Project:

After pulling down a copy of the project, from the projects root directory run to build the project:
```Shell
mvn clean install
```

To Run the project locally:
```Shell
cd ad-integration-ear
mvn appengine:devserver
```

Or, to push the application to the Google App Engine:
```Shell
mvn appengine:update
```

##A Note About Project Structure and Debugging
The project structure is based on the appengine-skeleton archetype. As noted in the Google App Engine documentation, there are some difficulties between the mvn plugin and the Eclipse plug in.

In order to enable debugging a plugin has been specified in the ear's pom.xml. You will also need to set up a   _Remote Java Application Debugging_ configuration within Eclipse. 

For more information on this see: 
> http://stackoverflow.com/questions/13924990/how-do-i-make-eclipse-and-mvn-appenginedevserver-talk-to-each-other


