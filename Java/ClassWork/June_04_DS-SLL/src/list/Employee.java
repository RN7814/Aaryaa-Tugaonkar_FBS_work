package list;

// 1. Single responsibility: Holds data for one employee
class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Salary: $" + salary;
    }
}

// 2. Node wrapper for the Linked List
class ENode {
    Employee employee;
    ENode next;

    ENode(Employee employee) {
        this.employee = employee;
        this.next = null;
    }
}

// 3. Dedicated manager for list operations
class EmployeeLinkedList {
    private ENode start = null;

    // Adds an employee to the end of the list
    void add(Employee data) {
        ENode nn = new ENode(data);
        if (this.start == null) {
            this.start = nn;
        } else {
            ENode end = start;
            while (end.next != null) {
                end = end.next;
            }
            end.next = nn;
        }
    }

    // Prints all employees in the list
    void display() {
        if (start == null) {
            System.out.println("The list is empty.");
            return;
        }
        ENode temp = start;
        while (temp != null) {
            System.out.println(temp.employee);
            temp = temp.next;
        }
    }
}

// 4. Execution class
class Test2 {
    public static void main(String[] args) {
        // Create individual employee data objects
        Employee e1 = new Employee(101, "Aaryaa Tugaonkar", 95746);
        Employee e2 = new Employee(102, "Swamini Warale", 98422);
        Employee e3 = new Employee(108, "Santosh Gadekar", 84244);

        // Create one central list manager
        EmployeeLinkedList list = new EmployeeLinkedList();

        // Add employees to the same list
        list.add(e1);
        list.add(e2);
        list.add(e3);

        // Display the complete list
        list.display();
    }
}
