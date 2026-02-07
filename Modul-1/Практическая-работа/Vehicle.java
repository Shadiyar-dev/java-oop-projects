public abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public void startEngine() {
        System.out.println(getInfo() + " — двигатель запущен");
    }

    public void stopEngine() {
        System.out.println(getInfo() + " — двигатель остановлен");
    }

    public String getInfo() {
        return brand + " " + model + " (" + year + ")";
    }
}
