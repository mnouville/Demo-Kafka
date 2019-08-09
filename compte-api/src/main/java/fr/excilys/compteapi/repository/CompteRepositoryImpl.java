package fr.excilys.compteapi.repository;

import fr.excilys.compteapi.model.Compte;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class CompteRepositoryImpl implements CompteRepository {

    private static final String QUERY_FIND_ALL = "from Compte";
    private static final String QUERY_FIND_BY_ID_CLIENT = "from Compte where idClient = :idClient";

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
        log.info("Update : " + compte.toString());
        Session session = entityManager.unwrap(Session.class);
        session.get(Compte.class, compte.getId());
        session.merge(compte);
    }

    /**
     * Retourne un objet de type Compte correspondant à son propriétaire.
     *
     * @param idClient Long
     * @return Soit un objet de type Compte
     * Soit ne retourne rien
     */
    @Override
    public Optional<Compte> getByIdClient(Long idClient) {
        Session session = entityManager.unwrap(Session.class);
        Query<Compte> query = session.createQuery(QUERY_FIND_BY_ID_CLIENT);
        query.setParameter("idClient", idClient);
        List<Compte> result = (List<Compte>) query.list();
        return Optional.of(result.get(0));
    }

    /**
     * Permet de crediter un compte d'un certain montant.
     *
     * @param value    Double
     * @param idClient Long
     */
    @Override
    public void credit(Long idClient, Double value) {
        log.info("methode credit");
        Optional<Compte> compteClient = getByIdClient(idClient);

        if (compteClient.isPresent()) {

            Double solde = compteClient.get().getSolde();
            compteClient.get().setSolde(solde + value);
            log.info("Methode credit : " + compteClient.get().toString());

            update(compteClient.get());
        }
    }

    /**
     * Permet de debiter un compte d'un certain montant.
     *
     * @param value    Double
     * @param idClient Long
     */
    @Override
    public void debit(Long idClient, Double value) {
        Optional<Compte> compteClient = getByIdClient(idClient);

        if (compteClient.isPresent()) {
            Double solde = compteClient.get().getSolde();
            compteClient.get().setSolde(solde - value);
            update(compteClient.get());
        }
    }
}
