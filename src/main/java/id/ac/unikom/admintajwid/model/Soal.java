package id.ac.unikom.admintajwid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_soal")
//@Data
public class Soal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String pertanyaan;

    @OneToMany(mappedBy = "soal", cascade = CascadeType.ALL)
    private List<PilihanJawaban> pilihanJawabans;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date")
    private Date createdDate;

    private String file;

    @Size(min = 1, max = 30)
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "id_materi")
    @JsonIgnore
    private Materi materi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public List<PilihanJawaban> getPilihanJawabans() {
        return pilihanJawabans;
    }

    public void setPilihanJawabans(List<PilihanJawaban> pilihanJawabans) {
        this.pilihanJawabans = pilihanJawabans;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Materi getMateri() {
        return materi;
    }

    public void setMateri(Materi materi) {
        this.materi = materi;
    }
}
