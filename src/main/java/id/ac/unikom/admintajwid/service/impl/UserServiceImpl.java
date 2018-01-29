package id.ac.unikom.admintajwid.service.impl;

import id.ac.unikom.admintajwid.model.Authority;
import id.ac.unikom.admintajwid.model.User;
import id.ac.unikom.admintajwid.repository.AuthorityRepository;
import id.ac.unikom.admintajwid.repository.UserRepository;
import id.ac.unikom.admintajwid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        detailsChecker.check(user);
        return user;
    }

    @Override
    public void save() {
        String username = "admin";
        String password = "admin";
        User user = userRepository.findByUsername(username);
        if(user == null){ // jika belum ada maka buat default admin
            user = new User();
            user.setUsername(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setFirstName("Administrator");
            user.setLastName("Tajwid");
            user.setActive(1);
            Authority authority = authorityRepository.findByAuthority("ADMIN");
            user.setAuthorities(new HashSet<>(Arrays.asList(authority)));
            userRepository.save(user);
        }
    }

    @Override
    public User sendEmail(User user) {
        User currentUser = userRepository.findByUsername(user.getUsername());
        if(currentUser == null){
            return currentUser;
        }
        return currentUser;
    }
}