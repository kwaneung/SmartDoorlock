from flask import Flask, render_template, request
import datetime
import numpy as np
import time
import sqlite3

app = Flask(__name__, static_url_path='/web')

strTime = datetime.datetime.now().strftime("%y-%m-%d %H:%M")

@app.route('/')
@app.route('/', methods=['POST'])
def main():
	templateData = {
	'title' : 'manager page',
	'inform' : 'select function'
	}
	return render_template('main.html', **templateData)

@app.route('/search', methods=['POST'])
def search():
	templateData = {
	'title' : 'search log',
	'inform' : 'Input date'
	}
	return render_template('search.html', **templateData)

@app.route('/reg', methods=['POST'])
def reg():
	templateData = {
	'title' : 'update inform',
	'inform' : 'Input inform(id, name, role, permission)'
	}
	return render_template('reg.html', **templateData)

@app.route('/echo_reg', methods=['POST'])
def echo_reg():
	conn = sqlite3.connect('DoorLock.db')
	cur = conn.cursor()

	id = request.form.get('id')
	name = request.form.get('name')
	role = request.form.get('role')
	perm = request.form.get('perm')

	result = []
	cur.execute("UPDATE user set name = (?), role = (?), perm = (?) WHERE id = (?)", (name, role, perm, id))
	conn.commit()
	result.append("id : " + id + " -> " + name + " " + role + " | perm : " + perm)
	return "</br>".join(result)

@app.route('/echo_log', methods=['POST'])
def echo_log():
	conn = sqlite3.connect('DoorLock.db')
	cur = conn.cursor()

	day = request.form.get('day')
	year = request.form.get('year')
	month = request.form.get('month')

	now = time.gmtime(time.time())	
	result = []



	cur.execute("SELECT YEAR, MONTH, DAY, TIME, role, name, u.id FROM log l, user u WHERE l.year = (?) and l.month = (?) and l.day = (?) and l.id == u.id", (year, month, day))
	for i in cur:
		result.append((str(i[0]) + ' ' + str(i[1]) + ' ' + str(i[2]) + ' ' + i[3] + ' ' + i[4] + ' | name : ' + i[5] + ' | id : ' + i[6]))	
	
	return  "</br>".join(result)

if __name__ == '__main__':
		app.run(host='0.0.0.0', port=8888, debug=True, threaded=True)

