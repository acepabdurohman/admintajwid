package id.ac.unikom.admintajwid.repository;

import id.ac.unikom.admintajwid.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
