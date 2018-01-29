package id.ac.unikom.admintajwid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_siswa_kelas")
//@Data
public class SiswaKelas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_siswa")
    @JsonIgnore
    private Siswa siswa;

    @ManyToOne
    @JoinColumn(name = "id_kelas")
    @JsonIgnore
    private Kelas kelas;

    @OneToMany(mappedBy = "siswaKelas", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Siswa getSiswa() {
        return siswa;
    }

    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
