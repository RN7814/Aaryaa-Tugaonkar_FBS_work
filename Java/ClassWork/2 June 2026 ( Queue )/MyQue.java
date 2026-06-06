

class MyQue 
{
    int front;
    int[] arrQue;
    int max;
    int end;

    public MyQue(int size)
    {
        this.max = size;
        this.arrQue = new int[this.max];
        this.front = this.end = -1;
    }

    void push( int data)
    {
        if( this.end== max-1)
        {
            System.out.print("Que is full");
        
        }else{
            if(this.front == -1)
            {
                this.front++;
            }
            this.arrQue[++end]=data;
        }
    }

    void pop()
    {
        if(this.front == -1 || this.front > this.end)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            int removed = this.arrQue[this.front++];
            System.out.println("Removed: " + removed);
            if(this.front > this.end)
            {
                this.front = this.end = -1;
            }
        }
    }

    void display()
    {
        if(this.front == -1 || this.front > this.end)
        {
            System.out.println("Queue is empty");
            return;
        }

        for(int i = this.front; i <= this.end; i++)
        {
            System.out.print(" " + this.arrQue[i]);
        }
        System.out.println();
    }
}

class Test
{
    public static void main(String[] args)
    
    {
        MyQue s1 = new MyQue(5);
        s1.push(10);
        s1.push(05);
        s1.push(30);
    
        s1.display();

        s1.pop();
        s1.display();

        s1.pop();
        s1.display();

        
    }
}

