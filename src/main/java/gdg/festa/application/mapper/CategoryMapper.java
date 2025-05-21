package gdg.festa.application.mapper;

import gdg.festa.domain.entity.Categories;
import gdg.festa.presentation.request.CategoryRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Categories toEntity(CategoryRequestDto categoryRequestDto){
        return Categories.builder()
                .name(categoryRequestDto.name())
                .build();
    }
}
