## 조인(JOIN)
- 2개 이상의 테이블에 있는 컬럼들을 합쳐서 가상의 테이블을 만들어 조회하는 방식을 말한다.
 	- 소스테이블 : 내가 먼저 읽어야 한다고 생각하는 테이블
	- 타겟테이블 : 소스를 읽은 후 소스에 조인할 대상이 되는 테이블
 
- 각 테이블을 어떻게 합칠지를 표현하는 것을 조인 연산이라고 한다.
    - 조인 연산에 따른 조인종류
        - Equi join , non-equi join
- 조인의 종류
    - Inner Join 
        - 양쪽 테이블에서 조인 조건을 만족하는 행들만 합친다. 
    - Outer Join
        - 한쪽 테이블의 행들을 모두 사용하고 다른 쪽 테이블은 조인 조건을 만족하는 행만 합친다. 조인조건을 만족하는 행이 없는 경우 NULL을 합친다.
        - 종류 : Left Outer Join,  Right Outer Join, Full Outer Join
    - Cross Join
        - 두 테이블의 곱집합을 반환한다. 
### 크로스조인
- SELECT * 
  FROM t1 CROSS JOIN t2;
#### **INNER JOIN** 직원의 ID가 100인 직원의 직원_ID, 이름, 입사년도, 소속부서이름을 조회
```mysql
select  e.emp_id, 
		    e.emp_name,
        e.hire_date,
        d.dept_name
from	  emp e inner join dept d on e.dept_id = d.dept_id  -- inner은 생략 가능하다.
where   e.emp_id = 100 ;
```
#### **INNER JOIN** 직원_ID, 이름, 급여, 담당업무명, 소속부서이름을 조회
```mqsql
select  e.emp_id,
		    e.emp_name,
        e.salary,
        j.job_title,
        d.dept_name
from	  emp e join job j on e.job_id = j.job_id 
			        join dept d on e.dept_id = d.dept_id
order by e.emp_id;
```
### self 조인
- 물리적으로 하나의 테이블을 두개의 테이블처럼 조인하는 것
#### 직원 ID가 101인 직원의 직원의 ID(emp.emp_id), 이름(emp.emp_name), 상사이름(emp.emp_name)을 조회
```mysql
select  emp_id,
		    emp_name, 
        mgr_id
from    emp  -- 부하직원 
where   emp_id = 101;
-- 1. 부하직원 정보 101    Neena   100

select  emp_name
from    emp -- 상사직원
where   emp_id = 100;
-- 2. 상사직원 정보  Steven

-- 1번과 2번을 합친것이 self join
select  e.emp_id,
		    e.emp_name "직원이름",
        m.emp_name "상사이름"
from    emp e  join emp m  on e.mgr_id = m.emp_id
where   e.emp_id = 101;
-- 101    Neena   Steven
```
### 외부조인
- 불충분 조인
    - 조인 연산 조건을 만족하지 않는 행도 포함해서 합친다
- 종류
  - left  outer join: 구문상 소스 테이블이 왼쪽
  - right outer join: 구문상 소스 테이블이 오른쪽
  - full outer join:  둘다 소스 테이블 (Mysql은 지원하지 않는다. - union 연산을 이용해서 구현)

- 구문
  - from 테이블a [LEFT | RIGHT] OUTER JOIN 테이블b ON 조인조건
  - OUTER는 생략 가능.


join: inner join   
left join : left outer join   
right join: right outer join   
##### 직원의 id(emp.emp_id), 이름(emp.emp_name), 급여(emp.salary), 부서명(dept.dept_name), 부서위치(dept.loc)를 조회   
##### 부서가 없는 직원의 정보도 나오도록 조회. dept_name의 내림차순으로 정렬한다
```mysql
select   e.emp_id,
		     e.emp_name,
         e.salary,
         d.dept_name,
         d.loc
from  dept d right join emp e on d.dept_id = e.dept_id
order by d.dept_name desc;
-- emp: source, dept: target
```
