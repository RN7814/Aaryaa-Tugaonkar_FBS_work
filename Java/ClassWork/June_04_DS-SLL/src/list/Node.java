package list;
import java.util.*;
public class Node {
	int data;
	Node next;
	
	Node(int data){
		this.data = data;
	}
}
class SLL
{
	Node start;
	
	void add(int data)
	{
		Node nn = new Node(data);
		if(this.start==null)
		{
			this.start=nn;
		}else {
			Node end = start;
			while (end.next != null)
			{
				end=end.next;
			}
			end.next=nn;
			this.display();
		}
	}
	boolean search(int data)
	{
		Node temp = start;
		int count=0;
		 while (temp != null)
		    {
		        if (temp.data == data)
		        {
		            System.out.println("Data Found at no " + count +" Postion");
		            return true;
		        }
		        count++;
		        temp = temp.next; 
		    }
		    
		    System.out.println("Data Not Found");
		    return false;
		
	}
	void insertAtBeg(int data)
	{
		//create new node 
		Node newnode = new Node(data);
		newnode.next=start;
		start=newnode;
		this.display();
	}
	void display()
	{
		Node temp = start;
		if(start==null)
		{
			System.out.println("Single Linked List is Empty");
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
			return;
		}else {
			System.out.println(start.data + " Deleted");
			start=start.next;
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
			return;
		}else
		{
			Node temp = start;
			while(temp.next.next != null)
			{
				
				temp= temp.next;
			}
			System.out.println(temp.data + " Deleted");
			temp.next = null; // temp.next= temp.next.next;
			this.display();
		}
	}
	
}
class Test
{
	public static void main(String[] args) {
		SLL list1 = new SLL();
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
	            System.out.println("\nSelect Operation To do ON Single Linked List : ");
	            System.out.println("1 : Add the Value At The End");
	            System.out.println("2 : Add the Value At The Begining");
	            System.out.println("3 : Search the Data :");
	            System.out.println("4 : Delete the Data at The Begining :");
	            System.out.println("5 : Delete the Data at The End :");
	            System.out.println("6 : Display the values");
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
	            }else if(input == 3) {
	            	System.out.printf("Enter the Data to Search : ");
	            	int value = sc.nextInt();
	            	list1.search(value);
	            }else if( input == 4)
	            {
	            	System.out.println("Data deleted at Start.");
	            	list1.DeleteAtStart();
	            }else if( input == 5)
	            {
	            	System.out.println("Data Deleted and End.");
	            	list1.DeleteAtEnd();
	            }
	            else if(input == 6) {
	                list1.display();
	            } 
	            else if(input != 0) {
	                System.out.println("Invalid Choice! Please try again.");
	            }
	            
	        } while(input != 0);

	        System.out.println("Exited successfully.");
	        sc.close();
	    }

	}

