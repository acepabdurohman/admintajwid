package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Soal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SoalRepository extends CrudRepository<Soal, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM t_soal ORDER BY RAND() LIMIT 10")
    List<Soal> findListsRandom();
}
