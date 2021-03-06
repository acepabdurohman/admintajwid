package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

    @Query("select q from Quiz q where q.siswaKelas.kelas.id = :idKelas and q.siswaKelas.siswa.status = :status")
    List<Quiz> findByIdKelasAndStatusSiswa(@Param("idKelas") Integer idKelas, @Param("status") boolean status);

}
