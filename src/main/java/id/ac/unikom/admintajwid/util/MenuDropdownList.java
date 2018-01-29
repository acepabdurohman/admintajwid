package id.ac.unikom.admintajwid.util;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.repository.KelasRepository;
import id.ac.unikom.admintajwid.repository.MateriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuDropdownList {
    @Autowired
    private MateriRepository materiRepository;
    @Autowired
    private KelasRepository kelasRepository;

    public List<Materi> findAllMateri(){
        return (List<Materi>) materiRepository.findAll();
    }

    public List<Kelas> findAllKelas(){
        return (List<Kelas>) kelasRepository.findAll();
    }
}
