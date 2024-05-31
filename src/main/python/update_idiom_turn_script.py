import os
import mysql.connector
import random
from dotenv import load_dotenv
from pathlib import Path


env_path = Path(__file__).resolve().parents[2] / '.env'
load_dotenv(dotenv_path=env_path)

conn = mysql.connector.connect(
    host=os.getenv(DB_HOST),
    user=os.getenv(SPRING_DATASOURCE_USERNAME),
    password=os.getenv(SPRING_DATASOURCE_PASSWORD),
    database=os.getenv(MYSQL_DATABASE),
    port=os.getenv(PYTHON_DB_PORT)
)

cursor = conn.cursor()

select_query = "SELECT id FROM idiom"
cursor.execute(select_query)
idiom_ids = cursor.fetchall()

num_idioms = len(idiom_ids)
unique_orders = random.sample(range(1, num_idioms + 1), num_idioms)

update_query = "UPDATE idiom SET turn = %s WHERE id = %s"
for idiom_id, order in zip(idiom_ids, unique_orders):
    cursor.execute(update_query, (order, idiom_id[0]))

conn.commit()
cursor.close()
conn.close()