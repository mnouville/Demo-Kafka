package fr.excilys.compteapi.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.excilys.compteapi.model.MessageClientKafka;
import fr.excilys.compteapi.service.CompteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ClientConsumer {

    private ObjectMapper objectMapper;

    private CompteService compteService;

    public ClientConsumer(CompteService compteService) {
        this.compteService = compteService;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Consumer mis en place sur le topic operationsCompte. Ce dernier écoute en continue sur ce topic en attendant des messages.
     *
     * @param message String
     * @throws IOException
     */
    @KafkaListener(topics = "operationsCompte", groupId = "group_id")
    public void consume(String message) throws IOException {
        MessageClientKafka messageKafka = objectMapper.readValue(message, MessageClientKafka.class);

        switch (messageKafka.getFunction()) {
            case "credit":
                compteService.credit(messageKafka.getIdClient(), messageKafka.getContent());
                break;
            case "debit":
                compteService.debit(messageKafka.getIdClient(), messageKafka.getContent());
                break;
            default:
                log.info(String.format("Consumed Message -> ", message));
                break;
        }
    }

}
