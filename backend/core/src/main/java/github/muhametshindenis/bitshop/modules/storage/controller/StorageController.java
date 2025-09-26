package github.muhametshindenis.bitshop.modules.storage.controller;

import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
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
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{*path}")
    public ResponseEntity<?> load(@PathVariable("path") String path) {
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=\"" + Path.of(path).getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(this.storageService.load(path)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.storageService.save(path, file));
    }

    @DeleteMapping("{*path}")
    public ResponseEntity<?> delete(@PathVariable("path") String path) {
        this.storageService.delete(path);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
