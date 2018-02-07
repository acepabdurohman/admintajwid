package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.dto.SiswaScore;
import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import id.ac.unikom.admintajwid.model.SiswaKelas;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SiswaService {

    void save(Siswa siswa, Kelas kelas);

    void delete(Siswa siswa);

    Siswa findByUsernameAndPasswordAndStatus(String username, String password, boolean status);
}
