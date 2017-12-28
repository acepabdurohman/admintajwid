package id.ac.unikom.admintajwid.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void uploadFile(MultipartFile multipartFile) throws Exception;

    String getFileLink(String namaFile) throws Exception;
}
