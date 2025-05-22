package gdg.festa.application.mapper;

import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import org.springframework.stereotype.Service;

@Service
public class LostImageMapper {

    public LostImage toEntity(String imagesUrl, Lost lost){
        return LostImage.LostImagesBuilder()
                .lost(lost)
                .imageUrl(imagesUrl)
                .build();
    }

}
