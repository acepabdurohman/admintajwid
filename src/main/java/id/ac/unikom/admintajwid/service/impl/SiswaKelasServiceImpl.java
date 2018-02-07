package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import id.ac.unikom.admintajwid.model.SiswaKelas;
import id.ac.unikom.admintajwid.repository.KelasRepository;
import id.ac.unikom.admintajwid.repository.SiswaKelasRepository;
import id.ac.unikom.admintajwid.repository.SiswaRepository;
import id.ac.unikom.admintajwid.service.SiswaKelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SiswaKelasServiceImpl implements SiswaKelasService {

    @Autowired
    private SiswaKelasRepository siswaKelasRepository;
    @Autowired
    private SiswaRepository siswaRepository;
    @Autowired
    private KelasRepository kelasRepository;

    @Override
    public List<SiswaKelas> findByIdKelasAndStatus(Integer id, boolean status) {
        return siswaKelasRepository.findByKelas_IdAndSiswa_Status(id, status);
    }

    @Override
    @Transactional
    public void save(SiswaKelas siswaKelas) {
        Integer idSiswa = siswaKelas.getSiswa().getId();
        String nomorInduk = siswaKelas.getSiswa().getNomorInduk();
        String password = siswaKelas.getSiswa().getPassword();
        String namaLengkap = siswaKelas.getSiswa().getNamaLengkap();
        Siswa siswa = siswaRepository.findOne(idSiswa);
        siswa.setNomorInduk(nomorInduk);
        siswa.setNamaLengkap(namaLengkap);
        siswa.setPassword(password);
        siswa.setStatus(true);
        Integer idKelas = siswaKelas.getKelas().getId();
        Kelas kelas = kelasRepository.findOne(idKelas);
        siswaKelas.setSiswa(siswa);
        siswaKelas.setKelas(kelas);
        Set<SiswaKelas> sk = new HashSet<>();
        sk.add(siswaKelas);
        siswa.setSiswaKelases(sk);
        siswaRepository.save(siswa);
    }

    @Override
    public SiswaKelas findByIdAndStatus(Integer id, boolean status) {
        return siswaKelasRepository.findByIdAndSiswa_Status(id, status);
    }
}
