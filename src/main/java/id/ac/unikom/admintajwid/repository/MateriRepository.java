package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Materi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MateriRepository extends CrudRepository<Materi, Integer> {

    @Query("select m.id, m.nama from Materi m")
    List<Object> findAllMateri();
}
