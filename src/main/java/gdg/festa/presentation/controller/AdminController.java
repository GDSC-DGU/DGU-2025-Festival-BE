package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.notices.CreateNoticesUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.CreateNoticesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CreateNoticesUsecase createNoticesUsecase;

    @PostMapping("/notices")
    public CommonResponseDto<?> createNotices(
            @ModelAttribute CreateNoticesRequestDto createNoticesRequestDto
    ){
        return CommonResponseDto.ok(createNoticesUsecase.execute(createNoticesRequestDto));
    }

}
