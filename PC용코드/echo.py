import socket
import struct
import asyncio
import websockets
import pickle
import time
import os

def get_ipaddress(network):
    return socket.gethostbyname(socket.getfqdn())

async def echo(websocket, path):
    message = await websocket.recv() 
    print(message)

    if message == '1':
        print('Account_Lookup')
        result, UUID = await Account_Lookup(websocket)
        await websocket.send(result)
        if result == '1':
            #로그파일 저장
            now = time.gmtime(time.time()) #현재시각을 가져온다.
            with open(str(now.tm_year) + '_' + str(now.tm_mon) + '.txt' , 'a') as f:
                f.write(time.ctime() + ' - ' + UUID + '\n')
    elif message == '2':
        print('Account_Register')
        await websocket.send(await Account_Register(websocket))
    elif message == '3':
        print('Webcam_streaming')
    elif message == '4':
        print('Webcam_Capturing')

 
print("Server start: "+get_ipaddress('enxb827eb9cd99f'))
 
port = 180
asyncio.get_event_loop().run_until_complete(
    websockets.serve(echo, get_ipaddress('enxb827eb9cd99f'), port, close_timeout = 100))
asyncio.get_event_loop().run_forever()
