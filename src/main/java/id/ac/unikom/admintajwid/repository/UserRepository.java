package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUsername(String username);
}
