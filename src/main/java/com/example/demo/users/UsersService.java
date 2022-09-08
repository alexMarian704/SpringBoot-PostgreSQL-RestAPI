package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    public void addNewUser(Users users) {
       Optional<Users> userByEmail = usersRepository.findUsersByEmail(users.getEmail());
       if(userByEmail.isPresent()){
           throw new IllegalStateException("email taken");
       }
       usersRepository.save(users);
    }

    public void deleteUser(Long id){
        boolean exists = usersRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("not found");
        }
        usersRepository.deleteById(id);
    }
    @Transactional
    public void updateUser(Long id, String email) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new IllegalStateException("not found"));
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<Users> userByEmail = usersRepository.findUsersByEmail(email);
            if(userByEmail.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }
}
