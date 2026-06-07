package assignment_2;

class Time {
    private int hr;
    private int min;
    private int sec;

    // Constructor
    public Time(int hr, int min, int sec) {
        this.hr = hr;
        this.min = min;
        this.sec = sec;
        normalize();
    }

    // Default Constructor
    public Time() {
        this(0, 0, 0);
    }

    // Helper method to normalize time (handle carries and 24-hour wrap)
    private void normalize() {
        if (sec >= 60) {
            min += sec / 60;
            sec %= 60;
        }
        if (min >= 60) {
            hr += min / 60;
            min %= 60;
        }
        if (hr >= 24) {
            hr %= 24;
        }
    }

    // Display time in hh:mm:ss 24-hour format
    public void display() {
        System.out.printf("%02d:%02d:%02d%n", hr, min, sec);
    }

    // 1. Overload add() to add two Time objects
    public Time add(Time t) {
        Time result = new Time();
        result.sec = this.sec + t.sec;
        result.min = this.min + t.min;
        result.hr = this.hr + t.hr;
        result.normalize();
        return result;
    }

    // 2. Overload add() to add an integer value based on unit
    // 'h' for hours, 'm' for minutes, 's' for seconds
    public Time add(int val, char unit) {
        Time result = new Time(this.hr, this.min, this.sec);
        
        switch (Character.toLowerCase(unit)) {
            case 'h':
                result.hr += val;
                break;
            case 'm':
                result.min += val;
                break;
            case 's':
                result.sec += val;
                break;
            default:
                System.out.println("Invalid unit! Use 'h', 'm', or 's'.");
        }
        
        result.normalize();
        return result;
    }
}

class Main1 {
    public static void main(String[] args) {
        Time t1 = new Time(10, 45, 50);
        Time t2 = new Time(2, 20, 15);

        System.out.print("Original Time 1: ");
        t1.display();
        System.out.print("Original Time 2: ");
        t2.display();

        // Case 1: Add two Time objects
        Time sumTime = t1.add(t2);
        System.out.print("\nAfter adding Time 1 and Time 2: ");
        sumTime.display();

        // Case 2: Add integer as seconds
        Time addSec = t1.add(75, 's');
        System.out.print("After adding 75 seconds to Time 1: ");
        addSec.display();

        // Case 3: Add integer as minutes
        Time addMin = t1.add(20, 'm');
        System.out.print("After adding 20 minutes to Time 1: ");
        addMin.display();

        // Case 4: Add integer as hours
        Time addHr = t1.add(15, 'h');
        System.out.print("After adding 15 hours to Time 1 (24-hr wrap): ");
        addHr.display();
    }
}
