/* 이름, 나이 정보 관련된 정보
 * 
 */

package model.domain;

public class People {
	// 이름
	private String name;
	// 나이(- + 32bit 정수)
	// private: 외부 클래스에서 호출 불가
	private int age;


// age 변수값 수정하는 공개된 메소드
// 1살 이상이여야 함
/*
 * 하나의 숫자를 받아서
 * 0을 초과한 양수인지 확인 후에
 * 유효한 경우 age변수에 대입
 * 무효한 경우 오류 메세지 출력
 */
	public void setAge(int newAge) {
		if(newAge > 0) {
			age = newAge;
		}else {
			System.out.println("무효");
		}
	}
	// private로 외부에 은닉해 놓은 변수값 반환
	public int getAge() {
		return age;
	}
	
	// name 변수값 수정
	// 멤버변수와 로컬 변수명이 동일한 경우 구분을 위해 this 키워드 필수
	public void setName(String name) {
		this.name = name; 
	}
	// name 변수값 반환
	public String getName() {
		return name;
	}
}