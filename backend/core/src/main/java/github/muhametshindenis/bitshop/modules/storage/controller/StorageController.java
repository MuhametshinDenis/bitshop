package github.muhametshindenis.bitshop.modules.storage.controller;

import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
@RestController
@RequestMapping("/cdn")
@Tag(name = "CDN", description = "API для управления файлами")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Operation(summary = "Загрузить файл", description = "Возвращает файл по указанному пути. Используется Content-Disposition inline.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл успешно возвращен"),
            @ApiResponse(responseCode = "400", description = "Некорректный путь"),
            @ApiResponse(responseCode = "404", description = "Файл не найден")
    })
    @GetMapping("/{*path}")
    public ResponseEntity<?> load(@Parameter(description = "Путь к файлу в S3/CDN", required = true) @PathVariable("path") String path) {
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=\"" + Path.of(path).getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(this.storageService.load(path)));
    }

    @Operation(summary = "Сохранить файл", description = "Сохраняет файл в S3 и возвращает CDN URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл успешно сохранен"),
            @ApiResponse(responseCode = "400", description = "Ошибка при сохранении файла")
    })
    @PostMapping
    public ResponseEntity<?> save(@Parameter(description = "Файл для сохранения", required = true)
                                      @RequestParam("file") MultipartFile file,
                                  @Parameter(description = "Путь для сохранения файла", required = true)
                                  @RequestParam("path") String path) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.storageService.save(path, file));
    }

    @Operation(summary = "Удалить файл", description = "Удаляет файл по указанному пути")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл успешно удален"),
            @ApiResponse(responseCode = "400", description = "Некорректный путь"),
            @ApiResponse(responseCode = "404", description = "Файл не найден")
    })
    @DeleteMapping("{*path}")
    public ResponseEntity<?> delete(@Parameter(description = "Путь к файлу в S3/CDN", required = true)
                                        @PathVariable("path") String path) {
        this.storageService.delete(path);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
