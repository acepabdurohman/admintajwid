package id.ac.unikom.admintajwid.service;

import id.ac.unikom.admintajwid.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save();

}
