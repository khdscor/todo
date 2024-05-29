package khds.ecommerce.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "file") // 테이블 명을 작성
public class FileEntity {

    @Id // primary key임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false, unique = true, length = 1000)
    private String filename;

    @Column(name = "create_date")
    private Date newDate;

    public FileEntity(String filename, Date newDate) {
        this.filename = filename;
        this.newDate = newDate;
    }
}
