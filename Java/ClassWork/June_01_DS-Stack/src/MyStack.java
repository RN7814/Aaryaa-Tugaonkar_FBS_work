
public class MyStack {
	
	int top;
	int max;
	int[] arrStack;
	
	public MyStack(int size)
	{
		this.top=-1;
		this.max = size;
		arrStack = new int[max];
		
	}
	
	public void push(int data)
	{
		if(top==max-1)
		{
			System.out.println("Stack is Full");
		}else {
			arrStack[++top]=data;
		}
	}
	public int pop()
	{
		if(top==-1)
		{
			System.out.println("Stack is empty");
			return -1;

		}else {
			int poppedValue = arrStack[top]; // 1. Save the value
			arrStack[top--]=0;               // 2. Clear slot and decrement top
			return poppedValue;
		}
	}
	
	public int peep()
	{
		if(top==-1)
		{
			System.out.println("Stack is Empty");
			return -1;
		}else
		{
			return this.arrStack[top];
		}
	}
	public void display()
	{
		for(int i= top; i>=0 ;i--) {
			System.out.println(this.arrStack[i]);
		}
	}
}

class Test
{
	public static void main(String[] args) {
		MyStack s1 = new MyStack(5);
		s1.push(10);
		s1.push(20);
		s1.push(30);
		s1.push(40);
		
		s1.display();
		
		s1.pop();
		s1.display();
		
	}
}