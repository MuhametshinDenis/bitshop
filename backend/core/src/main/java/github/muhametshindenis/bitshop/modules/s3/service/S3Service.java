package github.muhametshindenis.bitshop.modules.s3.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
public interface S3Service {
    void save(String key, MultipartFile multipartFile) throws IOException;
    InputStream load(String key);
    void delete(String key);
}
