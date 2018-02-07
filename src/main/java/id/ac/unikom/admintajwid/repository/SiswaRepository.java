package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiswaRepository extends CrudRepository<Siswa, Integer> {

    @Query(nativeQuery = true, value = "select * from t_siswa s where s.nomor_induk = :username and binary s.password = :password" +
            " and s.status = :status")
    Siswa findByNomorIndukAndPasswordAndStatus(@Param("username") String username, @Param("password") String password,
                                               @Param("status") boolean status);

    @Query("update Siswa s set s.status = false where s.id = :id")
    @Modifying
    void delete(@Param("id") Integer id);

    Siswa findByNomorIndukAndStatus(String nomorInduk, boolean status);

}
