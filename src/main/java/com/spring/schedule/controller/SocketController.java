package com.spring.schedule.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public  class SocketController extends TextWebSocketHandler {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private static List<WebSocketSession> sessionList = new ArrayList();

	/**
	 * websocket 연결 성공 시
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		// 채팅방에 접속한 사용자 세션을 리스트에 저장
		sessionList.add(webSocketSession);

		// 모든 세션에 채팅 전달
		for (WebSocketSession session : sessionList) {
			session.sendMessage(new TextMessage(webSocketSession.getId() + "님이 입장 했습니다."));
		}
	}

	/**
	 * websocket 연결 종료 시
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		// 채팅방에서 퇴장한 사용자 세션을 리스트에서 제거
		sessionList.remove(webSocketSession);

		// 모든 세션에 채팅 전달
		for (WebSocketSession session : sessionList) {
			session.sendMessage(new TextMessage(webSocketSession.getId() + "님이 퇴장 했습니다."));
		}
	}

	/**
	 * websocket 메세지 수신 및 송신
	 */
	@Override
	protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws Exception {
		// 모든 세션에 채팅 전달
		for (WebSocketSession session : sessionList) {
			session.sendMessage(new TextMessage(webSocketSession.getId() + ": " + textMessage.getPayload()));
		}
	}

	public static void handleNoticeMessage(String sendMessage) throws Exception {
		// 모든 세션에 채팅 전달
		for (WebSocketSession session : sessionList) {
			session.sendMessage(new TextMessage("SERVER:: " + sendMessage));
		}
	}
}