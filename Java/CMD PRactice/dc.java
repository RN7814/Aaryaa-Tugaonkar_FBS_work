// ─────────────────────────────────────────────
//  Class Hierarchy
// ─────────────────────────────────────────────

class Employee {
    String name;
    Employee(String name) { this.name = name; }

    void work() { System.out.println(name + " is working."); }
}

class HR extends Employee {
    HR(String name) { super(name); }
    void recruit() { System.out.println(name + " is recruiting candidates."); }
}

class SM extends Employee {          // Sales Manager
    SM(String name) { super(name); }
    void sellProduct() { System.out.println(name + " is selling products."); }
}

class Admin extends Employee {
    Admin(String name) { super(name); }
    void manageServer() { System.out.println(name + " is managing the server."); }
}

// ─────────────────────────────────────────────
//  Test Class
// ─────────────────────────────────────────────

class Test {
    public static void main(String[] args) {

        // Upcasting (implicit / automatic) ──────────────────
        Employee e1 = new HR("Riya");       // HR  → Employee
        Employee e2 = new SM("Arjun");      // SM  → Employee
        Employee e3 = new Admin("Sneha");   // Admin → Employee

        System.out.println("─── Upcasting (automatic) ───");
        e1.work();   // only Employee methods visible
        e2.work();
        e3.work();

        System.out.println();

        // ─────────────────────────────────────────────────────
        // 1.  UNSAFE DOWNCASTING  (no instanceof check)
        //     Works only if the actual object really IS an HR.
        //     Will throw ClassCastException if wrong!
        // ─────────────────────────────────────────────────────
        System.out.println("─── Unsafe Downcasting ───");

        HR hr = (HR) e1;        // safe here — e1 really is HR
        hr.recruit();           // HR-specific method now accessible

        // Uncommenting the line below would crash at runtime:
        // SM sm_bad = (SM) e1; // ❌ ClassCastException!

        System.out.println();

        // ─────────────────────────────────────────────────────
        // 2.  instanceof  CHECK
        //     Tests the actual runtime type before casting.
        // ─────────────────────────────────────────────────────
        System.out.println("─── instanceof Check ───");

        Employee[] staff = { e1, e2, e3 };

        for (Employee emp : staff) {
            if (emp instanceof HR)    System.out.println(emp.name + " → is HR");
            if (emp instanceof SM)    System.out.println(emp.name + " → is SM");
            if (emp instanceof Admin) System.out.println(emp.name + " → is Admin");
        }

        System.out.println();

        // ─────────────────────────────────────────────────────
        // 3.  SAFE DOWNCASTING  (instanceof + if before cast)
        // ─────────────────────────────────────────────────────
        System.out.println("─── Safe Downcasting (if + instanceof) ───");

        processEmployee(e1);   // HR
        processEmployee(e2);   // SM
        processEmployee(e3);   // Admin
    }

    // Safely downcasts and calls the role-specific method
    static void processEmployee(Employee emp) {
        if (emp instanceof HR) {
            HR hr = (HR) emp;           // ✅ safe — checked first
            hr.recruit();
        } else if (emp instanceof SM) {
            SM sm = (SM) emp;           // ✅ safe
            sm.sellProduct();
        } else if (emp instanceof Admin) {
            Admin admin = (Admin) emp;  // ✅ safe
            admin.manageServer();
        } else {
            emp.work();                 // plain Employee fallback
        }
    }
}