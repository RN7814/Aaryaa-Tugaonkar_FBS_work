class Q7
{

public static void main(String args[]) {
	int a=32000;
	double da,ta,hra,tot;
	if(a<=5000) {
		da=a*0.10;
		ta=a*0.20;
		hra=a*0.25;
		tot=da+ta+hra+a;
		System.out.printf("%f,%f,%f",da,ta,hra);
		System.out.printf("\nif basic salary is <=5000 then total Salary=%f",tot);
	} else {
		da=a*0.15;
		ta=a*0.25;
		hra=a*0.30;
		tot=da+ta+hra+a;
		System.out.printf("\n%f,%f,%f",da,ta,hra);
		System.out.printf("if basic salary is %d <=5000 then total Salary=%f",a,tot);
	}
}
}

	