package github.muhametshindenis.bitshop.modules.storage.util;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;

import java.util.UUID;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.09.2025 September 2025
 */
public class StorageUtils {
    /**
     * Форматирует путь, убирая ведущий слэш.
     *
     * @param path путь к файлу
     * @return отформатированный путь
     * @throws BadRequestException если путь null или пустой
     */
    public static String formattedPath(String path) {
        if (path == null || path.isEmpty()) {
            throw new BadRequestException("Путь является обязательным!");
        }

        return path.startsWith("/") ? path.substring(1) : path;
    }

    /**
     * Генерация URL для доступа к файлу через CDN.
     *
     * @param s3Key ключ файла в S3
     * @return URL через CDN
     */
    public static String presignedUrl(String s3Key) {
        return String.format("http://localhost:8080/cdn/%s", s3Key);
    }

    /**
     * Преобразует CDN URL обратно в ключ S3.
     *
     * @param presignedUrl URL через CDN
     * @return ключ файла в S3
     */
    public static String fromPresignUrlToS3Key(String presignedUrl) {
        String prefix = "http://localhost:8080/cdn/";
        if (presignedUrl.startsWith(prefix)) {
            return presignedUrl.substring(prefix.length());
        }
        return presignedUrl;
    }

    /**
     * Нормализует имя папки для S3:
     * - убирает ведущие слэши
     * - добавляет завершающий слэш
     *
     * @param folder имя папки
     * @return отформатированная папка
     */
    public static String folderFormatter(String folder) {
        if (folder == null || folder.isEmpty()) return "";

        folder = folder.startsWith("/") ? folder.substring(1) : folder;
        folder = folder.endsWith("/") ? folder : folder + "/";

        return folder;
    }

    /**
     * Форматирует имя файла, добавляя UUID для уникальности.
     *
     * @param fileName имя файла
     * @return уникальное имя файла с UUID
     */
    public static String fileNameFormatter(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            fileName = "file";
        }
        return UUID.randomUUID() + "_" + fileName;
    }

    /**
     * Генерирует ключ для S3 в формате: folder + fileName.
     *
     * @param folder   имя папки
     * @param fileName имя файла
     * @return ключ для S3
     */
    public static String s3KeyGenerator(String folder, String fileName) {
        String formattedFolder = folderFormatter(folder);
        String formattedFileName = fileNameFormatter(fileName);
        return formattedFolder + formattedFileName;
    }
}
