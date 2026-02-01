package usersystem;

public class UserSystemMain {
    public static void main(String[] args) {
        System.out.println("=== Система управления пользователями ===\n");
        System.out.println("Демонстрация принципов YAGNI, KISS и DRY\n");

        UserManager userManager = new UserManager();

        User admin = new User("Администратор", "admin@company.com", "Admin");
        User manager = new User("Менеджер", "manager@company.com", "Manager");
        User user = new User("Пользователь", "user@company.com", "User");

        System.out.println("1. Добавление пользователей:");
        userManager.addUser(admin);
        userManager.addUser(manager);
        userManager.addUser(user);

        User duplicate = new User("Дубликат", "admin@company.com", "User");
        userManager.addUser(duplicate);

        System.out.println("\n2. Список пользователей:");
        userManager.displayAllUsers();
        System.out.println("Всего пользователей: " + userManager.getUserCount());

        System.out.println("\n3. Обновление пользователя:");
        userManager.updateUser("manager@company.com", "Главный менеджер", "Senior Manager");

        System.out.println("\n4. Удаление пользователя:");
        userManager.removeUser("user@company.com");

        userManager.removeUser("unknown@company.com");

        System.out.println("\n5. Финальный список:");
        userManager.displayAllUsers();
        System.out.println("Всего пользователей: " + userManager.getUserCount());

        System.out.println("\n=== Демонстрация завершена ===");
    }
}