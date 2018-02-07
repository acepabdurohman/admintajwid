package id.ac.unikom.admintajwid.service.impl;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import id.ac.unikom.admintajwid.dto.SiswaScore;
import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.model.Quiz;
import id.ac.unikom.admintajwid.model.SiswaKelas;
import id.ac.unikom.admintajwid.repository.MateriRepository;
import id.ac.unikom.admintajwid.repository.QuizRepository;
import id.ac.unikom.admintajwid.repository.SiswaKelasRepository;
import id.ac.unikom.admintajwid.service.QuizService;
import id.ac.unikom.admintajwid.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private MateriRepository materiRepository;
    @Autowired
    private SiswaKelasRepository siswaKelasRepository;
    @Autowired
    private DateConverter dateConverter;

    @Override
    public Quiz findOne(Integer id) {
        return quizRepository.findOne(id);
    }

    @Override
    public List<Quiz> findByIdKelasAndStatusSiswa(Kelas kelas, boolean status) {
        List<Quiz> quizzes = quizRepository.findByIdKelasAndStatusSiswa(kelas.getId(), status);
        return quizzes;
    }

    @Override
    public void save(SiswaScore siswaScore) throws java.text.ParseException {
        Quiz quiz = new Quiz();
        Materi materi = materiRepository.findOne(siswaScore.getIdMateri());
        SiswaKelas siswaKelas = siswaKelasRepository.findOne(siswaScore.getIdSiswaKelas());
        quiz.setMateri(materi);
        quiz.setSiswaKelas(siswaKelas);
        Date currentDate = dateConverter.convert("GMT+7");
        quiz.setTanggal(currentDate);
        quiz.setNilai(siswaScore.getNilai());
        quizRepository.save(quiz);
    }

    @Override
    public void delete(Quiz quiz) {
        quizRepository.delete(quiz);
    }
}
