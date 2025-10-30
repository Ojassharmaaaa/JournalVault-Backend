package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/All-users")
    public ResponseEntity<?> getAllUsers(){
        List<User>all=userService.getAll();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/Create-admin")
    public void createAdmin(@RequestBody User user){
        userService.saveNewAdmin(user);
    }

}
