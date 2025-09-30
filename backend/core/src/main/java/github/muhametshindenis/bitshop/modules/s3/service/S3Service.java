package github.muhametshindenis.bitshop.modules.s3.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Интерфейс {@link S3Service} для работы с файлами в Amazon S3.
 * <p>
 * Контракт предоставляет методы для:
 * <ul>
 *     <li>сохранения файлов в S3 с указанием ключа;</li>
 *     <li>загрузки файлов из S3 по ключу;</li>
 *     <li>удаления файлов из S3 по ключу.</li>
 * </ul>
 * <p>
 * Реализация интерфейса должна заботиться о корректной обработке ошибок и
 * обеспечении корректного подключения к S3.
 *
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @see <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html">AWS S3Client Java SDK v2</a>
 * @since 13.09.2025 September 2025
 */
public interface S3Service {
    /**
     * Сохраняет файл в S3 по указанному ключу.
     *
     * @param key           уникальный ключ для файла в S3
     * @param multipartFile файл для сохранения
     * @throws IOException если произошла ошибка при чтении файла или записи в S3
     */
    void save(String key, MultipartFile multipartFile) throws IOException;

    /**
     * Загружает файл из S3 по указанному ключу.
     *
     * @param key ключ файла в S3
     * @return {@link InputStream} для чтения содержимого файла
     */
    InputStream load(String key);

    /**
     * Удаляет файл из S3 по указанному ключу.
     *
     * @param key ключ файла в S3
     */
    void delete(String key);
}
