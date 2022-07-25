package model.domain;

public class Review {
	private String reviewName;
	private int reviewDate;
	
	public String getReviewName() {
		return reviewName;
	}
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	public int getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(int reviewDate) {
		if (reviewDate > 0 ) {
			this.reviewDate = reviewDate;
		}
		else {
			System.out.println("오류");
		}
	}
	
	
}
