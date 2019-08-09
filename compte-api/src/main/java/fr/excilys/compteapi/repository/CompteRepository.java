package fr.excilys.compteapi.repository;

import fr.excilys.compteapi.model.Compte;

import java.util.Optional;

public interface CompteRepository {

    /**
     * Retourne potentiellement un Compte en fonction de son ID.
     *
     * @param idCompte Long
     * @return Soit un Objet de type Compte
     * Soit ne retourne rien
     */
    public Optional<Compte> getById(Long idCompte);

    /**
     * Retourne potentiellement l'ID du Compte qui vient d'être créé.
     *
     * @param compte Objet de type compte
     * @return Soit un Long
     * Soit ne retourne rien
     */
    public Optional<Long> add(Compte compte);

    /**
     * Supprime un Compte.
     *
     * @param c Objet de type Compte
     */
    public void delete(Compte c);

    /**
     * Supprime un Compte en fonction de son ID.
     *
     * @param idCompte Long
     */
    public void deleteById(Long idCompte);

    /**
     * Modifie un Compte.
     *
     * @param compte Objet de type Compte
     */
    public void update(Compte compte);

    /**
     * Retourne un objet de type Compte correspondant à son propriétaire.
     *
     * @param idClient Long
     * @return Soit un objet de type Compte
     * Soit ne retourne rien
     */
    public Optional<Compte> getByIdClient(Long idClient);

    /**
     * Permet de crediter un compte d'un certain montant.
     *
     * @param value    Double
     * @param idClient Long
     */
    public void credit(Long idClient, Double value);

    /**
     * Permet de debiter un compte d'un certain montant.
     *
     * @param value    Double
     * @param idClient Long
     */
    public void debit(Long idClient, Double value);
}
