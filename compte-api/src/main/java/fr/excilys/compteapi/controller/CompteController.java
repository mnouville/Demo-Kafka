package fr.excilys.compteapi.controller;

import fr.excilys.compteapi.exceptions.ElementNotFoundException;
import fr.excilys.compteapi.model.Compte;
import fr.excilys.compteapi.service.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comptes/")
@CrossOrigin
public class CompteController {

    private CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    /**
     * Méthode GET pour obtenir un compte en fonction de son ID.
     *
     * @param id Long
     * @return Objet de type ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Compte> getById(@PathVariable Long id) {
        Optional<Compte> compte = this.compteService.getById(id);

        if (compte.isPresent()) {
            return ResponseEntity.ok(compte.get());
        }

        throw new ElementNotFoundException();
    }

    /**
     * Méthode POST pour ajouter un nouveau compte.
     *
     * @param compte Objet de type Compte
     * @return Objet de type ResponseEntity
     */
    @PostMapping(value = "/")
    public ResponseEntity<Long> add(@RequestBody Compte compte) {
        Optional<Long> compteId = this.compteService.add(compte);

        if (compteId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(compteId.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    /**
     * Méthode DELETE pour supprimer un compte en fonction de son ID.
     *
     * @param id Long
     * @return Objet de type ResponseEntity
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.compteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Méthode PUT permettant de modifier un compte existant.
     *
     * @param compte Objet de type compte
     * @return Objet de type ResponseEntity
     */
    @PutMapping(value = "/")
    public ResponseEntity<Void> update(@RequestBody Compte compte) {
        this.compteService.update(compte);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
