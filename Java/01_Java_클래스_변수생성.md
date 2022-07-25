# 학습내용

PascalCase: FirstName - class   
UPPER_SNAKE_CASE: FIRST_NAME - 상수   
camelCase: firstName - 변수, 메소드   

## 1. 변수 선언 및 호출 방법
python      
  - 이름값 저장하는 변수 선언 및 초기화   
		name = ''   
		name = ""   
		
java   
  - string name = "";   (name 변수에는 문자열(string)타입만 가능)              		
  - int age = 10;   
			
sql 
```
create table person(   
		name varchar(20),   
		age int   
		);   
```
## 2. 메소드 구현 및 호출 방법
python 
```
def getName():
	return "이름"
```
java 
```
string getName(){
	return "이름";
		}
```	
```
string getName(){   # 오류
	return 10;
		}
```
## 3. 생성자 구현 및 호출 방법
python
```
__init__():
```
		
java
```
클래스명
```

## 4. lombok을 활용한 코드 다이어트

### 1. java 프로그램 실행 방식
1. 자바에서 실행을 위한 필수 메소드   
	- public static void main(String[] args){}
	
2. 단위 test인 jUnit framework 사용시
	- main 메소드 없이도 실행 가능
	- @Test - 선언된 메소드 실행
	
3. server 즉 web
	- server와 browser로 실행
	
## 5. 문법 
1. 변수 선언
	타입 변수명;
	타입 변수명 = 값;
	
2. 객체 생성 문법
	타입 변수명 = new 생성자();   
	변수: 객체 참조 변수, 객체가 메모리에 생성되어 저장된 위치값 보유   
		- 객체가 보유한 모든 변수와 메소드 사용 가능하게 하는 변수
		
3. access modifier
	1. 기능: 접근 범위 제한
	2. 종류
		1. public
			- 외부 클래스에서 access 가능 의미
		2. private
			- 외부 클래스에선 access 불가
			
## 6. 리뷰
1. java 프로그램 개발 기준 : class 단위

2. 소스 구조
```
	package 선언구;
	import  package 활용구;
	
	public class class명{
		멤버 변수;
		메소드([argument_list]){
			//로직 구현
		}
	}		
```		
3. 객체 생성 문법
	타입 변수 = new 생성자();
	
4. 멤버 변수, 메소드 호출 하는 문법   
	1단계 : 객체 생성 필수   
	2단계 : 객체 참조 변수로 메소드나 멤머 변수 호출   
	
	
5. 명명 규칙
	1. class명 : 파스칼 케이스 권장
	2. 변수, 메소드 : 카멜 케이스 권장
	
6. 변수 
	1. 선언된 위치에 따른 변수
		1. 멤버 - 생성되는 객체를 구성하는 구성
		2. 로컬 - 메소드 호출시에만 생성 및 메소드 종료시 자동 휘발 
		
	2. 타입에 따른 변수
		1. 기본 타입
		2. 참조 타입
		
	3. 주의 사항
		멤버변수명과 로컬 변수명이 동일한 경우 구분 키워드   
		this.멤버변수 표현으로 구분
## 7. 실습
```java
package model.domain;

// @Getter  # lombok을 이용해서 메소드 생성 가능, shift + ctrl + o
// @Setter
public class Review {
	private String reviewName;  // 매개변수
	private int reviewDate;
	
	public String getReviewName() {
		return reviewName;
	}
	public void setReviewName(String reviewName) {  // reviewName = 로컬변수
		this.reviewName = reviewName;  // 매개변수 = 로컬변수
	}
	public int getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(int reviewDate) {
		if (reviewDate > 0 ) {
			this.reviewDate = reviewDate;
		}
		else {
			System.out.println("오류");  // reviewDate가 음수이면 오류 출력
		}
	}
	
	
}
```
```java
package step01;

import model.domain.Review;

public class Review1 {

	public static void main(String[] args) {
		Review r1 = new Review();  // Review 객체 생성
		
		r1.setReviewName("김영준");
		String name = r1.getReviewName();
		System.out.println(name);
		
		r1.setReviewDate(20220725);
		int date = r1.getReviewDate();
		System.out.println(date);
		
	}

}
```
### 출력
```
김영준
20220725
```
