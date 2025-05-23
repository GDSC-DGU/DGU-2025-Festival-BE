package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.notice.CreateNoticeUsecase;
import gdg.festa.application.usecase.notice.EditNoticeUsecase;
import gdg.festa.application.usecase.notice.RemoveNoticeUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.notice.CreateNoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CreateNoticeUsecase createNoticeUsecase;
    private final EditNoticeUsecase editNoticeUsecase;
    private final RemoveNoticeUsecase removeNoticeUsecase;

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
}