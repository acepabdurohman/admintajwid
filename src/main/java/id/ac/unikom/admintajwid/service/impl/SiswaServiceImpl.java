package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import id.ac.unikom.admintajwid.model.SiswaKelas;
import id.ac.unikom.admintajwid.repository.KelasRepository;
import id.ac.unikom.admintajwid.repository.SiswaKelasRepository;
import id.ac.unikom.admintajwid.repository.SiswaRepository;
import id.ac.unikom.admintajwid.service.SiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class SiswaServiceImpl implements SiswaService {

    @Autowired
    private SiswaRepository siswaRepository;
    @Autowired
    private KelasRepository kelasRepository;
    @Autowired
    private SiswaKelasRepository siswaKelasRepository;

    @Override
    @Transactional
    public void save(Siswa siswa, Kelas kelas) {
        SiswaKelas siswaKelas = new SiswaKelas();
        siswaKelas.setSiswa(siswa);
        siswaKelas.setKelas(kelas);
        Set<SiswaKelas> sk = new HashSet<>();
        sk.add(siswaKelas);
        siswa.setSiswaKelases(sk);
        siswaRepository.save(siswa);
    }

    @Override
    @Transactional
    public void delete(Siswa siswa) {
        siswaRepository.delete(siswa.getId());
    }

    @Override
    public Siswa findByUsernameAndPasswordAndStatus(String username, String password, boolean status) {
        Siswa siswa = siswaRepository.findByNomorIndukAndPasswordAndStatus(username, password, status);
        return siswa;
    }
}
