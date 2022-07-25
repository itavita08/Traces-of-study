package step01;

import model.domain.car;

public class Syntax3CarTest {

	public static void main(String[] args) {
		// 객체 생성 / c1: 객체주소값 보유한 참조 변수
		// 멤버 변수들로만 객체 생성, 즉 메모리 확보
		// c1 참조 타입 변수, 로컬 변수
		car c1 = new car();
		c1.setCarName("그랜져");  // 로컬 변수는 carName 변수가 메모리에 생성
		String car = c1.getCarName();
		System.out.println(car);
		c1.print();
		

	}

}
