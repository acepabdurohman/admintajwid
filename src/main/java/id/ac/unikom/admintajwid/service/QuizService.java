package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.dto.SiswaScore;
import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Quiz;

import java.text.ParseException;
import java.util.List;

public interface QuizService {

    List<Quiz> findByIdKelasAndStatusSiswa(Kelas kelas, boolean status);

    Quiz findOne(Integer id);

    void save(SiswaScore siswaScore) throws ParseException;

    void delete(Quiz quiz);
}
