package gdg.festa.presentation.controller;

import gdg.festa.application.dto.pub.ReadPubWaitingUserListResponseDto;
import gdg.festa.application.usecase.pubs.ReadAdminPubUsecase;
import gdg.festa.application.usecase.pubs.ReadPubsUsecase;
import gdg.festa.application.usecase.pubs.UpdatePubUsecase;
import gdg.festa.application.usecase.reserve.CalledReserveUseCase;
import gdg.festa.application.usecase.reserve.CompleteReserveUseCase;
import gdg.festa.core.annotation.UserId;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PubController {

    private final UpdatePubUsecase updatePubUsecase;
    private final ReadPubsUsecase readPubsUsecase;
    private final CompleteReserveUseCase completeReserveUseCase;
    private final ReadAdminPubUsecase readAdminPubUsecase;
    private final CalledReserveUseCase calledReserveUseCase;


    @PatchMapping("/admin/pub")
    public CommonResponseDto<?> updateState(
            @UserId UUID pubAdminId,
            @RequestParam(name = "pubsStatus") String pubsStatus
    ){
        return CommonResponseDto.ok(updatePubUsecase.execute(pubAdminId, pubsStatus));
    }

    @GetMapping("/pubs")
    public CommonResponseDto<?> findAllWaiting(
    ){
        return CommonResponseDto.ok(readPubsUsecase.execute());
    }

    @PatchMapping("/admin/pub/reserve")
    public CommonResponseDto<?> reserveComplete(
            @UserId UUID adminId,
            @RequestBody CompletedReserveRequestDto completedReserveRequestDto
    ) {
        return CommonResponseDto.ok(completeReserveUseCase.execute(adminId, completedReserveRequestDto));
    }

    @PostMapping("/admin/pub/call")
    public CommonResponseDto<?> reserverCalled(
            @UserId UUID adminId,
            @RequestBody CompletedReserveRequestDto completedReserveRequestDto
    ) {
        return CommonResponseDto.ok(calledReserveUseCase.execute(completedReserveRequestDto));
    }

    @GetMapping("/admin/pub")
    public CommonResponseDto<ReadPubWaitingUserListResponseDto> findAllReserver(
            @UserId UUID pubAdminId
    ){
        return CommonResponseDto.ok(readAdminPubUsecase.execute(pubAdminId));
    }
}
