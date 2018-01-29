package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.dto.MateriList;
import id.ac.unikom.admintajwid.model.Materi;

import java.util.List;

public interface MateriService {

    Materi findById(Integer id);

    List<MateriList> findOnlyIdAndName();
}
