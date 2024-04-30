package khds.ecommerce.chatting;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface Test3Repository extends ReactiveMongoRepository<ChattingContent, String> {
    Flux<ChattingContent> findChattingContentByName(String name);
}
