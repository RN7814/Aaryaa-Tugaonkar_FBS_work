package sorts;

public class Selection_Sort {

	public void SSort(int [] arr)
	{
		for ( int i = 0 ; i < arr.length ; i++)
		{
			int min = arr[i];
			int index = i;
			for ( int j = i+1 ; j< arr.length ; j++)
			{
				if(min > arr[j])
				{
					min = arr[j];
					index = j;
//					System.out.println("\nInside the If Block ");
//					this.display(arr);
				}
			}
			// --- TRACING BEFORE SWAP ---
			System.out.print("Pass " + i + " [Before Swap]: ");
			this.display(arr);
			System.out.println(" (Swapping " + arr[i] + " with " + arr[index] + ")");
			
			
			int temp = arr[i];
			arr[i]=arr[index];
			arr[index]=temp;
			System.out.println("Pass" + i );
			
			
			// --- TRACING AFTER SWAP ---
						System.out.print("Pass " + i + " [After Swap]:  ");
						this.display(arr);
						System.out.println("\n--------------------------------------------------");
					}
		}
	
	void display(int[] arr)
	{
		for(int i =0 ; i < arr.length ; i++)
		{
			System.out.print(arr[i] + " ");
		}
		
	}
	
}
class Test2
{
	public static void main(String[] args) {
		Selection_Sort s1= new Selection_Sort();
		int[] arr={30,1,15,48,3,9,55,26,19,2,56,87,9,99,5,2};
		
		s1.SSort(arr);
		s1.display(arr);
	}
}