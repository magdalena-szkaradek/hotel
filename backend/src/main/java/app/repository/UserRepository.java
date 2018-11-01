package app.repository;

import app.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {


    @Query("SELECT t.name FROM User t where t.name = ?1 AND t.password = ?2")
    public Optional<String> findByUserNameAndPassword(String name, String password);
}


