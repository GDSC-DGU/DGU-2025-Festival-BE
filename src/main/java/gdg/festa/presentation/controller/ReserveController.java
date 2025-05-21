package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.reserve.CreateReserveUseCase;
import gdg.festa.application.usecase.reserve.ReadReverseUsecase;
import gdg.festa.application.usecase.reserve.UpdateReverseUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReadReverseUsecase readReverseUsecase;
    private final UpdateReverseUsecase updateReverseUsecase;
    private final CreateReserveUseCase createReserveUseCase;

    @GetMapping("")
    public CommonResponseDto<?> findreserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(readReverseUsecase.execute(number));
    }

    @PatchMapping("")
    public CommonResponseDto<?> cancelReserve(
            @RequestParam String number
    ){
        return CommonResponseDto.ok(updateReverseUsecase.execute(number));
    }

    @PostMapping("/{boothsId}")
    public CommonResponseDto<?> createReserve(
            @PathVariable(name = "boothsId") Long boothId,
            @RequestBody CreateReserveRequestDto createReserveRequestDto
    ) {
        return CommonResponseDto.ok(createReserveUseCase.execute(boothId, createReserveRequestDto));
    }
}
