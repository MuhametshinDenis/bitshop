package github.muhametshindenis.bitshop.modules.s3.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.modules.s3.service.S3Service;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
@Slf4j
@Service
public class S3ServiceImpl implements S3Service {
    @Value("${spring.aws.s3.bucket}")
    private String BUCKET_NAME;

    private final S3Client s3Client;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @PostConstruct
    public void init() {
        List<Bucket> buckets = s3Client.listBuckets().buckets();

        boolean bucketExists = buckets.stream().anyMatch(bucket -> bucket.name().equals(BUCKET_NAME));

        if (!bucketExists) {
            log.warn("Bucket with name: '{}' doesn't exists!", BUCKET_NAME);

            this.s3Client.createBucket(CreateBucketRequest.builder()
                    .bucket(BUCKET_NAME)
                    .build());

            log.info("Bucket created: {}", BUCKET_NAME);
        }
    }

    @Override
    public void save(String key, MultipartFile multipartFile) throws IOException {
        this.s3Client.putObject(PutObjectRequest.builder()
                        .bucket(this.BUCKET_NAME)
                        .key(key)
                        .contentType(multipartFile.getContentType())
                .build(), RequestBody.fromBytes(multipartFile.getBytes()));
    }

    @Override
    public InputStream load(String key) {
        return this.s3Client.getObject(GetObjectRequest.builder()
                        .bucket(this.BUCKET_NAME)
                        .key(key)
                .build());
    }

    @Override
    public void delete(String key) {
        this.s3Client.deleteObject(DeleteObjectRequest.builder()
                        .bucket(this.BUCKET_NAME)
                        .key(key)
                .build());
    }
}
