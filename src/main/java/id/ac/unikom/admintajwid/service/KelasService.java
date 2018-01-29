package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.model.Kelas;

import java.util.List;

public interface KelasService {

    Kelas findById(Integer id);

    List<Kelas> findAll();

}
