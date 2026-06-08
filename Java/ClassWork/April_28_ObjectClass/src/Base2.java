
public class Base2 {
void xyz()
{
	//System.out.println("Pune");
}
}
class Derived2 extends Base2
{
	void xyz()
	{
		System.out.println("DELHI");
	}

}
 class Test1
 {
	 public static void main(String[] args) {
		Base2 b1 = new Derived2();
		b1.xyz();
		
		///// op is "DELHI" so we can say 
		/// when fuction is called by derived class's object it will give excute the Derived Fucntion 
		/// but for we also need atleast declaration of same function i base class
	}
 }