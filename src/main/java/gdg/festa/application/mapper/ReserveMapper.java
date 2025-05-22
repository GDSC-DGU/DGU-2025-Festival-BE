package gdg.festa.application.mapper;

import gdg.festa.domain.entity.Reserve;
import org.springframework.stereotype.Service;

@Service
public class ReserveMapper {

    public Reserve toEntity(String phoneNumber, String browserToken) {
        return Reserve.reservesBuilder()
                .phoneNumber(phoneNumber)
                .browserToken(browserToken)
                .build();
    }
}
