package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.dto.SiswaScore;
import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> findByIdKelas(Kelas kelas);

    void save(SiswaScore siswaScore);
}
