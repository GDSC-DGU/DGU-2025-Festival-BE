package gdg.festa.application.service.Losts;

import gdg.festa.application.usecase.Losts.RemoveLostsUsecase;
import gdg.festa.domain.repository.LostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveLostService implements RemoveLostsUsecase {

    private final LostsRepository lostsRepository;

    @Override
    public void execute(Long lostsId){
        lostsRepository.deleteById(lostsId);
    }
}
