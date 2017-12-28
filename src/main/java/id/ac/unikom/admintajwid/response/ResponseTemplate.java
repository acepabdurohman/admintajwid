package id.ac.unikom.admintajwid.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseTemplate {
    private Integer status;
    private String message;
    private Object data;
}
