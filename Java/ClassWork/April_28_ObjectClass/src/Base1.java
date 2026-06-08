
public class Base1 {
void xyz()
{
	System.out.println("Mumbai");
}
}
class Derived extends Base1
{

}
 class Test
 {
	 public static void main(String[] args) {
		Base1 b1 = new Derived();
		b1.xyz();
		
		///// op is "mumbai" so we can say 
		/// when fuction is called by derived class's object it will give priorty to Base class Fucntion as it'
		/// does not have the same fucntion of its own.
	}
 }