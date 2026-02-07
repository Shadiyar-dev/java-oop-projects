package dip;

import java.util.List;

public class NotificationService {
    private List<NotificationSender> senders;

    public NotificationService(List<NotificationSender> senders) {
        this.senders = senders;
    }

    public void addSender(NotificationSender sender) {
        this.senders.add(sender);
    }

    public void sendNotification(String message) {
        for (NotificationSender sender : senders) {
            sender.send(message);
        }
    }
}
