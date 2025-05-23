package gdg.festa.infrastructure.sms;

public record SentMessage(
         String msg_id,
         String dest_phone
) {
}
