package sorts;

public class Insert_Sort {




	void isort(int[] arr)
	{
		for (int i = 1; i < arr.length ;i++) {
		int key =  arr[i];
		int j = i-1;
		while(j>=0 && arr[j]> key )
		{
			arr[j+1] = arr[j];
			j--;
		}
		arr[j+1]=key;
		System.out.println("pass " + i  + " "   );
		
		for(int k = 0 ; k < arr.length ; k++) {
		System.out.printf(arr[k]+" | ");
		}
		System.out.println(); 
		}
	}
}
class Test3
{
	public static void main(String[] args) {
		int [] brr = {70,21,9,36,85,46,57};
		Insert_Sort s1 = new Insert_Sort();
		s1.isort(brr);
	}
}