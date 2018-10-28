package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public Iterable<User> getAllUsers(){

       return userService.getAllUsers();
    }

    //register
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addUser")
    public ResponseEntity addNewUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.OK);
    }

    //get user by id
    @GetMapping("/getUser/{user_id}")
    public Optional<User> getUser(@PathVariable Integer user_id){
        return userService.findUser(user_id);
    }
    @DeleteMapping("/delete/{user_id}")
    public void deleteUser(@PathVariable Integer user_id){
        userService.deleteUser(user_id);
    }


}
