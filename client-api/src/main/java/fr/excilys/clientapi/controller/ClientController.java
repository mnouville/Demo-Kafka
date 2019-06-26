package fr.excilys.clientapi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.excilys.clientapi.exceptions.ElementNotFoundException;
import fr.excilys.clientapi.model.Client;
import fr.excilys.clientapi.service.ClientService;

@RestController
@RequestMapping("/api/clients/")
@CrossOrigin
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Méthode GET pour obtenir un client en fonction de son ID.
     *
     * @param id Long
     * @return Objet de type ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Optional<Client> client = this.clientService.getById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        throw new ElementNotFoundException();
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
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    /**
     * Méthode DELETE pour supprimer un client.
     *
     * @param client Objet de type Client
     * @return Objet de type ResponseEntity
     */
    @DeleteMapping(value = "/")
    public ResponseEntity<Void> delete(@RequestBody Client client) {
        this.clientService.delete(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
