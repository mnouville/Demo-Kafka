package fr.excilys.clientapi.repository;

import fr.excilys.clientapi.model.Client;

import java.util.Optional;

public interface ClientRepository {

    /**
     * Retourne potentiellement un Client en fonction de son ID.
     *
     * @param idClient Long
     * @return Soit un Objet de type Client
     * Soit ne retourne rien
     */
    Optional<Client> getById(Long idClient);

    /**
     * Retourne potentiellement l'ID du Client qui vient d'être créé.
     *
     * @param client Objet de type Client
     * @return Soit un Long
     * Soit ne retourne rien
     */
    Optional<Long> add(Client client);

    /**
     * Supprime un Client.
     *
     * @param client Objet de type Client
     */
    void delete(Client client);

    /**
     * Supprime un Client en fonction de son ID.
     *
     * @param idClient Long
     */
    void deleteById(Long idClient);

    /**
     * Modifie un Client.
     *
     * @param client Objet de type Client
     */
    void update(Client client);

}
