import os
import json
import mysql.connector
from dotenv import load_dotenv
from pathlib import Path

env_path = Path(__file__).resolve().parents[2] / '.env'
load_dotenv(dotenv_path=env_path)

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