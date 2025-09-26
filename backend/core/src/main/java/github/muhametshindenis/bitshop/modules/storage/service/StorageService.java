package github.muhametshindenis.bitshop.modules.storage.service;

import github.muhametshindenis.bitshop.modules.storage.dto.SaveStorageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
public interface StorageService {
    String save(String folder, MultipartFile file) throws IOException;
    InputStream load(String folder);
    void delete(String file);
}
