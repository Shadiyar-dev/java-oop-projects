public class Car extends Vehicle {
    private int doors;
    private String transmission;

    public Car(String brand, String model, int year, int doors, String transmission) {
        super(brand, model, year);
        this.doors = doors;
        this.transmission = transmission;
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                ", двери: " + doors +
                ", трансмиссия: " + transmission;
    }
}
