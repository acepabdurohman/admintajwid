package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiswaRepository extends CrudRepository<Siswa, Integer> {

    @Query(nativeQuery = true, value = "select * from t_siswa s where s.nomor_induk = :username and binary s.password = :password")
    Siswa findByNomorIndukAndPassword(@Param("username") String username, @Param("password") String password);

}
