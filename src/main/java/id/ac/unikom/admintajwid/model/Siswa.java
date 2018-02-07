package id.ac.unikom.admintajwid.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
//@Data
@Table(name = "t_siswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nomor_induk", unique = true)
    @Size(min = 1, max = 17)
    @NotNull
    private String nomorInduk;

    @NotNull
    @Size(min = 1, max = 8)
    private String password;


    @NotNull
    @Column(name = "nama_lengkap")
    @Size(min = 1, max = 50)
    private String namaLengkap;

    @OneToMany(mappedBy = "siswa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SiswaKelas> siswaKelases;

    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomorInduk() {
        return nomorInduk;
    }

    public void setNomorInduk(String nomorInduk) {
        this.nomorInduk = nomorInduk;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Set<SiswaKelas> getSiswaKelases() {
        return siswaKelases;
    }

    public void setSiswaKelases(Set<SiswaKelas> siswaKelases) {
        this.siswaKelases = siswaKelases;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
