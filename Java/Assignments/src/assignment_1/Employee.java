package assignment_1;
class Employee{
    int EmployeeId;
    String Name;
    double Salary;

    void setId(int i){
        this.EmployeeId=i;
    }
    
    void setName(String n){
        this.Name=n;
    }

    void setSalary(double s){
        this.Salary=s;
	}
		
	int getId(){
		return this.EmployeeId;
	}
	
	double getSalary(){
		return this.Salary;
	}
	
	String getName(){
		return this.Name;
	}
		
		
	}	
    

class Test4{
    public static void main (String[] args){
        Employee e1 =  new Employee();

        e1.setName("Rn");
        e1.setId(01);
        e1.setSalary(50000);

        //System.out.printf("%d ",e1.EmployeeId);
        //System.out.printf("%s ",e1.Name);
        //System.out.printf("%f ",e1.Salary);
		
		System.out.println(e1.getId() + "-" + e1.getName() + "-" + e1.getSalary());


    }
}