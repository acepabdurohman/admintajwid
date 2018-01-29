package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.repository.KelasRepository;
import id.ac.unikom.admintajwid.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KelasServiceImpl implements KelasService {

    @Autowired
    private KelasRepository kelasRepository;

    @Override
    public Kelas findById(Integer id) {
        return kelasRepository.findOne(id);
    }

    @Override
    public List<Kelas> findAll() {
        return (List<Kelas>) kelasRepository.findAll();
    }
}
