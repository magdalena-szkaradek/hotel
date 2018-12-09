package app.service;

import app.entity.User;
import app.entity.UserDTO;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createNewUser(User user) {
        user.setAmount_of_reservations(0);
        return userRepository.save(user);
    }

    public void deleteUser(Integer user_id) {
        userRepository.deleteById(user_id);
    }

    public Optional<User> findUser(Integer user_id) {
        return userRepository.findById(user_id);
    }

    public Optional<String> findByUserNameAndPass(String userName, String userPassword) {
        return userRepository.findByUserNameAndPassword(userName, userPassword);
    }

    public Iterable<User> getClients() {
        return userRepository.findClients();
    }

    public Iterable<User> getEmployees() {
        return userRepository.findEmployees();
    }

    public List<UserDTO> getAll() {
        Iterable<User> userIterable = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        userIterable.forEach(element -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(element.getUser_id());
            userDTO.setNameAndSurname(element.getName() + " " + element.getSurname());
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }
}
