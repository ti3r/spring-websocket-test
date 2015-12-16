package com.albiworks.test.springaop;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * The WebSocket handler in charge of sending back the ping and ack messages to the 
 * client
 * @author Alexandro Blanco <alex@albiworks.com>
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {

	Timer timer = new Timer(true);
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		
		StringBuilder b = new StringBuilder("Server has received the following message: ");
		b.append(msg);
		b.append(" . Ack Time: "+LocalDateTime.now().toString());
		
		session.sendMessage(new TextMessage(b.toString()));
	}

	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		timer.scheduleAtFixedRate(new PingClientChecker(session, timer), 0, 5000);
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		timer.cancel();
	}



	class PingClientChecker extends TimerTask{

		WebSocketSession session;
		Timer timer; 
		
		public PingClientChecker(WebSocketSession session, Timer timer) {
			this.session = session;
		}

		@Override
		public void run() {
			if (session != null && session.isOpen()){
				try {
					session.sendMessage(new TextMessage("Ping"));
				} catch (IOException e) {
					e.printStackTrace();
					timer.cancel();
				}
			}
		}
		
	}
	
}
