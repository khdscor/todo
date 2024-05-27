package khds.ecommerce.file;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
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
    public FileEntity uploadImage(HttpServletRequest request,
        @RequestParam(value="file", required = false) MultipartFile[] files,
        @RequestParam(value="comment", required = false) String comment) {

        String FileNames = "";

        String originFileName = files[0].getOriginalFilename();
        long fileSize = files[0].getSize();
        String safeFile = System.currentTimeMillis() + originFileName;

        File f1 = new File(filepath + safeFile);
        try {
            files[0].transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final FileEntity file = FileEntity.builder()
            .filename(safeFile)
            .newDate(new Date())
            .build();

        return fileRepository.save(file);
    }
}
