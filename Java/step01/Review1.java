package step01;

import model.domain.Review;

public class Review1 {

	public static void main(String[] args) {
		Review r1 = new Review();
		
		r1.setReviewName("김영준");
		String name = r1.getReviewName();
		System.out.println(name);
		
		r1.setReviewDate(20220725);
		int date = r1.getReviewDate();
		System.out.println(date);
		
	}

}
