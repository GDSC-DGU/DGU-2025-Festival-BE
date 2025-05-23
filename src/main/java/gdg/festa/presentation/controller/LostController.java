package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.lost.EditLostUsecase;
import gdg.festa.application.usecase.lost.GetLostUsecase;
import gdg.festa.application.usecase.lost.RegistLostUsecase;
import gdg.festa.application.usecase.lost.RemoveLostUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.lost.LostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/losts")
@RequiredArgsConstructor
public class LostController {

    private final RegistLostUsecase registerLostsUsecase;
    private final GetLostUsecase getLostUsecase;
    private final EditLostUsecase editLostUsecase;
    private final RemoveLostUsecase removeLostUsecase;

    @PostMapping("/")
    public CommonResponseDto<?> LostsRegister(
        @ModelAttribute LostRequestDto lostRequestDto
    ){
        registerLostsUsecase.execute(lostRequestDto);
        return CommonResponseDto.created(true);
    }

    @GetMapping("/{lostsId}")
    public CommonResponseDto<?> getLostsItem(
            @PathVariable Long lostsId
    ){
        return CommonResponseDto.ok(getLostUsecase.execute(lostsId));
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
