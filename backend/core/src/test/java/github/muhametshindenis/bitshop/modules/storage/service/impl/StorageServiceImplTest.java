package github.muhametshindenis.bitshop.modules.storage.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.modules.s3.service.S3Service;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.09.2025 September 2025
 */
@DisplayName("Unit тесты для сервиса Storage")
@ExtendWith(MockitoExtension.class)
class StorageServiceImplTest {
    @Mock
    private S3Service s3Service;

    @InjectMocks
    private StorageServiceImpl storageServiceImpl;

    @Test
    void save_WhenValidFolderAndFileProvided_ShouldSaveFileAndReturnPresignUrl() throws IOException {
        //given
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("example.txt");

        //when
        String result = storageServiceImpl.save("folder/", file);

        //then
        verify(s3Service, times(1)).save(anyString(), eq(file));
        assertTrue(result.startsWith("http://localhost:8080/cdn/folder/"));
        assertTrue(result.endsWith("_example.txt"));
    }

    @Test
    void save_WhenFileHasNoNane_ShouldSaveFileAndReturnPresignUrl() throws IOException {
        //given
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn(null);

        //when
        String result = storageServiceImpl.save("folder", file);

        //then
        verify(s3Service, times(1)).save(anyString(), eq(file));
        assertTrue(result.startsWith("http://localhost:8080/cdn/folder/"));
    }

    @Test
    void save_WhenFolderHasNoNane_ShouldSaveFileAndReturnPresignUrl() throws IOException {
        //given
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("example.txt");

        //when
        String result = storageServiceImpl.save(null, file);

        //then
        verify(s3Service, times(1)).save(anyString(), eq(file));
        assertTrue(result.startsWith("http://localhost:8080/cdn/"));
        assertTrue(result.endsWith("_example.txt"));
    }

    @Test
    void save_WhenFolderAndFileHasNoNane_ShouldSaveFileAndReturnPresignUrl() throws IOException {
        //given
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn(null);

        //when
        String result = storageServiceImpl.save(null, file);

        //then
        verify(s3Service, times(1)).save(anyString(), eq(file));
        assertTrue(result.startsWith("http://localhost:8080/cdn/"));
    }

    @Test
    void load_WhenPathIsNull_ShouldThrowBadRequestException() {
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> storageServiceImpl.load(null));

        assertEquals("Путь является обязательным!", exception.getMessage());
    }

    @Test
    void load_WhenPathIsEmpty_ShouldThrowBadRequestException() {
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> storageServiceImpl.load(""));

        assertEquals("Путь является обязательным!", exception.getMessage());
    }

    @Test
    void load_WhenValidPath_ShouldCallS3LoadAndReturnInputStream() {
        // given
        String path = "/folder/file.txt";
        InputStream mockedStream = mock(InputStream.class);
        when(s3Service.load("folder/file.txt")).thenReturn(mockedStream);

        // when
        InputStream result = storageServiceImpl.load(path);

        // then
        verify(s3Service, times(1)).load("folder/file.txt");
        assertSame(mockedStream, result);
    }

    @Test
    void delete_WhenValidPresignedUrl_ShouldCallS3DeleteWithCorrectKey() {
        // given
        String presignedUrl = "http://localhost:8080/cdn/folder/file.txt";
        String expectedKey = "folder/file.txt";

        // when
        storageServiceImpl.delete(presignedUrl);

        // then
        verify(s3Service, times(1)).delete(expectedKey);
    }
}