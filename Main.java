public class Main {
    public static void main(String[] args) {

        Vehicle car1 = new Car("Toyota", "Camry", 2020, 4, "Автомат");
        Vehicle car2 = new Car("BMW", "X5", 2022, 4, "Автомат");
        Vehicle moto1 = new Motorcycle("Yamaha", "MT-07", 2021, "Нейкед", false);

        Garage garage1 = new Garage("Гараж №1");
        Garage garage2 = new Garage("Гараж №2");

        garage1.addVehicle(car1);
        garage1.addVehicle(moto1);
        garage2.addVehicle(car2);

        Fleet fleet = new Fleet();
        fleet.addGarage(garage1);
        fleet.addGarage(garage2);

        car1.startEngine();
        moto1.startEngine();
        car1.stopEngine();

        fleet.findVehicle("Camry");
        fleet.findVehicle("BMW");
    }
}
