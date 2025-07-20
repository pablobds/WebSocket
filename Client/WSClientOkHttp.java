package com.websocket.client.utils;

import com.websocket.client.MainActivity;
import com.websocket.client.R;

import android.widget.TextView;

import okhttp3.*;


public class WSClientOkHttp {
    
    private WebSocket ws;
    private MainActivity ctx;
    
    
    public WSClientOkHttp(MainActivity ctx){
        this.ctx = ctx;
    }
    
    
    public void connect(String host, String token) {
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder().url(host + token).build();
        
        
        ws = client.newWebSocket(request, new WebSocketListener() {
            
            @Override
            public void onOpen(WebSocket ws, Response r) {
               super.onOpen(ws, r);
               ws.send("ping");
               TextView status = ctx.findViewById(R.id.status);
               status.setText("Conectado!");
            }
            
            @Override
            public void onMessage(WebSocket ws, String data) {
                super.onMessage(ws, data);
                TextView msg = ctx.findViewById(R.id.msg);
                msg.setText(data);
            }
            
            @Override
            public void onClosing(WebSocket ws, int code, String r) {
                super.onClosing(ws, code, r);
                TextView status = ctx.findViewById(R.id.status);
                status.setText("Code: " + code + r);
            }
            
            
        });
        
    }
        
        
    public void sendMessage(String content) {
        ws.send(content);
    }
}
