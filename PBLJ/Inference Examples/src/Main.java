interface Drivable{
    void drive();
}

class Vehicle {
    String brand = "Mercedes";
    String speed = "150 km/h";

    public static void move(){
        System.out.println("Moving");
    }
}

class Car extends Vehicle implements Drivable {
    Car(String brand, String speed){
        this.brand = brand;
        this.speed = speed;
        System.out.println("Creating car: " + brand + " " + speed);
    }
    int seats = 5;

    @Override
    public void drive() {
        System.out.println("Drive Implementation");
    }

    public static void display() {
        System.out.println("Displaying car");
    }
}

public class Main {
    public static void main(String[] args) {
        Car c = new Car("Audi", "150 km/h");
        c.move();
        c.drive();
        c.display();

    }
}
