import os
import json
import mysql.connector
from dotenv import load_dotenv

load_dotenv()

json_data = json.loads("static/idioms.json")

conn = mysql.connector.connect(
    host=os.getenv(DB_HOST),
    user=os.getenv(SPRING_DATASOURCE_USERNAME),
    password=os.getenv(SPRING_DATASOURCE_PASSWORD),
    database=os.getenv(MYSQL_DATABASE),
    port=os.getenv(PYTHON_DB_PORT)
)

cursor = conn.cursor()

for item in json_data:
    cursor.execute('''
    INSERT INTO idiom (completed, name) VALUES (%s, %s)
    ''', (0, item['name']))

conn.commit()

cursor.close()
conn.close()