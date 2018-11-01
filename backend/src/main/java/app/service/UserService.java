package app.service;

import app.entity.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

     public Iterable<User> getAllUsers(){
         return userRepository.findAll();
     }

     public User createNewUser(User user){
         return userRepository.save(user);
     }

     public void deleteUser(Integer user_id){
         userRepository.deleteById(user_id);
     }

     public Optional<User> findUser(Integer user_id){
        return userRepository.findById(user_id);
     }

     public Optional<String> findByUserNameAndPass(String userName, String userPassword){
         return userRepository.findByUserNameAndPassword(userName, userPassword);
     }

}
