from fastapi import FastAPI, WebSocket, WebSocketDisconnect
import uvicorn

from secrets import token_urlsafe
web = FastAPI()

@web.websocket("/ws")
async def websocket_handler(ws: WebSocket):
    await ws.accept()

    #fechar conexao: await websocket.close()
    name =  ws.query_params.get("token")
    print(name, "Conectou!")
    try:
        while True:
            data = await ws.receive_text()
            await ws.send_text(f"Messagem do servidor: {token_urlsafe(6)}, Sua msg: {data}")
    
    except WebSocketDisconnect:
        pass


uvicorn.run(web, host="0.0.0.0", port=8000)
