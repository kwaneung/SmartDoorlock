import socket
import struct
import asyncio
import websockets
import pickle
import time
import os

def get_ipaddress():
  s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
  s.connect(("gmail.com",80))
  r = s.getsockname()[0]
  s.close()
  return r

async def echo(websocket, path):
    message = await websocket.recv() 
    print(message)
    await websocket.send(message) 

 
print("Server start: "+get_ipaddress())
 
port = 180
asyncio.get_event_loop().run_until_complete(
    websockets.serve(echo, get_ipaddress(), port, close_timeout = 100))
asyncio.get_event_loop().run_forever()
