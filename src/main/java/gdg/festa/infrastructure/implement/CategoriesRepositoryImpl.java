package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.Categories;
import gdg.festa.domain.repository.CategoriesRepository;
import gdg.festa.infrastructure.jpa.CategoriesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoriesRepositoryImpl implements CategoriesRepository {

    private final CategoriesJpaRepository categoriesJpaRepository;

    public Categories save(Categories categories){
        return categoriesJpaRepository.save(categories);
    }
}
