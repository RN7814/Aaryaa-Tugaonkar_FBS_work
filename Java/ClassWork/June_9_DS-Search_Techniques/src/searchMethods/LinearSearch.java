package searchMethods;

public class LinearSearch {

	void Linear(int [] arr , int key)
	{
		boolean found= false;
		for(int i=0;i<arr.length ; i++)
		{
			if(key == arr[i])
				{
					System.out.println("=====Element Found====");
					found=true;
					break;
				}
		}if ( found ==  false)
		{
			System.out.println("Data Not Found");
		}
	}
}

class Test1
{
	public static void main(String[] args) {
		int[] arr = {10,20,50,4,071,95};
		LinearSearch l1 = new LinearSearch();
		l1.Linear(arr, 20);;
	}
}