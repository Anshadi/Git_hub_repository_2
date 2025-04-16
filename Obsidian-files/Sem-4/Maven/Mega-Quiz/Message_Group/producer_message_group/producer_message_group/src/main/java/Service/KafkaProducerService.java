package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired 
    private KafkaTemplate<String,String> kafkaTemplate ;

public ResponseEntity<String> sendMessage (String groupId , String Message)
{
    String Topic = "chat-group"+groupId ;
    kafkaTemplate.send(Topic,Message);   
    return ResponseEntity.ok("Message sent");
}

    
}
