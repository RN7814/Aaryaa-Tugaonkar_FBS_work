package assignment_3;

// Base class to store customer details
class Customer {
    int house_no;
    String name;
    double units;
    
    Customer(int h, String n, double u) {
        this.house_no = h;
        this.name = n;
        this.units = u;
    }
    
    Customer() {
        this.house_no = 0;
        this.name = "Not Given";
        this.units = 0;
    }
}


class ElectricityBill extends Customer {
    double bill;
    
    static double rate = 4.5; 
    
    ElectricityBill(int h, String n, double u) {
        super(h, n, u);
    }
    
    static void updateRate(double newRate) {
        rate = newRate;
    }
    
    double calculateBill() {
        this.bill = this.units * rate;
        return this.bill;
    }
    
    void display() {
        System.out.println("House No: " + this.house_no);
        System.out.println("Name: " + this.name);
        System.out.println("Units Consumed: " + this.units);
        System.out.println("Rate per Unit: " + rate);
        System.out.println("Total Bill Amount: " + this.bill);
        System.out.println("-------------------------");
    }
}

class LightTest {
    public static void main(String[] args) {
        
        ElectricityBill bill1 = new ElectricityBill(24, "Aryan Tugaonkar", 234);
        
       
        bill1.calculateBill();
        bill1.display();
        
        
        ElectricityBill.updateRate(5.5);
        
        ElectricityBill bill2 = new ElectricityBill(25, "John Doe", 100);
        bill2.calculateBill();
        bill2.display();
    }
}
