package com.websocket.client.utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WSClient extends WebSocketClient {

    public WSClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Conexão aberta!");
        send("Olá servidor!");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Conexão fechada: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Erro: " + ex.getMessage());
    }
    
}
