package gdg.festa.presentation.controller;

import gdg.festa.application.dto.Losts.LostsResponseDto;
import gdg.festa.application.usecase.Losts.RegisterLostsUsecase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.CategoryRequestDto;
import gdg.festa.presentation.request.LoginRequestDto;
import gdg.festa.presentation.request.LostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/losts")
@RequiredArgsConstructor
public class LostsController {

    private final RegisterLostsUsecase registerLostsUsecase;

    @PostMapping("/")
    public CommonResponseDto<?> LostsRegister(
        @RequestBody LostsRequestDto lostsRequestDto
    ){
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto(
                lostsRequestDto.CategoryName());
        registerLostsUsecase.execute(lostsRequestDto,categoryRequestDto);
        return CommonResponseDto.ok("분실물 등록 완료");
    }

}
