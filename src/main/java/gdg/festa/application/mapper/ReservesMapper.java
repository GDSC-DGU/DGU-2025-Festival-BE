package gdg.festa.application.mapper;

import gdg.festa.domain.entity.Reserves;
import org.springframework.stereotype.Service;

@Service
public class ReservesMapper {

    public Reserves toEntity(String phoneNumber, String browserToken) {
        return Reserves.reservesBuilder()
                .phoneNumber(phoneNumber)
                .browserToken(browserToken)
                .build();
    }
}
