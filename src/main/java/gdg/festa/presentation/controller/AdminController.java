package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.notices.CreateNoticesUsecase;
import gdg.festa.application.usecase.notices.EditNoticesUsecase;
import gdg.festa.application.usecase.notices.RemoveNoticeUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.CreateNoticesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CreateNoticesUsecase createNoticesUsecase;
    private final EditNoticesUsecase editNoticesUsecase;
    private final RemoveNoticeUsecase removeNoticeUsecase;

    @PostMapping("/notices")
    public CommonResponseDto<?> createNotices(
            @ModelAttribute CreateNoticesRequestDto createNoticesRequestDto
    ){
        return CommonResponseDto.ok(createNoticesUsecase.execute(createNoticesRequestDto));
    }

    @PatchMapping("/notices/{noticeId}")
    public CommonResponseDto<?> editNotices(
            @PathVariable Long noticeId,
            @ModelAttribute CreateNoticesRequestDto createNoticesRequestDto
    ){
        editNoticesUsecase.execute(noticeId,createNoticesRequestDto);
        return CommonResponseDto.created(true);
    }

    @DeleteMapping("/notices/{noticeId}")
    public CommonResponseDto<?> deleteNotices(
            @PathVariable Long noticeId
    ){
        removeNoticeUsecase.execute(noticeId);
        return CommonResponseDto.created(true);
    }
}