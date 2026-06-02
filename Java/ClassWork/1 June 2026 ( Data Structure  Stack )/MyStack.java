package p1;
public class MyStack
{
    int top;
    int max;
    int [] arrStack;

    public MyStack(int size)
    {
        this.max = size;
        arrStack = new int[max];
        top = -1;

    }

    public void push(int data)
    {
        if(top==max-1){
            System.out.println("Stack is full");

        }else{
            arrStack[++top] = data;
        }
    }
    public void display()
    {
        for(int i = top; i>=0 ; i--)
        {
            System.out.println(" "+this.arrStack[i]);
        }
    }
    public int pop()
    {
        if(top == -1){
            System.out.println("Stack is empty");
            return -1;
        }else{
            return arrStack[top--];
        }
    }
    
    public int peek()
    {
        if(top == -1){
            System.out.println("Stack is empty");
            return -1;
        }else{
            return arrStack[top];
        }
    }
}
class Test
{
    public static void main( String []args)
    
    {
        MyStack s1 = new MyStack(5);
        s1.push(10);
        s1.push(20);
        s1.push(20);
        s1.push(20);
        s1.push(20);
        s1.push(20);

        s1.display();
    }
}