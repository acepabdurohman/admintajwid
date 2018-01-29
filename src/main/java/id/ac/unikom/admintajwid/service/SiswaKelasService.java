package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import id.ac.unikom.admintajwid.model.SiswaKelas;

import java.util.List;

public interface SiswaKelasService {

    List<SiswaKelas> findByIdKelas(Integer id);

    void save(SiswaKelas siswaKelas);

    SiswaKelas findById(Integer id);

}
