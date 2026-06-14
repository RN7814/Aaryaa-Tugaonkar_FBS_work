package searchMethods;

public class BinarySearch {

	public void binary(int [] arr , int key)
	{
		int start = 0;
		int end = arr.length-1;
			while(start<=end){

				int mid = start + end /2;
				if(arr[mid] == key)
				{
					System.out.println("======Element Found========");
					return;
				}
				else if (key > arr[mid])
				{
					start = mid +1;
				}else {
					end = mid -1;	
				}
				System.out.println("Element Not Found");
				return;
			}
	}
}
class Test
{
	public static void main(String[] args) {
		int [] trail = {10,20,30,40,50};
		BinarySearch b1 = new BinarySearch();
		b1.binary(trail, 4);
		
	}
}
