package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.lost.GetLostUsecase;
import gdg.festa.core.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/losts")
@RequiredArgsConstructor
public class LostController {

    private final GetLostUsecase getLostUsecase;


    @GetMapping("/{lostsId}")
    public CommonResponseDto<?> getLostsItem(
            @PathVariable Long lostsId
    ){
        return CommonResponseDto.ok(getLostUsecase.execute(lostsId));
    }

}
