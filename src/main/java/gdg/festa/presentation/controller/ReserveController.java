package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.reserve.CreateReserveUseCase;
import gdg.festa.application.usecase.reserve.ReadReserveUsecase;
import gdg.festa.application.usecase.reserve.UpdateReserveUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReadReserveUsecase readReserveUsecase;
    private final UpdateReserveUsecase updateReserveUsecase;
    private final CreateReserveUseCase createReserveUseCase;

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

    @PostMapping("/{boothsId}")
    public CommonResponseDto<?> createReserve(
            @PathVariable(name = "boothsId") Long boothId,
            @RequestBody CreateReserveRequestDto createReserveRequestDto
    ) {
        return CommonResponseDto.ok(createReserveUseCase.execute(boothId, createReserveRequestDto));
    }
}
