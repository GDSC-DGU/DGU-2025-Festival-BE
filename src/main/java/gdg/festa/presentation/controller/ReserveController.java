package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.reserve.ReadReverseUsecase;
import gdg.festa.core.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReserveController {

    private final ReadReverseUsecase readReverseUsecase;

    @GetMapping("/reserve")
    public CommonResponseDto<?> findreserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(readReverseUsecase.execute(number));
    }
}
