package queues;

 class CircularQueue {
	 int front;
	 int size;
	 int rear;
	 int[] cqueue;
	 
	 CircularQueue(int size){
		 this.size= size;
		 this.front = this.rear= -1;
		 this.cqueue = new int[size];
	 }
	 
	 boolean isEmpty()
	 {
	     if (front == -1)
	     {
	         return true;
	     } else {
	         return false;
	     }	 
	 }
	 boolean isFull()
	 {
		 if((front == 0 && rear== size-1)|| (front == rear+1))
		 {
			 return true;
			 
		 }else {
			 return false;
		 }
	 }
	 void push(int data) {
		 if(isFull())
		 {
			 System.out.println("Queue is full");
			 return;
		 }else if(isEmpty())
		 {
			 front=rear=0;
		 }else if(rear==size-1) {
			 rear=0;
		 }
		 else {
			 rear++;
		 }
		 this.cqueue[rear]=data;
	 }
	 void pop()
	 {
		 if(isEmpty()) {
			 System.out.println("Queue is Empty");
			 
		 }else {
			 int ele = this.cqueue[front];
			 if(front ==  rear) {
				 front= rear=-1;
				 
			 }else if(front == size -1)
			 {
				 front=0;
			 }else {
				 front ++;
				 
			 }
			 System.out.println(ele + " is the deleted element from queue");;
			 
		 }
	 }
	 
	 void display()
	 {

		 if (isEmpty())
		 {
			 System.out.println("Queue is Empty nothing to display");
		 }else {
			 int i =  front;
			 while(i != rear)
			 {
				 System.out.println(this.cqueue[i] + " \n");
				 if(i == size -1)
				 {
					 i=0;
				 }else {
					 i++;
				 }
			 }
			 System.out.println(this.cqueue[i] + " \n");
			 }
		 }
}
 

 class Test
 {
	 public static void main(String[] args) {
		CircularQueue c1 = new CircularQueue(5);
		
		c1.push(10);
		c1.push(20);
		c1.display();
		c1.push(50);
		c1.push(80);
		c1.display();
		////////////////////////////
		
		c1.pop();
		c1.pop();
		c1.display();
		
		c1.push(12);
		c1.push(25);
		c1.display();
		
	}
 }