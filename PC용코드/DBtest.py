import sqlite3
import time

now = time.localtime(time.time())
conn = sqlite3.connect('DoorLock.db')
cur = conn.cursor()


#cur.execute('drop table user')
#cur.execute('drop table log')

cur.execute('CREATE TABLE IF NOT EXISTS log( YEAR INTEGER, MONTH INTEGER, DAY INTEGER, TIME TEXT, ID TEXT, TIMESTRING INTEGER)')
cur.execute('CREATE TABLE IF NOT EXISTS user( ID text PRIMARY KEY, NAME text DEFAULT "name", ROLE text DEFAULT "role", PERM text DEFAULT "0")')
'''
cur.execute("SELECT name FROM sqlite_master WHERE type='table';")
for row in cur:
    print(row[0])
'''

#cur.execute('DELETE FROM user WHERE ID = "2016154045"')

#cur.execute('INSERT INTO user(ID) VALUES("2016154045")')
conn.commit()

print('----user-----------------------')
cur.execute('select * from user')
for i in cur:
	print(i)
cur.execute('select * from log')
print('----log-----------------------')

#cur.execute('INSERT INTO user VALUES("24049152163", "홍길동", "아빠", "0")')
#conn.commit()

for i in cur:
	print(i)
