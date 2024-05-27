package khds.ecommerce.file;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
public class fileUploadController {

    final String filepath = "C:/Users/khdsc/IdeaProjects/e-commerce/e-commerce/src/main/resources/static/images/";

    private final FileRepository fileRepository;

    @PostMapping("image")
    public ResponseEntity<List<FileEntity>> uploadImage(@RequestParam(value = "file") MultipartFile[] files) {
        List<FileEntity> entities = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String originFileName = files[i].getOriginalFilename();
            long fileSize = files[i].getSize();
            String safeFile = System.currentTimeMillis() + originFileName;

            File f1 = new File(filepath + safeFile);
            try {
                files[i].transferTo(f1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            entities.add(FileEntity.builder()
                .filename(safeFile)
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
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName)
        throws MalformedURLException {
        try {
            Path filePath = Paths.get(filepath).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + StringUtils.cleanPath(resource.getFilename()) + "\"")
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UrlResource(filepath + "error.jpg"));
        }
    }
}
