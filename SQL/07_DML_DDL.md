# DML
---
## COMMIT
- select @@autocommit;
  - autocommit : sql문 실행시마다 커밋처리(최종적용)
  - @@autocommit 값 : 1이면 autocommit
  - autocommit 해제 -> @@autocommit 값을 0으로 설정
- commit: 최종 적용
- rollback: commit 적용 전, 변경 전 상태로 돌아갈 수 있다
## INSERT
- 구문
  - 한행추가 :
    - INSERT INTO 테이블명 (컬럼 [, 컬럼]) VALUES (값 [, 값[])
    - 모든 컬럼에 값을 넣을 경우 컬럼 지정구문은 생략 할 수 있다.

  - 조회결과(select)를 INSERT 하기 (subquery 이용)   
    - INSERT INTO 테이블명 (컬럼 [, 컬럼])  SELECT 구문   
	 - INSERT할 컬럼과 조회한(subquery) 컬럼의 개수와 타입이 맞아야 한다.   
	 - 모든 컬럼에 다 넣을 경우 컬럼 설정은 생략할 수 있다.   
#### insert 구문
```mysql
insert into   emp   (emp_id,emp_name,job,hire_date) values (301,'유관순','AC_COUNT','2020-10-20')
--         테이블 명            컬럼 명                               값      
```
## UPDATE
- 테이블의 컬럼의 값을 수정
- UPDATE 테이블명   SET    변경할 컬럼 = 변경할 값  [, 변경할 컬럼 = 변경할 값]   [WHERE 제약조건]
  - UPDATE: 변경할 테이블 지정
  - SET: 변경할 컬럼과 값을 지정
  - WHERE: 변경할 행을 선택. 
#### update 구문
```mysql
update emp  -- table 선택
set salary = 5000  -- 어떤 컬럼의 값을 어떤 값으로 바꿀지 설정
where emp_id = 200;  -- 변경할 행을 선택
```
## DELETE
- 테이블의 행을 삭제
- 구문 
  - DELETE FROM 테이블명 [WHERE 제약조건]
  - WHERE: 삭제할 행을 선택
#### delete 구문(담당 업무(emp.job_id)가 'SA_MAN'이고 급여(emp.salary) 가 12000 미만인 직원들을 삭제)
```mysql
delete from emp where job_id = 'SA_MAN'
                and salary < 12000;
```
---
# DDL
---
## 테이블 생성
- 구문: create table 테이블 이름( 컬럼 설정 )
  - 컬럼 설정
    - 컬럼명   데이터타입  [default 값]  [제약조건] 
    - 데이터타입
    - default : 기본값. 값을 입력하지 않을 때 넣어줄 기본값.
  - 제약 조건 설정
    - primary key (PK): 행식별 컬럼. NOT NULL, 유일값(Unique)
    - unique Key (uk) : 유일값을 가지는 컬럼. null을 가질 수있다.
    - not null (nn)   : 값이 없어서는 안되는 컬럼.
    - check key (ck)  : 컬럼에 들어갈 수 있는 값의 조건을 직접 설정.
    - foreign key (fk): 다른 테이블의 primary key 컬럼의 값만 가질 수 있는 컬럼.    
                        -다른 테이블을 참조할 때 사용하는 컬럼.  

- 레벨 설정  
  - 컬럼 레벨 설정
    - 컬럼 설정에 같이 설정
  - 테이블 레벨 설정
    - 컬럼 설정 뒤에 따로 설정
  - 기본 문법 : constraint 제약조건이름 제약조건타입(지정할컬럼명)  (PK, UK는 이름 없이 바로 설정 가능-컬럼레벨에서)
  - 테이블 제약 조건 조회
    - select * from information_schema.table_constraints;
#### 테이블의 제약조건 조회
```mysql
select * from information_schema.table_constraints
where table_schema='testdb';
```
#### 테이블 생성(컬럼 레벨 설정)
```mysql
create table parent_tb(
	  no int primary key,  -- PK 제약조건 컬럼레벨, 테이블레벨 정의
    name varchar(50) not null,  -- not null 제약조건은 컬럼레벨로 지정
	  join_date timestamp default current_timestamp,  -- 날짜/시간 타입의 컬럼에 insert 시점의 일시를 기본값으로 넣을 경우 
                                                    --    -> type: timestamp, default 값: current_timestamp
    email varchar(100) unique,  -- UK 제약조건, 컬럼레벨, 테이블레벨 지정 
    gender char(1) not null check(gender in ('M','F')),  -- ck, 컬럼레벨, 테이블레벨 지정, check(컬럼명 조건)
    age int check(age between 10 and 30)  -- age 컬럼은 10 ~ 30 사이의 정수만 가능
);
```
#### 테이블 생성(테이블 레벨 설정)
```mysql
create table child_tb(
	no int auto_increment,  -- auto_increment는 자동증가 컬럼, insert할 시 1씩 증가하는 정수를 가진다 PK
    jumin_num char(14),  -- UK
    age int not null,  -- CK
    p_no int not null,  -- FK (parent_tb.no 참조)
    constraint child_tb_no_pk primary key(no),
    constraint child_tb_jumin_num_uk unique(jumin_num),
    constraint child_tb_age_ck check(age >0 and age <= 100),
    constraint child_tb_p_no_fk foreign key(p_no) references parent_tb(no) on delete cascade  -- 참조행이 삭제되면 데이터를 같이 삭제 
);
```
## 테이블 삭제
- 구문: drop table 테이블이름;
- 제약조건 해제
   SET FOREIGN_KEY_CHECKS = 0;
- 제약조건 설정
   SET FOREIGN_KEY_CHECKS = 1; 
#### 테이블 삭제 구문
```mysql
DROP TABLE IF EXISTS book;  -- 테이블 생성 전에 사용하여 중복되는 테이블이 있을 경우 삭제하고 다시 생성할 수 있도록 한다.
create table book(
	isbn varchar(13) primary key,
    title varchar(50) not null,
    .....);
```
## ALTER
- 테이블 수정
- 컬럼 관련 수정

	- 컬럼 추가
	  -ALTER TABLE 테이블이름 ADD COLUMN 추가할 컬럼설정 [,ADD COLUMN 추가할 컬럼설정]
  
	- 컬럼 수정
	  -ALTER TABLE 테이블이름 MODIFY COLUMN 수정할컬럼명 변경설정 [, MODIFY COLUMN 수정할컬럼명  변경설정]
		  - 숫자/문자열 컬럼은 크기를 늘릴 수 있다.
		  - 크기를 줄일 수 있는 경우 : 열에 값이 없거나 모든 값이 줄이려는 크기보다 작은 경우
		  - 데이터가 모두 NULL이면 데이터타입을 변경할 수 있다. (단 CHAR<->VARCHAR 는 가능.)

	- 컬럼 삭제	
	   -ALTER TABLE 테이블이름 DROP COLUMN 컬럼이름 [CASCADE CONSTRAINTS]
		   - CASCADE CONSTRAINTS : 삭제하는 컬럼이 Primary Key인 경우 그 컬럼을 참조하는 다른 테이블의 Foreign key 설정을 모두 삭제한다.
		   - 한번에 하나의 컬럼만 삭제 가능.

	- 컬럼 이름 바꾸기
	    -ALTER TABLE 테이블이름 RENAME COLUMN 원래이름 TO 바꿀이름;
- 제약 조건 관련 수정
	- 제약조건 추가
	  - ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건 설정

	- 제약조건 삭제
	  - ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건이름
	  - PRIMARY KEY 제거: ALTER TABLE 테이블명 DROP PRIMARY KEY    
		- CASECADE : 제거하는 Primary Key를 Foreign key 가진 다른 테이블의 Foreign key 설정을 모두 삭제한다.

	- NOT NULL <-> NULL 변환은 컬럼 수정을 통해 한다.
	- ALTER TABLE 테이블명 MODIFY COLUMN 컬럼명 타입 NOT NULL  
	- ALTER TABLE 테이블명 MODIFY COLUMN 컬럼명 NULL
#### 기존 테이블 복사
```mysql
-- not null을 제외한 제약 조건은 copy가 안됨
create table 이름
as
select 문 -- subquery

create table cust
as
select * from customers where 1 = 0;  -- 테이블의 구조만 복사(데이터는 복사하지 않는다)
```
#### 컬럼 추가/수정/삭제
```mysql
alter table cust add column age int default 0 not null;  -- 추가 

alter table cust modify column age tinyint not null;  -- not null을 지정하지 않으면 허용으로 변경된다

alter table cust rename column age to cust_age;  -- age => cust_age  -- 이름변경

alter table emp2 drop constraint emp2_email_uk;  -- 삭제
```
#### 제약조건 추가/삭제
```mysql
alter table cust add constraint cust_pk primary key(cust_id);  -- 추가(PK)
alter table ord add constraint ord_cust_fk foreign key(cust_id) references cust(cust_id);  -- 추가(FK)

alter table ord drop constraint ord_cust_fk;  -- 삭제
```
---
