package dip;

public class EmailSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("Электрондық пошта жіберілді: " + message);
    }
}