
package assignment_2;

public class Calculator {
	int a,b;
	double c,d;
	
//	public int getA() {
//		return a;
//	}
//	public void setA(int a) {
//		this.a = a;
//	}
//	public int getB() {
//		return b;
//	}
//	public void setB(int b) {
//		this.b = b;
//	}
//	public double getC() {
//		return c;
//	}
//	public void setC(double c) {
//		this.c = c;
//	}
//	public double getD() {
//		return d;
//	}
//	public void setD(double d) {
//		this.d = d;
//	}
	
	void Addition(int aa,int bb)
	{
		this.a=aa;
		this.b=bb;
		System.out.println("Addition = " + (this.a + this.b));
	}
	void Addition(double cc,double dd)
	{
		this.c =cc;
		this.d =dd;
		System.out.println("Addition = " + (this.c + this.d));
	}
	void Addition(int aa,double bb)
	{
		this.a=aa;
		this.c=bb;
		System.out.println("Addition = " + (this.a + this.c));
	}
	void Addition(double aa,int bb)
	{
		this.d=aa;
		this.b=bb;
		System.out.println("Addition = " + (this.d + this.b));
	}
	////////////////////////////////////////// Addition Ends here/////////////////////////////////////
	void Substract(int aa,int bb)
	{
		this.a=aa;
		this.b=bb;
		System.out.println("Substarction = " + (this.a - this.b));
	}
	void Substract(double aa,double bb)
	{
		this.c=aa;
		this.d=bb;
		System.out.println("Substarction = " + (this.c - this.d));
	}
	void Substract(int aa,double bb)
	{
		this.a=aa;
		this.c=bb;
		System.out.println("Substarction = " + (this.a - this.c));
	}
	void Substract(double aa,int bb)
	{
		this.d=aa;
		this.b=bb;
		System.out.println("Substarction = " + (this.d - this.b));
	}
	////////////////////////////////////////// SubStraction Ends here/////////////////////////////////
	void Multiply(int aa,int bb)
	{
		this.a=aa;
		this.b=bb;
		System.out.println("Multiplication = " + (this.a * this.b));
	}
	void Multiply(double aa,double bb)
	{
		this.c=aa;
		this.d=bb;
		System.out.println("Multiplication = " + (this.c * this.d));
	}
	void Multiply(int aa,double bb)
	{
		this.a=aa;
		this.c=bb;
		System.out.println("Multiplication = " + (this.a * this.c));
	}
	void Multiply(double aa,int bb)
	{
		this.d=aa;
		this.b=bb;
		System.out.println("Multiplication = " + (this.d * this.b));
	}
	////////////////////////////////////////// Multiplication ENds here///////////////////////////////
	void Divide(int aa,int bb)
	{
		this.a=aa;
		this.b=bb;
		System.out.println("Division = " + (this.a / this.b));
	}
	void Divide(double aa,double bb)
	{
		this.c=aa;
		this.d=bb;
		System.out.println("Division = " + (this.c / this.d));
	}
	void Divide(int aa,double bb)
	{
		this.a=aa;
		this.c=bb;
		System.out.println("Division = " + (this.a / this.c));
	}
	void Divide(double aa,int bb)
	{
		this.d=aa;
		this.b=bb;
		System.out.println("Division = " + (this.d / this.b));
	}
	//////////////////////////////////////// Division Ends here /////////////////////////////////////

}

class TestCal
{
	public static void main(String[] args) {
	int a=10,b=20;
	double c=5.5;
	Calculator add = new Calculator();
	add.Addition(a,b);
	add.Addition(c, c);
	add.Addition(a, c);
	add.Addition(c, b);

	Calculator sub = new Calculator();
	sub.Substract(a,b);
	sub.Substract(c, c);
	sub.Substract(a, c);
	sub.Substract(c, b);
	
	Calculator mul = new Calculator();
	mul.Multiply(a, b);
	mul.Multiply(c, c);
	mul.Multiply(a, c);
	mul.Multiply(c, b);
	
	Calculator div = new Calculator();
	div.Divide(a, b);
	div.Divide(c, c);
	div.Divide(a, c);
	div.Divide(c, b);
	
	}
}
