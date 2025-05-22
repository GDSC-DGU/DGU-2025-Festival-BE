package gdg.festa.application.mapper;

import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class LostsImageMapper {

    public LostImages toEntity(String imagesUrl,Losts losts){
        return LostImages.builder()
                .losts(losts)
                .imageUrl(imagesUrl)
                .build();
    }

}
