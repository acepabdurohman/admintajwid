package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.model.Soal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SoalService {
    List<Soal> findAll();

    Soal findById(Integer id);

    void save(Soal soal, Materi materi, MultipartFile filePertanyaan, MultipartFile fileJawaban1, MultipartFile fileJawaban2,
              MultipartFile fileJawaban3, MultipartFile fileJawaban4) throws Exception;

    void delete(Integer id);

    List<Soal> findListsByIdMateriRandom(Integer idMateri);

    List<Soal> findByIdMateri(Integer id);
}
