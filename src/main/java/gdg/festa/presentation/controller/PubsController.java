package gdg.festa.presentation.controller;

import gdg.festa.application.dto.pubs.ReadPubWaitingUserListResponseDto;
import gdg.festa.application.usecase.pubs.ReadAdminPubsUsecase;
import gdg.festa.application.usecase.pubs.ReadPubsUsecase;
import gdg.festa.application.usecase.pubs.UpdatePubsUsecase;
import gdg.festa.application.usecase.reserve.CompleteReserveUseCase;
import gdg.festa.core.annotation.UserId;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.domain.type.PubsStatus;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PubsController {

    private final UpdatePubsUsecase updatePubsUsecase;
    private final ReadPubsUsecase readPubsUsecase;
    private final CompleteReserveUseCase completeReserveUseCase;
    private final ReadAdminPubsUsecase readAdminPubsUsecase;


    @PatchMapping("/admin/pub")
    public CommonResponseDto<?> updateState(
            @UserId UUID pubAdminId,
            @RequestParam(name = "pubsStatus") String pubsStatus
    ){
        return CommonResponseDto.ok(updatePubsUsecase.execute(pubAdminId, pubsStatus));
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

    @GetMapping("/admin/pub")
    public CommonResponseDto<ReadPubWaitingUserListResponseDto> findAllReserver(
            @UserId UUID pubAdminId
    ){
        return CommonResponseDto.ok(readAdminPubsUsecase.execute(pubAdminId));
    }
}
