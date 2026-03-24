class Q10
{

public static void main (String args[]){
	int Eng=89,Math=90,Civics=87,Hist=92,Geo=87;
	int tot=Eng+Math+Civics+Hist+Geo;
	int tem=tot*100;
	double per=tem/500 ;
	System.out.printf("Total marks of all 5 Sub is %d out of 500",tot);
	System.out.printf(" The Total in percetage %f",per);
}
}