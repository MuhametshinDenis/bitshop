package github.muhametshindenis.bitshop.modules.storage.service;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Интерфейс {@link StorageService} для работы с файлами в S3 и генерации URL через CDN.
 * <p>
 * Сервис предоставляет методы для:
 * <ul>
 *     <li>сохранения файлов в S3 с генерацией CDN URL;</li>
 *     <li>загрузки файлов по пути или CDN URL;</li>
 *     <li>удаления файлов из S3.</li>
 * </ul>
 * <p>
 * Контракт не определяет внутреннюю реализацию, все пути и имена должны быть корректно нормализованы
 * в реализации.
 *
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
public interface StorageService {
    /**
     * Сохраняет файл в указанную папку S3 и возвращает CDN URL.
     *
     * @param folder папка для сохранения (например "avatars/")
     * @param file   файл для сохранения
     * @return URL для доступа к файлу через CDN
     * @throws IOException если произошла ошибка при сохранении файла
     */
    String save(String folder, MultipartFile file) throws IOException;

    /**
     * Загружает файл из S3 по указанному пути.
     *
     * @param folder путь к файлу (может быть как presigned URL, так и ключ S3)
     * @return {@link InputStream} файла
     * @throws BadRequestException если путь пустой или null
     */
    InputStream load(String folder);

    /**
     * Удаляет файл из S3.
     *
     * @param file presigned URL или ключ файла в S3
     */
    void delete(String file);
}
