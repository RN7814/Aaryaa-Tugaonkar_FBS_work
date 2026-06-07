package assignment_2;

// Attributes for the specific shapes as per requirements
class Triangle {
	
    double base, height;
    
    Triangle(double b, double h) { 
    	this.base = b; this.height = h; 
    	}
}

class Rectangle {
    double length, breadth;
    
    Rectangle(double l, double b)
    	{ 
    	this.length = l; this.breadth = b;
    	}
}

class Circle {
    double radius;
    Circle(double r) 
    	{ 
    	this.radius = r;
    	}
}

class Shape {
    double area;


    void calculateArea(Triangle t) {
        this.area = 0.5 * t.base * t.height;
        System.out.println("Area of Triangle: " + this.area);
    }

    void calculateArea(Rectangle r) {
        this.area = r.length * r.breadth;
        System.out.println("Area of Rectangle: " + this.area);
    }

    void calculateArea(Circle c) {
        this.area = 3.14 * c.radius * c.radius;
        System.out.println("Area of Circle: " + this.area);
    }
}

class TestArea {
    public static void main(String[] args) {
        Shape scheduler = new Shape();
        
        Triangle tri = new Triangle(10, 5);
        Rectangle rect = new Rectangle(10, 20);
        Circle circ = new Circle(7);

        // Calling overloaded methods
        scheduler.calculateArea(tri);
        scheduler.calculateArea(rect);
        scheduler.calculateArea(circ);
    }
}