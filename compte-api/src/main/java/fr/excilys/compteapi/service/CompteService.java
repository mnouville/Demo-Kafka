package fr.excilys.compteapi.service;

import fr.excilys.compteapi.model.Compte;
import fr.excilys.compteapi.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {

    private CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    /**
     * Retourne potentiellement un Compte en fonction de son ID.
     *
     * @param idCompte Long
     * @return Soit un Objet de type Compte
     *         Soit ne retourne rien
     */
    public Optional<Compte> getById(Long idCompte) {
        return this.compteRepository.getById(idCompte);
    }

    /**
     * Retourne potentiellement l'ID du Compte qui vient d'être créé.
     *
     * @param compte Objet de type Compte
     * @return Soit un Long
     *         Soit ne retourne rien
     */
    public Optional<Long> add(Compte compte) {
        return this.compteRepository.add(compte);
    }

    /**
     * Supprime un Compte.
     *
     * @param compte Objet de type Compte
     */
    public void delete(Compte compte) {
        this.compteRepository.delete(compte);
    }

    /**
     * Supprime un Compte en fonction de son ID.
     *
     * @param id Long
     */
    public void deleteById(Long id) {
        this.compteRepository.deleteById(id);
    }

    /**
     * Modifie un Compte.
     *
     * @param compte Objet de type Compte
     */
    public void update(Compte compte) {
        this.compteRepository.update(compte);
    }

}
