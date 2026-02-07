package usersystem;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public boolean addUser(User user) {
        if (user == null) {
            System.out.println("Ошибка: пользователь не может быть null");
            return false;
        }

        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                System.out.println("Ошибка: пользователь с email " + user.getEmail() + " уже существует");
                return false;
            }
        }

        users.add(user);
        System.out.println("Пользователь добавлен: " + user);
        return true;
    }

    public boolean removeUser(String email) {
        User userToRemove = findUserByEmail(email);

        if (userToRemove == null) {
            System.out.println("Ошибка: пользователь с email " + email + " не найден");
            return false;
        }

        users.remove(userToRemove);
        System.out.println("Пользователь удален: " + userToRemove);
        return true;
    }

    public boolean updateUser(String email, String newName, String newRole) {
        User userToUpdate = findUserByEmail(email);

        if (userToUpdate == null) {
            System.out.println("Ошибка: пользователь с email " + email + " не найден");
            return false;
        }

        userToUpdate.setName(newName);
        userToUpdate.setRole(newRole);
        System.out.println("Пользователь обновлен: " + userToUpdate);
        return true;
    }

    private User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Нет зарегистрированных пользователей");
            return;
        }

        System.out.println("Список пользователей:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }

    public int getUserCount() {
        return users.size();
    }
}
