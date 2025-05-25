package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.lost.RegistLostUsecase;
import gdg.festa.application.usecase.lost.RemoveLostUsecase;
import gdg.festa.application.usecase.lost.UpdateLostUsecase;
import gdg.festa.application.usecase.notice.CreateNoticeUsecase;
import gdg.festa.application.usecase.notice.UpdateNoticeUsecase;
import gdg.festa.application.usecase.notice.RemoveNoticeUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.lost.CreateLostRequestDto;
import gdg.festa.presentation.request.lost.UpdateLostRequestDto;
import gdg.festa.presentation.request.notice.CreateNoticeRequestDto;
import gdg.festa.presentation.request.notice.UpdateNoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/festa")
@RequiredArgsConstructor
public class AdminController {
    private final CreateNoticeUsecase createNoticeUsecase;
    private final UpdateNoticeUsecase updateNoticeUsecase;
    private final RemoveNoticeUsecase removeNoticeUsecase;
    private final UpdateLostUsecase updateLostUsecase;
    private final RemoveLostUsecase removeLostUsecase;
    private final RegistLostUsecase registLostUsecase;

    @PostMapping("/notices")
    public CommonResponseDto<?> createNotices(
            @ModelAttribute CreateNoticeRequestDto createNoticeRequestDto
    ){
        return CommonResponseDto.ok(createNoticeUsecase.execute(createNoticeRequestDto));
    }

    @PatchMapping("/notices")
    public CommonResponseDto<?> updateNotice(
            @ModelAttribute UpdateNoticeRequestDto updateNoticeRequestDto
    ){
        updateNoticeUsecase.execute(updateNoticeRequestDto);
        return CommonResponseDto.created(true);
    }

    @DeleteMapping("/notices/{noticeId}")
    public CommonResponseDto<?> deleteNotice(
            @PathVariable Long noticeId
    ){
        removeNoticeUsecase.execute(noticeId);
        return CommonResponseDto.created(true);
    }

    @PostMapping("/losts")
    public CommonResponseDto<?> LostsRegister(
            @ModelAttribute CreateLostRequestDto createLostRequestDto
    ){
        registLostUsecase.execute(createLostRequestDto);
        return CommonResponseDto.created(true);
    }


    @PatchMapping("/losts")
    public CommonResponseDto<?> editLostsItem(
            @ModelAttribute UpdateLostRequestDto updateLostRequestDto
    ) {
        updateLostUsecase.execute(updateLostRequestDto);
        return CommonResponseDto.created(true);
    }


    @DeleteMapping("/losts/{lostsId}")
    public CommonResponseDto<?> removeLostsItem(
            @PathVariable Long lostsId
    ) {
        removeLostUsecase.execute(lostsId);
        return CommonResponseDto.created(true);
    }
}