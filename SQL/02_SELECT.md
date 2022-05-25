# SELECT
## SELECT 기본 구문 
- 연산자, 컬럼 별칭
- select 컬럼명, 컬럼명 [, .....]  => 조회할 컬럼 지정. *: 모든 컬럼   
  from  테이블명  => 조회할 테이블 지정.
#### EMP테이블의 모든 컬럼의 모든 항목을 조회
```mysql
select emp_id, 
       emp_name, 
       job, mgr_id, 
       salary, 
       comm_pct, 
       dept_name 
from   emp;
select * from emp;  -- * : 모든결과
```
#### EMP 테이블의 업무(job) 동일한 값은 하나씩만 조회되도록 처리(distinct)
```mysql
select distinct job  -- distinct: 동일한 값은 하나씩만 조회되도록 처리
from   emp;
```
#### select 컬럼명 as 별칭 (as 생략가능)
```mysql
select emp_id as 직원ID,  -- emp_id에서 조회한 결과를 직원 ID로 보여준다
       emp_name as "직원 이름", -- 별칭에 컬럼명으로 못사용하는 문자(공백)를 쓸 경우 " "로 감싼다.
	     hire_date as 입사일,
       salary 급여,
       dept_name as 소속부서
from   emp;
```
## 연산자
- 산술 연산자 
	- +, -, *, /, %, mod, div
- date/time/datetime 
  - +, - : 마지막 항목(date:일, time: 초, datetime: 초)의 값을 +/- => 계산 결과가 정수형으로 반환된다. (ex:20100104 -> 20100102)
- 여러개 값을 합쳐 문자열로 반환
	- concat(값, 값, ...) 
- cast(): 타입변환 함수
- 피연산자가 null인경우 결과는 null
- 연산은 그 컬럼의 모든 값들에 일률적으로 적용된다.
- 같은 컬럼을 여러번 조회할 수 있다.
#### 문자열 합치기(concat)
```mysql
select 20 + null;  -- null : 없는값, 모르는값
select concat("이름:", "홍길동"),
	     concat("나이:", 20);  -- 문자열과 모든 타입의 값을 합칠떄 사용
```
#### 급여 + 1000 한 값을 조회
```mysql
select emp_name,
       salary,
       salary + 1000
from   emp;
```
#### 급여(salary)을 연봉으로 조회. (곱하기 12)
```mysql
select salary * 12 as "연봉"
from   emp;
```
#### 급여 앞에 $를 붙여 조회
```mysql
select emp_name,
       concat("$",salary) as "salary"
from   emp;
```
## Where
- where 절을 이용한 행 선택
- where 컬럼 연산자 피연산자값 - 컬럼의 값들과 연산했을 때 True인 행들만 조회 -> filter
### where 연산자
|연산자|설 명|
|:--|:--|
|AND,OR|AND: 두 조건 모두 만족, OR: 둘 중 하나만 만족|
|=,<>,!=,<,>,>=,<=|= : 같은것을 조회, != : 같지않은것을 조회, > : 큰값 조회, < : 작은값 조회|
|BETWEEN a AND b|a와 b사이의 데이터를 조회(a,b) 값 포함|
|IN (list)|list의 값 중 어느 하나와 일치하는 데이터를 조회|
|LIKE|문자 형태로 일치하는 데이터를 조회(%, _ 사용)|
|IS NULL|NULL값을 가진 데이터를 조회|
#### 직원_ID가 110인 직원의 이름과 부서명을 조회( = )
```mysql
select emp_name,
       dept_name
from   emp
where  emp_id = 110;
```
#### 'Sales' 부서에 속하지 않은 직원들의, 이름, 부서명을 조회( != )
```mysql
select emp_id,
	     emp_name,
       dept_name
from   emp
where  dept_name != "Sales";
-- where dept_name <> "Sales" , <> = (!=)
```
#### 커미션비율이 0.2~0.3 사이인 직원의 ID, 이름, 커미션비율을 조회( between)
```mysql
select emp_id,
       emp_name,
       comm_pct
from   emp
where  comm_pct between 0.2 and 0.3;
```
#### 업무가 'IT_PROG' 거나 'ST_MAN' 인 직원의ID, 이름, 업무을 조회( IN )
```mysql
select emp_id,
       emp_name,
       job
from   emp
where  job in ('IT_PROG','ST_MAN');
```
#### 직원 이름이 S로 시작하는 직원의ID, 이름을 조회( like )
```mysql
select emp_id,
	     emp_name
from   emp
where  emp_name like 'S%';  -- like: 문자열의 부분일치 비교연산, %: 0글자 이상
```
#### 직원 이름의 세 번째 문자가 “e”인 모든 사원의 이름을 조회( like )
```mysql
select emp_name
from   emp
where  emp_name like '___e%';  -- _ : 한 글자를 나타낸다
```
#### 직원의 이름에 '%' 가 들어가는 직원의ID, 직원이름 조회( like )
```mysql
/* 
패턴문자를 조회조건에서 사용해야 하는 경우 escape 구문을 이용해 
패턴문자를 검색문자로 표시하는 특수문자를 지정한다
'%X_X%' : _에는 어떤 숫자가 와도 상관이 없다
*/
select emp_id,
       emp_name
from   emp
where emp_name like '%#%' escape '#';
```
#### 부서명이 null인 직원의ID, 이름, 부서명을 조회( NULL )
```mysql
select emp_id,
       emp_name
from   emp
where  dept_name is null;
```
#### 2007년 이후 입사한 직원들의, 이름, 입사일을 조회( year )
```mysql
-- date/datatime에서 년도만 추출: year(값). ex) year('2020-10-10') => 2020
select emp_id,
       emp_name,
       hire_date,
       hire_date
from   emp
where year(hire_date) >= 2007;
```
## WHERE 조건이 여러개인 경우
- AND OR 사용
- 참 and 참 -> 참: 조회 결과 행
- 거짓 or 거짓 -> 거짓: 조회 결과 행이 아님
- 연산 우선순위 : and > or   
   - where 조건1 and 조건2 or 조건3   
      1.조건 1 and 조건2   
      2. 1결과 or 조건3
   - or를 먼저 하려면 where 조건1 and (조건2 or 조건3)
#### 업무가 'SA_REP' 이고 급여가 $9,000인 직원의 직원의ID, 이름, 업무, 급여를 조회
```mysql
select emp_id,
       emp_name,
       job,
       salary
from   emp
where  job = 'SA_REP'
and    salary = 9000;
```
#### 업무가 'FI_ACCOUNT' 거나 급여가 $8,000 이상인 직원의ID, 이름, 업무, 급여를 조회.
```mysql
select emp_id,
       emp_name,
       job,
       salary
from   emp
where  job = 'FI_ACCOUNT'
or     salary >= 8000;
```
#### 부서가 'Sales'이고 업무(job)가 'SA_MAN'이고 급여가 $13,000 이하인 직원의ID, 이름, 업무, 급여, 부서를 조회
```mysql
select emp_id,
       emp_name,
       job,
       salary
       dept_name
from   emp
where  dept_name = 'Sales'
and    job = 'SA_MAN'
and    salary <= 13000;
```
#### 업무에 'MAN'이 들어가는 직원들 중 급여가 $10,000 이하이 거나 2008년 이후 입사한 직원의ID, 이름, 업무, 입사일, 급여를 조회
```mysql
select emp_id,
       emp_name,
	     job,
       hire_date,
       salary
from   emp
where  job like '%MAN%' 
and    (salary <= 10000 
or     year(hire_date) <= 2008);
/* ()를 묶지 않으면 and가 우선이 되어서    
   (job like '%MAN%' and salary <= 1000) 조건을 만족하거나    
   (year(hire_date) >= 2008) 조건을 만족하는 행을 선택하게 된다.   
*/
```
## ORDER BY
- order by절은 select문의 마지막에 온다
- order by 정렬기준컬럼 정렬방식 [, ...]
    - 정렬기준컬럼 지정 단위: 컬럼이름, 컬럼의순번(select절의 선언 순서), 별칭(alias) 
      select salary 급여, hire_date from emp ...
	    에서 salary 컬럼 기준 정렬을 설정할 경우. 
      order by salary 또는 1 또는 급여
    - 정렬방식
        - ASC : 오름차순, 기본방식(생략가능)
        - DESC : 내림차순
- 문자열 오름차순 : 숫자 -> 대문자 -> 소문자 -> 한글      
  Date 오름차순 : 과거 -> 미래
  null 오름차순 : 맨처음에 나온다.   
- order by salary asc, emp_id desc
  - salary로 전체 정렬을 하고 salary가 같은 행은 emp_id로 정렬.
#### 직원들의 전체 정보를 직원 ID가 큰 순서대로 정렬해 조회( desc )
```mysql
select * from emp
order by emp_id desc;
```
#### 부서명을 부서명의 오름차순으로 정렬해 조회하시오( asc )
```mysql
select * 
from   emp
order by dept_name;
```
#### EMP 테이블에서 ID, 이름, 급여, 입사일을 급여 오름차순으로 정렬하고 급여가 같은 경우는 먼저 입사한 순서로 정렬.
```mysql
select emp_id,
       emp_name,
       salary,
       hire_date
from   emp
order by salary, hire_date;
```
