//Question 3: Library Fine Calculation
//Problem Statement
//Design a class named LibraryUser to calculate fines for late book returns.
//Requirements
//The class should contain:
//• User name
//• Number of days the book is returned late
//• Fine charged per day (same for all users)
//• A method to calculate the total fine amount
//Fine Calculation Formula
//Total Fine = Days Late × Fine Per Day

package assignment_3;

 class LibraryUser {
	String user;
	int latedays;
	double fine;
	static double finerate;
	static
	{
		finerate = 3.5;
				
	}
	
	LibraryUser(String user, int ld ){
		this.user = user;
		this.latedays = ld;
		
	}
	void calfine(){
		this.fine = this.latedays * finerate ;
		System.out.println( this.fine + " is the fine for late days on " + this.user );
		
	}

}
class Library
{
	public static void main(String[] args) {
		LibraryUser l1 = new LibraryUser("Glitcher420",15);
		LibraryUser l2 = new LibraryUser("Swamini",20);
		
		l1.calfine();
		l2.calfine();
	}
}
