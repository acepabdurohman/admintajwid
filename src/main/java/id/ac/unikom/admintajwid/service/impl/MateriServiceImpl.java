package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.dto.MateriList;
import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.repository.MateriRepository;
import id.ac.unikom.admintajwid.service.MateriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriServiceImpl implements MateriService {

    @Autowired
    private MateriRepository materiRepository;

    @Override
    public Materi findById(Integer id) {
        return materiRepository.findOne(id);
    }

    @Override
    public List<MateriList> findOnlyIdAndName() {
        List<Object> objects = materiRepository.findAllMateri();
        List<MateriList> materiLists = new ArrayList<>();
        for(Object materi : objects){
            Object[] m = (Object[]) materi;
            Integer id = (Integer) m[0];
            String nama = (String) m[1];
            MateriList materiList = new MateriList();
            materiList.setId(id);
            materiList.setNama(nama);
            materiLists.add(materiList);
        }
        return materiLists;
    }
}
