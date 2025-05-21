package gdg.festa.application.service.Losts;

import gdg.festa.application.mapper.CategoryMapper;
import gdg.festa.application.mapper.LostsMapper;
import gdg.festa.application.usecase.Losts.RegisterLostsUsecase;
import gdg.festa.domain.entity.Categories;
import gdg.festa.domain.entity.Losts;
import gdg.festa.domain.repository.CategoriesRepository;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.presentation.request.CategoryRequestDto;
import gdg.festa.presentation.request.LostsRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterLostsService implements RegisterLostsUsecase {

    private final LostsRepository lostsRepository;
    private final LostsMapper lostsMapper;
    private final CategoriesRepository categoriesRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void execute(LostsRequestDto lostsRequestDto, CategoryRequestDto categoryRequestDto) {
        Categories categories = categoryMapper.toEntity(categoryRequestDto);
        try{
            Categories saveCategory = categoriesRepository.save(categories);
        } catch (Exception e) {
            throw new RuntimeException("카테고리 저장 중 오류 발생",e);
        }

        Losts losts = lostsMapper.toEntity(lostsRequestDto);
        losts.setCategories(categories);
        try{
            Losts savelosts = lostsRepository.save(losts);
        } catch (Exception e) {
            throw new RuntimeException("분실물 저장 중 오류 발생",e);
        }

    }
}
