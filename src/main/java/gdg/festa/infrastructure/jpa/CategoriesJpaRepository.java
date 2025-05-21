package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesJpaRepository extends JpaRepository<Categories,Long> {
    Categories save(Categories categories);
}
