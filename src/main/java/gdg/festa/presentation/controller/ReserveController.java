package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.reserve.ReadReverseUsecase;
import gdg.festa.application.usecase.reserve.UpdateReverseUsecase;
import gdg.festa.core.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReserveController {

    private final ReadReverseUsecase readReverseUsecase;
    private final UpdateReverseUsecase updateReverseUsecase;

    @GetMapping("/reserve")
    public CommonResponseDto<?> findreserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(readReverseUsecase.execute(number));
    }

    @PatchMapping("/reserve")
    public CommonResponseDto<?> cancelReserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(updateReverseUsecase.execute(number));
    }
}
