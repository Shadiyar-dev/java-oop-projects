import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private List<Garage> garages = new ArrayList<>();

    public void addGarage(Garage garage) {
        garages.add(garage);
        System.out.println("Гараж добавлен: " + garage.getName());
    }

    public void removeGarage(Garage garage) {
        garages.remove(garage);
        System.out.println("Гараж удалён: " + garage.getName());
    }

    public void findVehicle(String model) {
        for (Garage garage : garages) {
            for (Vehicle v : garage.getVehicles()) {
                if (v.getInfo().contains(model)) {
                    System.out.println("Найдено: " + v.getInfo() +
                            " в гараже " + garage.getName());
                }
            }
        }
    }
}
