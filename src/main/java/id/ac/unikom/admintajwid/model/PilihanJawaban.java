package id.ac.unikom.admintajwid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_pilihan_jawaban")
@Data
public class PilihanJawaban {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String pilihan;

    @ManyToOne
    @JoinColumn(name = "id_soal")
    @JsonIgnore
    private Soal soal;

    @Column(nullable = false, name = "apakah_benar")
    private boolean apakahBenar;

    private String file;

    @Size(min = 1, max = 30)
    private String contentType;
}
