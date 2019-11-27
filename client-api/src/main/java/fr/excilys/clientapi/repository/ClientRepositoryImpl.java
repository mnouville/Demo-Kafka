package fr.excilys.clientapi.repository;

import fr.excilys.clientapi.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private static final String QUERY_FIND_ALL = "from Client";

    @PersistenceContext
    private final EntityManager entityManager;

    public ClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retourne potentiellement un Client en fonction de son ID.
     *
     * @param idClient Long
     * @return Soit un Objet de type Client
     * Soit ne retourne rien
     */
    @Override
    public Optional<Client> getById(Long idClient) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.of(session.get(Client.class, idClient));
    }

    /**
     * Retourne potentiellement l'ID du Client qui vient d'être créé.
     *
     * @param client Objet de type Client
     * @return Soit un Long
     * Soit ne retourne rien
     */
    @Override
    public Optional<Long> add(Client client) {

        if (client.getId() != null) {
            Session session = entityManager.unwrap(Session.class);
            return Optional.of((long) session.save(client));
        }

        return Optional.empty();
    }

    /**
     * Supprime un Client en fonction de son ID.
     *
     * @param idClient Long
     */
    @Override
    public void deleteById(Long idClient) {
        Session session = entityManager.unwrap(Session.class);
        Client client = session.get(Client.class, idClient);
        session.remove(client);
    }

    /**
     * Modifie un Client.
     *
     * @param client Objet de type Client
     */
    @Override
    public void update(Client client) {
        Session session = entityManager.unwrap(Session.class);
        session.get(Client.class, client.getId());
        session.merge(client);
    }
}
