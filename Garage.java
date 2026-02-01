import java.util.ArrayList;
import java.util.List;

public class Garage {
    private String name;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Garage(String name) {
        this.name = name;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Добавлено в гараж " + name + ": " + vehicle.getInfo());
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        System.out.println("Удалено из гаража " + name + ": " + vehicle.getInfo());
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public String getName() {
        return name;
    }
}
