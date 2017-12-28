package id.ac.unikom.admintajwid.exception;

import id.ac.unikom.admintajwid.response.ResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseTemplate handleDataNotFound(Exception e){
        e.printStackTrace();
        ResponseTemplate responseTemplate = new ResponseTemplate(404, "Data tidak ditemukan", null);
        return responseTemplate;
    }
}
