package id.ac.unikom.admintajwid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;

    @ManyToOne
    @JoinColumn(name = "id_siswa_kelas")
    @JsonIgnore
    private SiswaKelas siswaKelas;

    @ManyToOne
    @JoinColumn(name = "id_materi")
    @JsonIgnore
    private Materi materi;

    private Integer nilai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public SiswaKelas getSiswaKelas() {
        return siswaKelas;
    }

    public void setSiswaKelas(SiswaKelas siswaKelas) {
        this.siswaKelas = siswaKelas;
    }

    public Materi getMateri() {
        return materi;
    }

    public void setMateri(Materi materi) {
        this.materi = materi;
    }

    public Integer getNilai() {
        return nilai;
    }

    public void setNilai(Integer nilai) {
        this.nilai = nilai;
    }
}
