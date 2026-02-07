package dip;

public class MessengerSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("Мессенджер хабарламасы жіберілді: " + message);
    }
}