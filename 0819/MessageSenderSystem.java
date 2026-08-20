interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Email 發送給 " + receiver + "： " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("簡訊發送給 " + receiver + "： " + message);
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Console 發送給 " + receiver + "： " + message);
    }
}

public class MessageSenderSystem {

    public static void notify(MessageSender sender, String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("接收者不能為空白");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            System.out.println("訊息不能為空白");
            return;
        }

        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "student@example.com", "作業已完成");
        notify(sms, "0912345678", "明天記得上課");
        notify(console, "系統管理員", "系統正常運作");

        notify(email, "", "測試訊息");
        notify(sms, "0912345678", "");
    }
}