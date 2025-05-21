package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.reserve.ReadReserveUsecase;
import gdg.festa.application.usecase.reserve.UpdateReserveUsecase;
import gdg.festa.core.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {

    private final ReadReserveUsecase readReserveUsecase;
    private final UpdateReserveUsecase updateReserveUsecase;

    @GetMapping("")
    public CommonResponseDto<?> findReserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(readReserveUsecase.execute(number));
    }

    @PatchMapping("")
    public CommonResponseDto<?> cancelReserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(updateReserveUsecase.execute(number));
    }
}
