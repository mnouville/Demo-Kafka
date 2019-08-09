package fr.excilys.clientapi.service;

import fr.excilys.clientapi.model.Client;
import fr.excilys.clientapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Retourne potentiellement un Client en fonction de son ID.
     *
     * @param idClient Long
     * @return Soit un Objet de type Client
     * Soit ne retourne rien
     */
    public Optional<Client> getById(Long idClient) {
        return this.clientRepository.getById(idClient);
    }

    /**
     * Retourne potentiellement l'ID du Client qui vient d'être créé.
     *
     * @param client Objet de type Client
     * @return Soit un Long
     * Soit ne retourne rien
     */
    public Optional<Long> add(Client client) {
        return this.clientRepository.add(client);
    }

    /**
     * Supprime un Client.
     *
     * @param client Objet de type Client
     */
    public void delete(Client client) {
        this.clientRepository.delete(client);
    }

    /**
     * Supprime un Client en fonction de son ID.
     *
     * @param id Long
     */
    public void deleteById(Long id) {
        this.clientRepository.deleteById(id);
    }

    /**
     * Modifie un Client.
     *
     * @param client Objet de type Client
     */
    public void update(Client client) {
        this.clientRepository.update(client);
    }

}
