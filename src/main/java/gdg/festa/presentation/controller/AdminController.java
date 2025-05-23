package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.notice.CreateNoticeUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.notice.CreateNoticesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CreateNoticeUsecase createNoticeUsecase;

    @PostMapping("/notices")
    public CommonResponseDto<?> createNotices(
            @ModelAttribute CreateNoticesRequestDto createNoticesRequestDto
    ){
        return CommonResponseDto.ok(createNoticeUsecase.execute(createNoticesRequestDto));
    }

}
