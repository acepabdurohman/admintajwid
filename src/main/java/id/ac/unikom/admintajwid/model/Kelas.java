package id.ac.unikom.admintajwid.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Data
@Table(name = "t_kelas")
public class Kelas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nama;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kelas", cascade = CascadeType.ALL)
    private Set<SiswaKelas> siswaKelases;

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

    public Set<SiswaKelas> getSiswaKelases() {
        return siswaKelases;
    }

    public void setSiswaKelases(Set<SiswaKelas> siswaKelases) {
        this.siswaKelases = siswaKelases;
    }
}
