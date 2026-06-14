package doubleList;

import java.util.*;
class Node {
	int data;
	Node prev;
	Node next;
	
	Node(int data){
		this.data = data;
	}
}
public class Dll 
	{
		Node start;
		int count;
		
		void add(int data)
		{
			Node nn = new Node(data);
			if(this.start==null)
			{
				this.start=nn;
				count++;
				return;
				
			}else {
				Node end = start;
				while (end.next != null)
				{
					end=end.next;
				}
				
				end.next=nn;
				count++;
				nn.prev = end;
				
				this.display();
			}
		}
		boolean search(int data)
		{
			Node temp = start;
			int count1=0;
			 while (temp != null)
			    {
			        if (temp.data == data)
			        {
			            System.out.println("Data Found at no " + count1 +" Postion");
			            return true;
			        }
			        count1++;
			        temp = temp.next; 
			    }
			    
			    System.out.println("Data Not Found");
			    return false;
			
		}
		void insertAtBeg(int data)
		{
			Node nn = new Node(data);
			if(start == null)
			{
				start = nn;
				count++;
				return;
			}else
			{
				if(start.prev == null)
				{
					start.prev = nn;
					nn.next = start;
					count++;
				    start=nn;	
				}
			}
			this.display();
		}
		void display()
		{
			Node temp = start;
			if(start==null)
			{
				System.out.println("Double Linked List is Empty");
				return;
			}
			while(temp.next != null)
			{
				System.out.print(temp.data + "-->");
				temp=temp.next;
			}
		System.out.println(temp.data);
		
		}
		void DeleteAtStart()
		{
			if(start==null)
			{
				System.out.println("List is Empty");
				return;
			}
			if(start.next == null)
			{
				System.out.println(start.data + " Deleted");
				start=null;
				count--;
				return;
			}else {
				System.out.println(start.data + " Deleted");
				start=start.next;
				start.prev =null;
				count--;
				this.display();
				return;
			}
			
		}
		void DeleteAtEnd()
		{

			if(start == null)
			{
				System.out.println("List is Empty");
				return;		
			}
			if(start.next == null)
			{
				System.out.println(start.data + " Deleted");
				start = null;
				count--;
				return;
			}else
			{
				Node temp = start;
				while(temp.next.next != null)
				{
					
					temp= temp.next;
				}
				System.out.println(temp.next.data + " Deleted");
				temp.next.prev = null; // temp.next= temp.next.next;
				temp.next=null;
				count--;
				this.display();
			}
		}
			void SortedAdd(int data)
			{
				Node nn = new Node(data);
				Node temp = start;
				if(this.start==null)
				{
					this.start=nn;
					count++;
					return;
					
				}else if (data < this.start.data)
			    {
			        nn.next = this.start;
			        this.start.prev = nn;
			        this.start = nn;
			        count++;
			        return;
			    }else
			  {
			    
			    while (temp.next != null && temp.next.data < data)
			    {
			        temp = temp.next;
			    }

			    // Insert the new node right after 'temp'
			    nn.next = temp.next;
			    nn.prev = temp;
			    count++;

			    if (temp.next != null)
			    	{
			        // Inserting into the middle of the list
			        temp.next.prev = nn;
			
			    	}
			    
			    temp.next = nn;
			    
		  }
					
		}
			void InsertAtPos(int data,int pos) {
				Node temp=start;
				Node nn =  new Node(data);
				if(start==null)
				{
					System.out.println("The List is Empty");				
					return;
				}
				if(pos == 1)
				{
					this.insertAtBeg(data);
					return;
				}else {
					for(int i= 1 ; i <= pos && temp!=null;i++)
					{
						temp = temp.next;
					}
					if(temp==null)
					{
						System.out.println("Invalid Position");
						return;
					}
					nn.next = temp.next;
			        nn.prev = temp;
			        if (temp.next != null) {
			            temp.next.prev = nn;
			        }
			        temp.next = nn;
			        count++;
			        this.display();
			    }
			
			}
			void DeleteAll()
			{
				while (start != null)
						{
					this.DeleteAtEnd();
					this.display();
						}
			}
				
}	
	class Test
	{
		public static void main(String[] args) {
			Dll list1 = new Dll();
			Scanner sc = new Scanner(System.in);
			list1.add(20);
			list1.add(30);
			list1.add(40);
			list1.add(80);
			
			list1.display();
			
			list1.search(80);
			
			list1.insertAtBeg(10);
			list1.insertAtBeg(00);
			list1.display();
			int input ;
			do {
	            System.out.println("\nSelect Operation To do ON Doubly Linked List : ");
	            System.out.println("1 : Add the Value At The End");
	            System.out.println("2 : Add the Value At The Beginning");
	            System.out.println("3 : Search the Data");
	            System.out.println("4 : Delete the Data at The Beginning");
	            System.out.println("5 : Delete the Data at The End");
	            System.out.println("6 : Add By AUTO SORTING");
	            System.out.println("7 : Insert At Specific Position");
	            System.out.println("8 : Display the values");
	            System.out.println("9 : Clear Whole List");
	            System.out.println("0 : Exit");

	            System.out.print("Your Choice : ");
	            input = sc.nextInt();
	            
	            if(input == 1) {
	                System.out.print("Enter The Value to Add at End : ");
	                int value = sc.nextInt();
	                list1.add(value);
	            } 
	            else if(input == 2) {
	                System.out.print("Enter The Value to Add at Beginning : ");
	                int value = sc.nextInt();
	                list1.insertAtBeg(value);
	            } else if(input == 3) {
	                System.out.print("Enter the Data to Search : ");
	                int value = sc.nextInt();
	                list1.search(value);
	            } else if(input == 4) {
	                System.out.println("Data deleted at Start.");
	                list1.DeleteAtStart();
	            } else if(input == 5) {
	                System.out.println("Data Deleted at End.");
	                list1.DeleteAtEnd();
	            } else if(input == 6) {
	                System.out.print("Enter The Value to Add : ");
	                int value = sc.nextInt();
	                list1.SortedAdd(value);
	                list1.display();
	            } else if(input == 7) {
	                System.out.print("Enter The Value to Add : ");
	                int value = sc.nextInt();
	                System.out.print("Enter The Position : ");
	                int pos = sc.nextInt();
	                list1.InsertAtPos(value, pos);
	            } else if(input == 8) {
	                list1.display();
	            } else if(input == 9) {
	            	list1.DeleteAll();
	                System.out.println("List is Cleared");
	            }else if(input != 0) {
	                System.out.println("Invalid Choice! Please try again.");
	            }
	            
	        } while(input != 0);

	        System.out.println("Exited successfully.");
	        sc.close();
	    }
	}




