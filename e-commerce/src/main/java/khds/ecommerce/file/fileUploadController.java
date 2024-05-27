package khds.ecommerce.file;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
