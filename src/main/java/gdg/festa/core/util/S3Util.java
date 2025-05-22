package gdg.festa.core.util;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class S3Util {


    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.dir}")
    private String dir;

    public String upload(MultipartFile file) {
        try {
            String key = dir + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));

            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
        } catch (Exception e) {
            // 파일이 손상되었거나, 읽을 수 없는 경우
            throw new CustomException(ErrorCode.INVALID_REQUEST_IMAGES);
        }
    }
    public List<String> upload(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .collect(Collectors.toList());
    }

    public void delete(String imageUrl) {
        try {
            String key = extractKeyFromUrl(imageUrl);
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
            log.info("S3 이미지 삭제 완료: {}", imageUrl);
        } catch (Exception e) {
            log.error("S3 이미지 삭제 중 오류 발생: {}", imageUrl, e);
            throw new RuntimeException("S3 이미지 삭제 중 오류 발생: " + imageUrl, e);
        }
    }

    private String extractKeyFromUrl(String imageUrl) {
        String baseUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/";
        if (!imageUrl.startsWith(baseUrl)) {
            throw new IllegalArgumentException("올바르지 않은 S3 URL 형식입니다: " + imageUrl);
        }
        return imageUrl.substring(baseUrl.length());
    }

}