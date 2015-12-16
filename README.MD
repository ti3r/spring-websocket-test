## Spring WebSocket Test

This project is a simple example on spring boot and spring websocket that will build a simple
echo websocket server that can be contacted by a simple html page to send messages to the web socket
server. 

#### Explanation
This is a gradle, spring boot app so in order to compile it just run the command `gradle clean build` 
or in order to run it directly run `gradle clean bootRun`. That will initiate the application in 
port 8080 to contact the websocket. Once the application is running you can use your browser to 
visit [http://localhost:8080/](http://localhost:8080/) and play with the websocket.

The websocket listener is configured within the /app context path, so if you want to contact the 
websocket listener directly using javascript you can use the following url 
[ws://localhost:8080/app](ws://localhost:8080/app). The configuration of the websocket listener
is done using the **@EnableWebSocket** annotation in the **WebSocketConfig** class. If you want
to know more about spring web sockets you can visit this [link](https://spring.io/guides/gs/messaging-stomp-websocket/)