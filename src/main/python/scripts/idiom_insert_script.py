import json
import mysql.connector

json_data = json.loads("static/idioms.json")

conn = mysql.connector.connect(
    host='localhost',
    user='potgon',
    password='admin1234',
    database='idioms_db',
    port=3307
)

cursor = conn.cursor()

for item in json_data:
    cursor.execute('''
    INSERT INTO idiom (completed, name) VALUES (%s, %s)
    ''', (0, item['name']))

conn.commit()

cursor.close()
conn.close()