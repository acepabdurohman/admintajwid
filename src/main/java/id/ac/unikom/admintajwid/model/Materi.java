package id.ac.unikom.admintajwid.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_materi")
//@Data
public class Materi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nama;

    @OneToMany(mappedBy = "materi", cascade = CascadeType.ALL)
    private Set<Soal> soals;

    @OneToMany(mappedBy = "materi", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Set<Soal> getSoals() {
        return soals;
    }

    public void setSoals(Set<Soal> soals) {
        this.soals = soals;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}