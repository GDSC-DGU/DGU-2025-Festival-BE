package gdg.festa.infrastructure.sms;

import java.util.List;

public record Content(
        String message,
         List<SentMessage> sent_messages,
         String reserv,
         String sent_time,
         String send_phone
) {
}
