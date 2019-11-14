package com.cyb.blogserver.interceptor;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SiteWebSocketPushHandler implements WebSocketHandler {

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		System.out.println("afterConnectionEstablished.......");
	}

	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		System.out.println("handleMessage.......");
	}

	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
		System.out.println("handleTransportError.......");
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		
		System.out.println("afterConnectionClosed.......");
	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}
