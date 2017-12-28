package id.ac.unikom.admintajwid.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_soal")
@Data
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

}
