
package co.com.mutantanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.mutantanalyzer.model.Mutant;


/**
 * Descripción Repositorio de la tabla de mutantes
 * 
 * @author felipe.lopera
 *
 */
@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {


    /**
     * Cosnutla todos los mutantes que se tienen guardados
     * @return
     */
    Long countByMutantTrue();

}
