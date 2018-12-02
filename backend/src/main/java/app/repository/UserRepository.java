package app.repository;

import app.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {


    @Query("SELECT t.name, t.employee, t.id FROM User t where t.name = ?1 AND t.password = ?2")
    public Optional<String> findByUserNameAndPassword(String name, String password);

    @Query("SELECT t FROM User t where t.employee = false AND t.name <> 'admin' ")
    public Iterable<User> findClients();

    @Query("SELECT t FROM User t where t.employee = true AND t.name <> 'admin' ")
    public Iterable<User> findEmployees();

    @Query("SELECT user FROM User user where user.user_id = ?1")
    User finByUserId(Integer userId);

    @Query("SELECT user.amount_of_reservations FROM User user where user.user_id = ?1")
    Integer getAmountOfReservation(Integer userId);

}


