package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path = "/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService=usersService;
    }

    @GetMapping(value = "/users")
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    @PostMapping(value = "/create/user")
    public void registerNewUser(@RequestBody Users users){
        usersService.addNewUser(users);
    }
    @DeleteMapping(value = "/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        usersService.deleteUser(id);
    }

    @PutMapping(value = "/update/{userId}")
    public  void updateUser(@PathVariable("userId") Long id,
                            @RequestParam(required = false) String email){
        usersService.updateUser(id , email);
    }
}
