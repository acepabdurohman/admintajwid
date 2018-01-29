package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.SiswaKelas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SiswaKelasRepository extends CrudRepository<SiswaKelas, Integer> {

    List<SiswaKelas> findByKelas_Id(Integer id);

}
