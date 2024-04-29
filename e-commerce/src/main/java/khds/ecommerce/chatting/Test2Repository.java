package khds.ecommerce.chatting;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Test2Repository extends MongoRepository<ChattingContent, String> {
    ChattingContent findChattingContentByName(String name);
}
