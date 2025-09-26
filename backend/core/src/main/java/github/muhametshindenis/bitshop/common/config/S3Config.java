package github.muhametshindenis.bitshop.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
@Configuration
public class S3Config {
    @Value("${spring.aws.s3.region}")
    private String REGION;

    @Value("${spring.aws.s3.access-key}")
    private String ACCESS_KEY;

    @Value("${spring.aws.s3.secret-key}")
    private String SECRET_KEY;

    @Value("${spring.aws.s3.url}")
    private String S3_URL;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(REGION))
                .endpointOverride(URI.create(S3_URL))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)
                ))
                .forcePathStyle(true)
                .build();
    }
}
