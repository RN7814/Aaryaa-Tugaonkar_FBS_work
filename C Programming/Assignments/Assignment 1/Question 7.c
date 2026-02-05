void main() {
	int a=32000;
	float da,ta,hra,tot;
	if(a<=5000) {
		da=a*0.10;
		ta=a*0.20;
		hra=a*0.25;
		tot=da+ta+hra+a;
		printf("%f,%f,%f",da,ta,hra);
		printf("if basic salary is <=5000 then total Salary=%f",tot);
	} else {
		da=a*0.15;
		ta=a*0.25;
		hra=a*0.30;
		tot=da+ta+hra+a;
		printf("\n%f,%f,%f",da,ta,hra);
		printf("if basic salary is %d <=5000 then total Salary=%f",a,tot);
	}
}