package sorts;

public class BubbleSort {
	public void sort(int[]arr)
	{
		for(int i=0 ; i<arr.length -1  ; i++)
		{
			for(int j=0 ; j < arr.length -i -1 ; j++)
			{
				if(arr[j] > arr[j+1])
				{
					int temp=arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=temp;
				}
			}
		}
		this.display(arr);
	}
		 void display(int[] brr)
		{
			for(int i=0; i < brr.length ;i++)
			{
				System.out.print(brr[i] + " -> " );
			}
		}
}

class Test
{ 
	public static void main(String[] args) {
		int[] arr = {100000, 10, 1000, 100, 10000}; 
		BubbleSort b1 = new BubbleSort();
		b1.sort(arr);
	}
}