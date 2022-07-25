/* 학습 내용
 * 1. 변수, 메소드 구현 및 호출 문법 익숙해지기
 * 2. 변수 - 데이터 표현
 * 		- 실행 환경 상에서 데이터 사용을 위해서의 선행조건
 * 		- 메모리 확보
 * 			문자열 저장 가능한 메모리 - name
 * 			숫자 저장 가능한 메모리 - age
 * 		- java에선 객체 생성 우선
 * 			name, age 하는 이름과 타입이 제한된 구조의
 * 			메모리 확보
 * 
 * 		- 객체 생성 문법
 * 		People p1 = new People();
 * 		1. People 타입이 가능 사유
 * 			- 사전에 People 클래스가 존재하기 때문
 * 		2. 객체 생성 사유
 * 			- 해당 클래스가 보유한 멤버 즉 데이터 사용을 위한 메모리 생성
 * 		3. p1이 실제 보유한 데이터
 * 			- 생성된 People 객체의 주소값
 * 		4. p1 변수
 * 			- 참조 변수(reference 변수)
 * 			- 해당 변수로 객체가 보유한 모든 멤버들 access 호출 가능
 * 
 * 
 * 3. 메소드 - 기능 구현
 * 
 */

package step01;
// ctrl + shift + o 시 import 구문 자동 생성
import model.domain.People;

public class Syntax2PeopleText {

	public static void main(String[] args) {
		// name과 age 정보 활용을 위한 메모리 확보
		// People 객체 생성
		People p1 = new People();
		// 시스템 메모리에 저장된 객체의 주소값 출력
		System.out.println(p1); // model.domain.People@379619aa
//		p1.name = "연아";
		p1.setAge(-30);
		p1.setAge(30);
		int age = p1.getAge();
		System.out.println(age); // 30
//		p1.age = -30;  private으로 access 불가
//		System.out.println(p1.age);
		
		
		
		
		// 시스템 메모리에 저장된 객체의 주소값 출력		
		People p2 = new People();
		System.out.println(p2); // model.domain.People@7de26db8
//		p2.name = "연아 신랑";
//		p2.age = 40;
	}

}
