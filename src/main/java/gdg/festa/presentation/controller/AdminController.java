package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.lost.EditLostUsecase;
import gdg.festa.application.usecase.lost.RegistLostUsecase;
import gdg.festa.application.usecase.lost.RemoveLostUsecase;
import gdg.festa.application.usecase.notice.CreateNoticeUsecase;
import gdg.festa.application.usecase.notice.EditNoticeUsecase;
import gdg.festa.application.usecase.notice.RemoveNoticeUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.lost.LostRequestDto;
import gdg.festa.presentation.request.notice.CreateNoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/festa")
@RequiredArgsConstructor
public class AdminController {
    private final CreateNoticeUsecase createNoticeUsecase;
    private final EditNoticeUsecase editNoticeUsecase;
    private final RemoveNoticeUsecase removeNoticeUsecase;
    private final EditLostUsecase editLostUsecase;
    private final RemoveLostUsecase removeLostUsecase;
    private final RegistLostUsecase registLostUsecase;

    @PostMapping("/notices")
    public CommonResponseDto<?> createNotices(
            @ModelAttribute CreateNoticeRequestDto createNoticeRequestDto
    ){
        return CommonResponseDto.ok(createNoticeUsecase.execute(createNoticeRequestDto));
    }

    @PatchMapping("/notices/{noticeId}")
    public CommonResponseDto<?> editNotice(
            @PathVariable Long noticeId,
            @ModelAttribute CreateNoticeRequestDto createNoticeRequestDto
    ){
        editNoticeUsecase.execute(noticeId,createNoticeRequestDto);
        return CommonResponseDto.created(true);
    }

    @DeleteMapping("/notices/{noticeId}")
    public CommonResponseDto<?> deleteNotice(
            @PathVariable Long noticeId
    ){
        removeNoticeUsecase.execute(noticeId);
        return CommonResponseDto.created(true);
    }

    @PostMapping("")
    public CommonResponseDto<?> LostsRegister(
            @ModelAttribute LostRequestDto lostRequestDto
    ){
        registLostUsecase.execute(lostRequestDto);
        return CommonResponseDto.created(true);
    }


    @PatchMapping("/{lostsId}")
    public CommonResponseDto<?> editLostsItem(
            @PathVariable Long lostsId,
            @ModelAttribute LostRequestDto lostRequestDto
    ) {
        editLostUsecase.execute(lostsId, lostRequestDto);
        return CommonResponseDto.created(true);
    }


    @DeleteMapping("/{lostsId}")
    public CommonResponseDto<?> removeLostsItem(
            @PathVariable Long lostsId
    ) {
        removeLostUsecase.execute(lostsId);
        return CommonResponseDto.created(true);
    }
}