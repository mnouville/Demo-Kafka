package fr.excilys.clientapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.excilys.clientapi.exceptions.NoElementException;
import fr.excilys.clientapi.kafka.producer.ClientProducer;
import fr.excilys.clientapi.model.Client;
import fr.excilys.clientapi.model.MessageClientKafka;
import fr.excilys.clientapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clients/")
@CrossOrigin
public class ClientController {

    private ClientService clientService;

    private ClientProducer clientProducer;

    private ObjectMapper objectMapper;

    public ClientController(ClientService clientService, ClientProducer clientProducer) {
        this.clientService = clientService;
        this.clientProducer = clientProducer;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping(value = "/test")
    public void test() {
        this.clientProducer.sendMessage("Coucou c'est le message test du client producer");
    }

    /**
     * Méthode POST permettant de réaliser des opérations bancaires diverses.
     *
     * @param messageClientKafka Objet de type MessageClientKafka
     * @return Objet de type ResponseEntity
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/operationBancaire")
    public ResponseEntity<String> operationBancaire(@RequestBody MessageClientKafka messageClientKafka) throws JsonProcessingException {
        this.clientProducer.sendMessage(objectMapper.writeValueAsString(messageClientKafka));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Méthode GET pour obtenir un client en fonction de son ID.
     *
     * @param id Long
     * @return Objet de type ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Client> client = this.clientService.getById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            throw new NoElementException();
        }
    }

    /**
     * Méthode POST pour ajouter un nouveau client.
     *
     * @param client Objet de type Client
     * @return Objet de type ResponseEntity
     */
    @PostMapping(value = "/")
    public ResponseEntity<Long> add(@RequestBody Client client) {
        Optional<Long> clientId = this.clientService.add(client);

        if (clientId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientId.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    /**
     * Méthode DELETE pour supprimer un client en fonction de son ID.
     *
     * @param id Long
     * @return Objet de type ResponseEntity
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Méthode PUT permettant de modifier un client existant.
     *
     * @param client Objet de type Client
     * @return Objet de type ResponseEntity
     */
    @PutMapping(value = "/")
    public ResponseEntity<Void> update(@RequestBody Client client) {
        this.clientService.update(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
