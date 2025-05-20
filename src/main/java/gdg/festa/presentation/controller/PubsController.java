package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.pubs.ReadPubsUsecase;
import gdg.festa.application.usecase.pubs.UpdatePubsUsecase;
import gdg.festa.core.annotation.UserId;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.domain.type.PubsStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PubsController {

    private final UpdatePubsUsecase updatePubsUsecase;
    private final ReadPubsUsecase readPubsUsecase;


    @PatchMapping("/admin/pub")
    public CommonResponseDto<?> updateState(
        //    @PathVariable(name = "pubAdminId") Long pubAdminId,
            @UserId UUID pubAdminId,
            @RequestParam(name = "pubsStatus") PubsStatus pubsStatus
    ){
        return CommonResponseDto.ok(updatePubsUsecase.execute(pubAdminId, pubsStatus));
    }

    @GetMapping("/pubs")
    public CommonResponseDto<?> findAllWaiting(
    ){
        return CommonResponseDto.ok(readPubsUsecase.execute());
    }
}
