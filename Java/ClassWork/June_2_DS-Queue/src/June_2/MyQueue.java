package June_2;


 class MyQue {
	int front;
	int max;
	int[] arrQue;
	int end;

	public MyQue(int size){
		this.max= size;
		this.arrQue = new int[max];
		this.front = this.end = -1;
		
	}
	
	public void push(int data)
	{
		if ( this.end == max-1)
		{
			System.out.println("\nQueue is Full");
		}else 
		{
			if(this.front==-1)
			{
				this.front++;
			}
			this.arrQue[++end]=data;
		}
	}
	
	public int pop()
	{
		if(this.front==-1)
		{
			System.out.println("Queue is Empty");
			return -1;
		}else
		{
			return arrQue[front++]=0; 
		}
	}
	
	public void display()
	{
		for(int i=this.front; i<=end ; i++)
		{
			System.out.printf(" "+ this.arrQue[i]);
		}
	}
	
}

class Main1
{
	public static void main(String[] args) {
		MyQue a1 = new MyQue(5);
		
		a1.push(10);
		a1.push(20);
		a1.push(30);
		a1.push(40);

		a1.display();
		System.out.println("\n");
		a1.pop();
		a1.display();
		System.out.println("\n");

		a1.pop();
		a1.display();
		System.out.println("\n");

	}
}
