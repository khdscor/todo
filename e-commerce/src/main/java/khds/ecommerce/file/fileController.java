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
import lombok.RequiredArgsConstructor;
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

    final String filepath = "C:/Users/khdsc/IdeaProjects/e-commerce/e-commerce/src/main/resources/static/images/";

    private final FileRepository fileRepository;

    @PostMapping("image")
    public ResponseEntity<List<FileEntity>> uploadImage(
        @RequestParam(value = "file") MultipartFile[] files) {
        List<FileEntity> entities = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            int lastIndexOfDot = fileName.lastIndexOf(".");
            String name = fileName.substring(0, lastIndexOfDot);
            String extension = fileName.substring(lastIndexOfDot);
            int fileNumber = 1;
            String fileSequence = "";
            while (new File(filepath + URLEncoder.encode(name, StandardCharsets.UTF_8)
                    + fileSequence + extension).exists()) {
                fileSequence = "(" + fileNumber + ")";
                fileNumber++;
            }
            File f1 = new File(filepath + URLEncoder.encode(name, StandardCharsets.UTF_8)
                    + fileSequence + extension);
            try {
                files[i].transferTo(f1);
            } catch (IOException e) {
                throw new RuntimeException("File not saved");
            }

            entities.add(FileEntity.builder()
                .filename(name+ fileSequence + extension)
                .newDate(new Date())
                .build());
        }
        return ResponseEntity.ok().body(fileRepository.saveAll(entities));
    }

    @GetMapping("find/files")
    public ResponseEntity<List<FileEntity>> findFiles() {
        return ResponseEntity.ok().body(fileRepository.findAll());
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        Path filePath = Paths.get(filepath).resolve(encodedFileName).normalize();
        byte[] fileContent;

        try {
            fileContent = Files.readAllBytes(filePath);

            String cleanFileName = StringUtils.cleanPath(encodedFileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", cleanFileName);

            return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}