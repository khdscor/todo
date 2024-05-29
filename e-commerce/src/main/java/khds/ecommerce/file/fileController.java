package khds.ecommerce.file;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class fileController {

    @Value("${file.upload.path}")
    private String filepath;

    private final FileRepository fileRepository;

    @PostMapping("image")
    public ResponseEntity<List<FileEntity>> uploadImage(@RequestParam(value = "file") MultipartFile[] files) {
        List<FileEntity> entities = new ArrayList<>();
        for (MultipartFile file : files) {
            // 파일 이름을 생성, 중복 시 번호 증가 ex. (1), (2)...
            String fileName = generateFileName(Objects.requireNonNull(file.getOriginalFilename()));
            // 서버 정적 저장소에 파일 저장
            saveFile(file, fileName);
            // DB에 저장할 파일 담기
            entities.add(new FileEntity(fileName, new Date()));
        }
        // DB에 저장
        return ResponseEntity.ok().body(fileRepository.saveAll(entities));
    }

    private String generateFileName(String originalFileName) {
        int lastIndexOfDot = originalFileName.lastIndexOf(".");
        String name = originalFileName.substring(0, lastIndexOfDot);
        // 확장자
        String extension = originalFileName.substring(lastIndexOfDot);
        int fileNumber = 1;
        // 이름 중복 시 증가할 순번
        String fileSequence = "";
        while (new File(filepath + name + fileSequence + extension).exists()) {
            fileSequence = "(" + fileNumber + ")";
            fileNumber++;
        }
        return name + fileSequence + extension;
    }

    private void saveFile(MultipartFile file, String fileName) {
        File targetFile = new File(filepath + fileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new RuntimeException("File not saved");
        }
    }

    @GetMapping("find/files")
    public ResponseEntity<List<FileEntity>> findFiles() {
        return ResponseEntity.ok().body(fileRepository.findAll());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        byte[] fileContent = readFileContent(fileName);
        return createResponseEntity(encodedFileName, fileContent);
    }

    private byte[] readFileContent(String fileName) {
        Path filePath = Paths.get(filepath).resolve(fileName).normalize();
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    private ResponseEntity<byte[]> createResponseEntity(String fileName, byte[] fileContent) {
        String cleanFileName = StringUtils.cleanPath(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", cleanFileName);
        return ResponseEntity.ok()
            .headers(headers)
            .body(fileContent);
    }
}