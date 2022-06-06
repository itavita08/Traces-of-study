# pymysql
- 파이썬에서 MySQL, MariaDB DBMS와 연동하는 다양한 함수를 제공하는 모듈
- Python [DB API 2.0](http://www.python.org/dev/peps/pep-0249) 표준을 따른다.
- [https://github.com/PyMySQL/PyMySQL/](https://github.com/PyMySQL/PyMySQL/)
- [https://pymysql.readthedocs.io/en/latest/](https://pymysql.readthedocs.io/en/latest/)
## 기본 작성 절차
1. Database 연결
    ```python
       connection =  pymysql.connect(host="DBMS 서버 ip", 
                                     port="port번호", 
                                     user="계정명", 
                                     password="비밀번호", 
                                     db="연결할데이터베이스이름", 
                                     charset='utf8')
    ```
    - port 번호 기본값: 3306
2. Connection을 이용해 Cursor 생성
    - Cursor: 연결된 Database에 sql문을 전송하고 select결과 조회 기능을 제공하는 객체
    ```python
        cursor = connection.cursor()
    ```
3. Cusror를 이용해 SQL문 전송
    ```python
        cursor.execute("sql문")
    ```
4. 연결 닫기
    - cursor, connection 연결을 닫는다.
    - with문을 이용할 수 있다. 
    ```python
    cursor.close()
    connection.close()
    ```
#### 테이블 생성
- pymysql을 이용해서는 DML(insert,update,delete)문을 실행한다.(DDL은 거의 실행하지 않는다.)
```python
# import
import pymysql

# DB 연결 - connect() => 연결 실패시 Exception 발생
connection = pymysql.connect(host='127.0.0.1', 
                             port=3306,
                             user='scott',
                             password='tiger',
                             db='testdb')
# Cursor를 Connection으로 부터 얻어온다. - Cursor: sql문 실행을 관리하는 객체.
cursor = connection.cursor()
# sql 문 실행 => 쿼리문 실행 실패시(sql문을 잘못 짠 경우, 제약조건 문제) -> Exception 발생
# sql 문은 string을 정의. execute() 메소드를 이용해서 DB에 전송.
sql = """
create table test_user (
  id  int  auto_increment  primary key, 
  name  varchar(30) not null,
  email varchar(100) not null unique,
  tall  decimal(5,2),
  birthday date
)
"""
cursor.execute('drop table if exists test_user')
cursor.execute(sql)  # 실행
# 연결닫기 - cursor, connection close
cursor.close()
connection.close()
```
### insert
```python
import pymysql # auto commit=False

sql = "insert into test_user (name, email, tall, birthday) values ('홍길동', 'hong@a.com', 182.23, '2000-02-03')"
try:
    # 연결
    connection = pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8')
    # cursor 생성
    cursor = connection.cursor()
    # insert문 실행
    cnt = cursor.execute(sql)
    connection.commit() # insert/delete/update 문 실행 후 commit 처리를 해야 한다.
    print(f"{cnt} 행이 insert 되었습니다.")
    
except Exception as e:
    if connection:
        connection.rollback()
    print(e)
finally:
#     연결 닫기
    if cursor: # if cursor!=None:
        cursor.close()
    if connection: # != None:
        connection.close()
```
#### insert - with문
```python
# with 문
# with 연결함수 as 변수
def insert_user(name, email, tall, birthday):
    sql = f"insert into test_user (name, email, tall, birthday) values ('{name}', '{email}', {tall}, '{birthday}')"
    with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
        with connection.cursor() as cursor:
            cnt = cursor.execute(sql)
            print(f'{cnt} 행이 insert됨')
            connection.commit()
```
### Parameterized Query
- Parameterized Query   
    - SQL 문에서 컬럼 값이 들어가는 자리에 값대신 `%s` placeholder를 사용한뒤 execute()에서 placeholder에 넣을 값을 tuple로 제공한다.   
    - query문을 쉽게 작성할 수 있는 장점이 있다.      
#### for문 사용
```python
names = ['이름1', '이름2', '이름3']
emails = ['e1@a.com', 'e2@a.com', 'e3@a.com']
talls = [192.4, 185.2, 178,6]
birthdays = ['2000-10-10', date.today(), '1999-05-07']

sql = 'insert into test_user (name, email, tall, birthday) values (%s, %s, %s, %s)'
with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
    with connection.cursor() as cursor:
        for data in zip(names, emails, talls, birthdays):
            cursor.execute(sql, data)
        
        connection.commit()
```
#### executemany() 사용
```python
datas = [
    ['name1', 'a1@a.com', 172.3, '2000-02-22'],
    ['name2', 'a2@a.com', 182.44, '2001-03-20'],
    ['name3', 'a3@a.com', 162.44, '2005-03-10'],
]

sql = 'insert into test_user (name, email, tall, birthday) values (%s, %s, %s, %s)'
with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
    with connection.cursor() as cursor:
        cursor.executemany(sql, datas)
        connection.commit()
```
### update/delete
- insert 방식과 동일
#### update
```python
 def update_user_by_id(id, name, email, tall, birthday):
    update_sql = 'update test_user set name=%s, email=%s, tall=%s, birthday=%s where id=%s'
    with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
        with connection.cursor() as cursor:
            cnt = cursor.execute(update_sql, (name, email, tall, birthday, id))
            connection.commit()
            return cnt   
```
#### delete
```python
def delete_user_by_id(id):
    delete_sql = "delete from test_user where id = %s"
    with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
        with connection.cursor() as cursor:
            cnt = cursor.execute(delete_sql, (id,))
            connection.commit()
            return cnt
```
## select (DQL-Data Query Language)
- 조회결과 조회   
    - cursor.execute("select문") 실행 후 cursor의 결과 조회 메소드(fetch메소드)를 이용해 결과를 받는다.   
- fetch메소드   
    - **fetchall()**   
        - 조회한 모든 행을을 반환   
    - **fetchmany(size=개수)**   
        - 지정한 size개수 만큼 반환   
    - **fetchone()**   
        - 조회결과 중 첫번째 행만 반환   
        - 주로 pk 동등 조건으로 조회한 경우 사용   
#### fetchall()
```python
sql = 'select * from test_user'

with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
# with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8', 
                     cursorclass=pymysql.cursors.DictCursor) as connection:   # 조회결과를 dictionary로 반환하는 커서.
                     
    with connection.cursor() as cursor:
#       select문 실행
        print(type(cursor))
        cursor.execute(sql)
#       select 결과 조회 (cursor.fetchXXX() 메소드 이용)
        result = cursor.fetchall()
        
# ((1, '홍길동', 'hong@a.com', Decimal('182.23'), datetime.date(2000, 2, 3)),
   (2, '홍길동2', 'hong2@a.com', Decimal('172.23'), datetime.date(2005, 2, 3)),
 .......)
```
#### fetchone()
```python
sql = 'select * from test_user where id = %s'  #pk 로 조회. - 조회결과 0 또는 1 행
with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
    with connection.cursor() as cursor:
        cursor.execute(sql, (10, ))  
        result = cursor.fetchone()  # 조회결과가 없으면 None을 있으면 1개의 데이터를 반환 => where 에서 pk로 조회할 경우 사용.
print(result)                       # 여러행이 조회된 경우에서 한행만(첫번째행) 반환
if result:
    print(len(result))
```
#### fetchmany()
```python
sql = "select * from test_user"
with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
    with connection.cursor() as cursor:
        cursor.execute(sql)
        result = cursor.fetchmany(size=3)
```
## cursor은 iterable타입
- for문에 select 실행한 cursor를 사용하면 조회결과를 한 행씩 조회할 수 있다.
```python
sql = "select * from test_user"
with pymysql.connect(host='127.0.0.1', port=3306, user='scott', password='tiger', db='testdb', charset='utf8') as connection:
    with connection.cursor() as cursor:
        cursor.execute(sql)
        for data in cursor:  #한번 반복시 마다 조회결과를 한행씩 반환
            print(data)
```
    
    
    
    
    
    
    
    
    
    
