package github.muhametshindenis.bitshop.modules.storage.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.modules.s3.service.S3Service;
import github.muhametshindenis.bitshop.modules.storage.dto.SaveStorageDto;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * StorageServiceImpl — сервис работы с файлами в S3 и генерации URL через CDN.
 * Папки должны передаваться в виде 'avatars/', файлы — 'avatar.png'.
 * Все пути и имена нормализуются автоматически.
 *
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
        String key = s3KeyGenerator(folder, file.getOriginalFilename());
        this.s3Service.save(key, file);
        return presignedUrl(key);
    }

    @Override
    public InputStream load(String path) {
        String formattedPath = this.formattedPath(path);
        return this.s3Service.load(formattedPath);
    }

    @Override
    public void delete(String file) {
        this.s3Service.delete(this.fromPresignUrlToS3Key(file));
    }


    private String formattedPath(String path) {
        if (path == null || path.isEmpty()) {
            throw new BadRequestException("Путь является обязательным!");
        }

        return path.startsWith("/") ? path.substring(1) : path;
    }

    /**
     * Генерация URL для доступа к файлу через CDN.
     */
    private String presignedUrl(String s3Key) {
        return String.format("http://localhost:8080/cdn/%s", s3Key);
    }

    private String fromPresignUrlToS3Key(String presignedUrl) {
        String prefix = "http://localhost:8080/cdn/";
        if (presignedUrl.startsWith(prefix)) {
            return presignedUrl.substring(prefix.length());
        }
        return presignedUrl; // если вдруг не начинается с prefix
    }

    /**
     * Нормализует папку:
     * - убирает ведущие слэши
     * - убирает лишние слэши в конце
     * - добавляет один завершающий слэш
     */
    private String folderFormatter(String folder) {
        if (folder == null || folder.isEmpty()) return "";

        folder = folder.startsWith("/") ? folder.substring(1) : folder;
        folder = folder.endsWith("/") ? folder : folder + "/";

        return folder;
    }

    /**
     * Генерирует безопасное имя файла с UUID.
     */
    private String fileNameFormatter(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            fileName = "file";
        }
        return UUID.randomUUID() + "_" + fileName;
    }

    /**
     * Генерирует ключ для S3: folder + имя файла.
     */
    private String s3KeyGenerator(String folder, String fileName) {
        String formattedFolder = folderFormatter(folder);
        String formattedFileName = fileNameFormatter(fileName);
        return formattedFolder + formattedFileName;
    }
}