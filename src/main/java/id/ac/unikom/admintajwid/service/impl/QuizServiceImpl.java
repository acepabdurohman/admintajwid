package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.dto.SiswaScore;
import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.model.Quiz;
import id.ac.unikom.admintajwid.model.SiswaKelas;
import id.ac.unikom.admintajwid.repository.MateriRepository;
import id.ac.unikom.admintajwid.repository.QuizRepository;
import id.ac.unikom.admintajwid.repository.SiswaKelasRepository;
import id.ac.unikom.admintajwid.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private MateriRepository materiRepository;
    @Autowired
    private SiswaKelasRepository siswaKelasRepository;

    @Override
    public List<Quiz> findByIdKelas(Kelas kelas) {
        List<Quiz> quizzes = quizRepository.findByIdKelas(kelas.getId());
        quizzes.forEach(quiz -> {
            System.out.println("ID Kelas : " + quiz.getId());
        });
        return quizzes;
    }

    @Override
    public void save(SiswaScore siswaScore) {
        Quiz quiz = new Quiz();
        Materi materi = materiRepository.findOne(siswaScore.getIdMateri());
        SiswaKelas siswaKelas = siswaKelasRepository.findOne(siswaScore.getIdSiswaKelas());
        quiz.setMateri(materi);
        quiz.setSiswaKelas(siswaKelas);
        quiz.setTanggal(new Date());
        quiz.setNilai(siswaScore.getNilai());
        quizRepository.save(quiz);
    }
}
