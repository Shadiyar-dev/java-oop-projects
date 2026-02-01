class UserRepository {
    public void saveUser(User user) {
        System.out.println("Сохранение пользователя " + user.getName() + " в БД");
    }
}