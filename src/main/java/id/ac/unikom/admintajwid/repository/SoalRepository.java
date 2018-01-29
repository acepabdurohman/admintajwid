package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Soal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoalRepository extends CrudRepository<Soal, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM t_soal s WHERE s.id_materi = :idMateri ORDER BY RAND() LIMIT 10")
    List<Soal> findListsRandom(@Param("idMateri") Integer idMateri);

    List<Soal> findByMateri_Id(Integer idMateri);
}
