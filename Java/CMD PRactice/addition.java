public class addition 
{
    public static void main(String[] args) 
    {
        int a = 8996;
        
        if (a % 2 == 0) 
        {
            // 1st digit from right (6)
            int temp = a % 10;          
            System.out.println(temp);   
            
            // 2nd digit from right (9)
            int b = a / 10;             
            int temp2 = b % 10;         
            System.out.println(temp2);  
            
            // 3rd digit from right (9)
            int c = b / 10;             
            int temp3 = c % 10;
            System.out.println(temp3);
        } 
        else 
        {
            System.out.print("nothing");
        }
    }
}
