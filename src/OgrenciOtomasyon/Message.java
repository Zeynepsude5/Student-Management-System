package OgrenciOtomasyon;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender; 
    private String receiver;
    private String content; 
    private String timestamp;

    public Message(String sender, String receiver, String content, String timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s'den %s'ye: %s", timestamp, sender, receiver, content);
    }
}
