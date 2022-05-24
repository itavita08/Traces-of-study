# MySQL
---
## 데이터 베이스(Database)
  - 지속적으로 유지, 관리되어야하는 데이터들의 집합
### 관계형 데이터 베이스(Relational Database)
  - 행과 열로 이루어진 2차원 표 형식으로 Data를 관리하는 데이처베이스
  - 테이블간의 관계를 통해 데이터를 관리하는 방식
### 테이블(Table)
  - 데이터 베이스에서 데이터를 저장하는 단위
  - **열**(속성)과 **행**(값)의 이차원 표 형식으로 관리
---
## SQL
  - 데이터베이스에 데이터를 질의, 등록, 수정, 삭제 등을 요청하기 위한 표준언어
  - **DML**
      01. 데이터를 조작하는데 사용하는 언어
      02. Rollback을 이용해 처리 취소가능
  - **DDL**
      01. Database 스키마를 생성, 삭제, 수정하는 언어
      2. 명령어를 실행하면 Rollback 불가능
  - **DLC**
      - Data 접근을 제어하기 위한 언어
 
 ### Database 생성 및 조회
 ```mysql
 -- 데이터베이스 생성
 create database hr;
 -- 데이터베이스 조회
 show database hr;
 -- 데이터베이스 삭제
 drop database hr;
 -- 데이터베이스 실행
 use hr;
 ```
 ### 사용자 계정 생성 및 권한 설정
 ```mysql
 create user scott@localhost identified by 'tiger';  -- 로컬에서 접속할 수 있는 계정
 create user scott@'%' identified by 'tiger';  -- 원격 접속 계정
 
 -- scott 계정에 모든 권한을 부여(grant 권한 on DB.TABLE to 계정)
 grant all privileges on *.* to scott@localhost;
 grant all privileges on *.* to scott@'%';
 
 select host, user from user;  -- 생성된 계정 확인
 ```
