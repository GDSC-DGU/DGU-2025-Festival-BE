package gdg.festa.infrastructure.sms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import net.nurigo.java_sdk.api.Message;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class SmsUtil {
    // private final SmsCertification smsCertification;

    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;

    @Value(("${coolsms.url}"))
    private String url;

    private final RestClient restClient = RestClient.create();

    private String createRandomNumber() {
        Random rand = new Random();
        String randomNum = "";
        for (int i = 0; i < 4; i++) {
            String random = Integer.toString(rand.nextInt(10));
            randomNum += random;
        }
        return randomNum;
    }

    private SendSmsRequestDto makeBody(String phoneNumber, String message) {
        return SendSmsRequestDto.builder()
                .token_key(apiSecret)
                .msg_type("sms")
                .dest_phone(phoneNumber)
                .send_phone(fromNumber)
                .msg_body(message)
                .build();

    }

    public void sendMessage(String phoneNumber, String message) {
        String response;

        try {
            response = restClient.post()
                    .uri(url)
                    .headers(httpHeaders -> {
                        httpHeaders.set("x-api-key", apiKey);
                        httpHeaders.set("Content-Type", "application/json; charset=utf-8");
                        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
// 수정
                    })
                    .body(makeBody(phoneNumber, message))
                    .retrieve()
                    .toEntity(String.class)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }
        String code;
        ObjectMapper objectMapper = new ObjectMapper();
        log.error(response);
        try{
            JsonNode rootNode = objectMapper.readTree(response);
            code = rootNode.get("code").asText();
            if(!Objects.equals(code, "200"))
                throw new CustomException(ErrorCode.SERVER_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }

    }

    public String sendMessageRandom(String phoneNumber) {
        String response;
        String randomNum = createRandomNumber();
        String message = "Dirvana 인증번호 : " + randomNum;
        try {
            response = restClient.post()
                    .uri(url)
                    .headers(httpHeaders -> {
                        httpHeaders.set("x-api-key", apiKey);
                        httpHeaders.set("Content-Type", "application/json; charset=utf-8");
                        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
// 수정
                    })
                    .body(makeBody(phoneNumber, message))
                    .retrieve()
                    .toEntity(String.class)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }
        String code;
        ObjectMapper objectMapper = new ObjectMapper();
        log.error(response);
        try{
            JsonNode rootNode = objectMapper.readTree(response);
            code = rootNode.get("code").asText();
            if(!Objects.equals(code, "200"))
                throw new CustomException(ErrorCode.SERVER_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }

        return randomNum;
    }


//    public String sendMessage(String phoneNumber) {
//        Map<Objects, Objects> response;
//        String randomNum = createRandomNumber();
//        try {
//            response = Objects.requireNonNull(restClient.post()
//                    .uri(url)
//                    .headers(httpHeaders -> {
//                        httpHeaders.set("x-api-key", apiKey);
//                        httpHeaders.set("Content-Type", "application/json; charset=utf-8");
//                    })
//                    .body(makeBody(phoneNumber, randomNum))
//                    .retrieve()
//                    .toEntity(Map.class)
//                    .getBody()
//            );
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
//        }
//        System.err.println(response);
//
//        return randomNum;
//    }

}

