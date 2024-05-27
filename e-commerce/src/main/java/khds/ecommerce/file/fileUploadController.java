package khds.ecommerce.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fileUploadController {

    @GetMapping("/file/upload")
    public ResponseEntity<Void> upload(){

        return ResponseEntity.ok().build();
    }
}
