package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.model.Soal;
import id.ac.unikom.admintajwid.repository.MateriRepository;
import id.ac.unikom.admintajwid.repository.SoalRepository;
import id.ac.unikom.admintajwid.service.SoalService;
import id.ac.unikom.admintajwid.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SoalServiceImpl implements SoalService {

    @Autowired
    private SoalRepository soalRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private MateriRepository materiRepository;

    @Override
    public List<Soal> findAll() {
        List<Soal> soals = (List<Soal>) soalRepository.findAll();
        soals.forEach(soal -> {
            if (soal.getFile() != null) {
                try {
                    soal.setFile(storageService.getFileLink(soal.getFile()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            soal.getPilihanJawabans().forEach(pilihanJawaban -> {
                if (pilihanJawaban.getFile() != null) {
                    try {
                        pilihanJawaban.setFile(storageService.getFileLink(pilihanJawaban.getFile()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        return soals;
    }

    @Override
    public Soal findById(Integer id) {
        Soal soal = soalRepository.findOne(id);
        return soal;
    }

    @Override
    @Transactional
    public void save(Soal soal, Materi materi, MultipartFile filePertanyaan, MultipartFile fileJawaban1, MultipartFile fileJawaban2,
                     MultipartFile fileJawaban3, MultipartFile fileJawaban4) throws Exception {
        MultipartFile[] multipartFiles = new MultipartFile[4];
        multipartFiles[0] = fileJawaban1;
        multipartFiles[1] = fileJawaban2;
        multipartFiles[2] = fileJawaban3;
        multipartFiles[3] = fileJawaban4;
        System.out.println("Kesini");
        if (soal.getId() == null) { // create data
            soal.setCreatedDate(new Date());
            if (!filePertanyaan.isEmpty()) {
                soal.setFile(filePertanyaan.getOriginalFilename());
                soal.setContentType(filePertanyaan.getContentType());
                storageService.uploadFile(filePertanyaan);
            }
            final int[] i = {0};
            soal.getPilihanJawabans().forEach(pilihanJawaban -> {
                pilihanJawaban.setSoal(soal);
                if (!multipartFiles[i[0]].isEmpty()) {
                    pilihanJawaban.setFile(multipartFiles[i[0]].getOriginalFilename());
                    pilihanJawaban.setContentType(multipartFiles[i[0]].getContentType());
                    try {
                        storageService.uploadFile(multipartFiles[i[0]]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                i[0]++;
            });
            soal.setMateri(materi);
            Set<Soal> soals = new HashSet<>();
            soals.add(soal);
            materi.setSoals(soals);
            //soalRepository.save(soal);
            materiRepository.save(materi);
        } else { // update data
            Soal current = soalRepository.findOne(soal.getId());
            if (current != null) {
                current.setPertanyaan(soal.getPertanyaan());
                current.setCreatedDate(soal.getCreatedDate());
                if (!filePertanyaan.isEmpty()) {
                    current.setFile(filePertanyaan.getOriginalFilename());
                    current.setContentType(filePertanyaan.getContentType());
                    storageService.uploadFile(filePertanyaan);
                }
                final int[] i = {0};
                soal.getPilihanJawabans().forEach(pilihanJawaban -> {
                    current.getPilihanJawabans().get(i[0]).setPilihan(pilihanJawaban.getPilihan());
                    current.getPilihanJawabans().get(i[0]).setApakahBenar(pilihanJawaban.isApakahBenar());
                    if (!multipartFiles[i[0]].isEmpty()) {
                        current.getPilihanJawabans().get(i[0]).setFile(multipartFiles[i[0]].getOriginalFilename());
                        current.getPilihanJawabans().get(i[0]).setContentType(multipartFiles[i[0]].getContentType());
                        try {
                            storageService.uploadFile(multipartFiles[i[0]]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i[0]++;
                });
                Materi mtr = materiRepository.findOne(soal.getMateri().getId());
                current.setMateri(mtr);
                soalRepository.save(current);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        soalRepository.delete(id);
    }

    @Override
    public List<Soal> findListsByIdMateriRandom(Integer idMateri) {
        List<Soal> soals = soalRepository.findListsRandom(idMateri);
        setFileLink(soals);
        return soals;
    }

    private List<Soal> setFileLink(List<Soal> soals) {
        soals.forEach(soal -> {
            if (soal.getFile() != null) {
                try {
                    soal.setFile(storageService.getFileLink(soal.getFile()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            soal.getPilihanJawabans().forEach(pilihanJawaban -> {
                if (pilihanJawaban.getFile() != null) {
                    try {
                        pilihanJawaban.setFile(storageService.getFileLink(pilihanJawaban.getFile()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        return soals;
    }

    @Override
    public List<Soal> findByIdMateri(Integer id) {
        List<Soal> soals = soalRepository.findByMateri_Id(id);
        setFileLink(soals);
        return soals;
    }
}
