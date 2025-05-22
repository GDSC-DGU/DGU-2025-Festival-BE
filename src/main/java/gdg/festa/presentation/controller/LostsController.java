package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.Losts.EditLostsUsecase;
import gdg.festa.application.usecase.Losts.GetLostsUsecase;
import gdg.festa.application.usecase.Losts.RegistLostsUsecase;
import gdg.festa.application.usecase.Losts.RemoveLostsUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/losts")
@RequiredArgsConstructor
public class LostsController {

    private final RegistLostsUsecase registerLostsUsecase;
    private final GetLostsUsecase getLostsUsecase;
    private final EditLostsUsecase editLostsUsecase;
    private final RemoveLostsUsecase removeLostsUsecase;

    @PostMapping("/")
    public CommonResponseDto<?> LostsRegister(
        @ModelAttribute LostsRequestDto lostsRequestDto
    ){
        registerLostsUsecase.execute(lostsRequestDto);
        return CommonResponseDto.created(true);
    }

    @GetMapping("/{lostsId}")
    public CommonResponseDto<?> getLostsItem(
            @PathVariable Long lostsId
    ){
        return CommonResponseDto.ok(getLostsUsecase.execute(lostsId));
    }

    @PatchMapping("/{lostsId}")
    public CommonResponseDto<?> editLostsItem(
            @PathVariable Long lostsId,
            @ModelAttribute LostsRequestDto lostsRequestDto
    ) {
        editLostsUsecase.execute(lostsId,lostsRequestDto);
        return CommonResponseDto.created(true);
    }


    @DeleteMapping("/{lostsId}")
    public CommonResponseDto<?> removeLostsItem(
            @PathVariable Long lostsId
    ) {
        removeLostsUsecase.execute(lostsId);
        return CommonResponseDto.created(true);
    }
}
