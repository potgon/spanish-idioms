import mysql.connector
import random

conn = mysql.connector.connect(
    host='localhost',
    user='potgon',
    password='admin1234',
    database='idioms_db',
    port=3307
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