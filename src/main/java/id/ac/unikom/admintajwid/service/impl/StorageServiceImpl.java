package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.service.StorageService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${minio.host}")
    private String host;
    @Value("${minio.port}")
    private String port;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.bucket.kuis}")
    private String bucketKuis;

    private MinioClient minioClient;

    @Override
    public void uploadFile(MultipartFile multipartFile) throws Exception {
        minioClient = new MinioClient(host + ":" + port, accessKey, secretKey);
        boolean isExist = minioClient.bucketExists(bucketKuis);
        if (!isExist) {
            minioClient.makeBucket(bucketKuis);
        }
        String content = multipartFile.getContentType();
        System.out.println("content : " + content);
        minioClient.putObject(bucketKuis, multipartFile.getOriginalFilename(), multipartFile.getInputStream(),
                multipartFile.getContentType());
        System.out.println(multipartFile.getOriginalFilename() + " berhasil di upload");
    }

    @Override
    public String getFileLink(String namaFile) throws Exception {
        minioClient = new MinioClient( host + ":" + port, accessKey, secretKey);
        boolean isExist = minioClient.bucketExists(bucketKuis);
        if (!isExist) {
            minioClient.makeBucket(bucketKuis);
        }
        /*return minioClient.presignedGetObject(bucketKuis, namaFile, 60 * 60 * 24);*/
        return minioClient.presignedGetObject(bucketKuis, namaFile);
    }
}
