package github.muhametshindenis.bitshop.modules.storage.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.modules.s3.service.S3Service;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import github.muhametshindenis.bitshop.modules.storage.util.StorageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

// TODO: Дописать Unit тесты для удаления
/**
 * @author Denis
 * @see <a href="https://github.com/MuhametshinDenis">GitHub</a>
 * @since 14.09.2025
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final S3Service s3Service;

    public StorageServiceImpl(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    public String save(String folder, MultipartFile file) throws IOException {
        String key = StorageUtils.s3KeyGenerator(folder, file.getOriginalFilename());
        this.s3Service.save(key, file);
        return StorageUtils.presignedUrl(key);
    }

    @Override
    public InputStream load(String path) {
        String formattedPath = StorageUtils.formattedPath(path);
        return this.s3Service.load(formattedPath);
    }

    @Override
    public void delete(String file) {
        this.s3Service.delete(StorageUtils.fromPresignUrlToS3Key(file));
    }
}