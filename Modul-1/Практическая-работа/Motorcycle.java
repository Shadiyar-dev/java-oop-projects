public class Motorcycle extends Vehicle {
    private String bodyType;
    private boolean hasBox;

    public Motorcycle(String brand, String model, int year, String bodyType, boolean hasBox) {
        super(brand, model, year);
        this.bodyType = bodyType;
        this.hasBox = hasBox;
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                ", тип: " + bodyType +
                ", бокс: " + (hasBox ? "есть" : "нет");
    }
}
