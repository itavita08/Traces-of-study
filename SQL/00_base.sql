# 주석
-- 주석
/*
블럭주석
control + / : 커서 위치에 주석 생성
*/
show databases;  -- ; : 명령어 종료, 실행 : control + enter
use mysql;  
# 데이터베이스를 생성
create database hr;
# 데이터베이스 확인
show databases;  -- mysql 명령어
# 데이터베이스 삭제
drop database hr;  -- mysql 명령어
# hr 데이터베이스를 사용
use hr;  -- mysql 명령어

# 계정 생성 - 계정:scott, 비밀번호:tiger
create user scott@localhost identified by 'tiger';  -- 로컬에서 접속할 수 있는 계정
create user scott@'%' identified by 'tiger';  -- 원격 접속 계정
# scott 계정에 모든 권한을 부여
grant all privileges on *.* to scott@localhost;
grant all privileges on *.* to scott@'%';

use mysql;
select host, user from user;  -- 계정이 등록되어있는지 확인

show tables;
