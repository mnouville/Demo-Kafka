package fr.excilys.compteapi.repository;

import fr.excilys.compteapi.model.Compte;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class CompteRepositoryImpl implements CompteRepository {

    private static final String QUERY_FIND_ALL = "from Compte";

    @PersistenceContext
    private final EntityManager entityManager;

    public CompteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retourne potentiellement un Compte en fonction de son ID.
     *
     * @param idCompte Long
     * @return Soit un Objet de type Compte
     * Soit ne retourne rien
     */
    @Override
    public Optional<Compte> getById(Long idCompte) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.of(session.get(Compte.class, idCompte));
    }

    /**
     * Retourne potentiellement l'ID du Compte qui vient d'être créé.
     *
     * @param compte Objet de type compte
     * @return Soit un Long
     * Soit ne retourne rien
     */
    @Override
    public Optional<Long> add(Compte compte) {

        if (compte.getId() != null) {
            Session session = entityManager.unwrap(Session.class);
            return Optional.of((long) session.save(compte));
        }

        return Optional.empty();
    }

    /**
     * Supprime un Compte.
     *
     * @param c Objet de type Compte
     */
    @Override
    public void delete(Compte c) {
        Session session = entityManager.unwrap(Session.class);
        Compte compte = session.get(Compte.class, c.getId());
        session.remove(compte);
    }

    /**
     * Supprime un Compte en fonction de son ID.
     *
     * @param idCompte Long
     */
    @Override
    public void deleteById(Long idCompte) {
        Session session = entityManager.unwrap(Session.class);
        Compte compte = session.get(Compte.class, idCompte);
        session.remove(compte);
    }

    /**
     * Modifie un Compte.
     *
     * @param compte Objet de type Compte
     */
    @Override
    public void update(Compte compte) {
        Session session = entityManager.unwrap(Session.class);
        session.get(Compte.class, compte.getId());
        session.merge(compte);
    }
}
