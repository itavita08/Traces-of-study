// program 시작하는 방식 단순 학습
/*step01 디렉토리 하단에 Syntax1.java로 저장한 형식
 * 
 * 1. 컴파일 명령어
 * - 이클립스에선 정상 저장
 * > javac Syntax1.java
 * 
 * 정상 컴파일시에 Suntax1.class라는 bytecode 자동 생성
 * 
 * 2. 실행 명령어
 * - 실행 단축키등 사용: F11 + cntl
 * >java syntax1
 * 
 */
package step01;

import org.junit.Test;

public class Syntax1 {

//	public static void main(String[] args) {
	
	// main 특별한 정해진 메소드 없이도 실행 가능하게하는 에노테이션
	@Test
	public void main() {
		System.out.println("콘솔창에 출력");
	}

}
