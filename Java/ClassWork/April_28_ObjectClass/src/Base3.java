
public class Base3 {

	void xyz() {
		
	}
}
class Derived3 extends Base3
{
	void xyz()
	{
		System.out.println("Solapur");
	}
}
 class Test3
 {
	 public static void main(String[] args) {
		Base3 b1 = new Derived3();
		b1.xyz();
		
			/// OP IS "COMPILATION ERROR" so we can say 
			/// when a function is called using a Base class reference, Java checks the 
			/// Base class for the function declaration during compilation. Since the Base 
			/// class does not have even a declaration of the function, the code fails to compile, 
			/// even though the underlying object has the method.
		
	}
 }   