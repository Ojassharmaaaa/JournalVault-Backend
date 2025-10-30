 package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


 @RestController
@RequestMapping("/User")
public class UserController {

     @Autowired
     private UserService userService;
     @Autowired
     private UserRepository userRepository;

//     @GetMapping("/Id/{myId}")
//     public ResponseEntity<User> getJournalEntryById(@PathVariable ObjectId myId)
//     {
//         Optional<User> user=userService.findbyId(myId);
//         if(user.isPresent())
//         {
//             return new ResponseEntity<>(user.get(), HttpStatus.OK);
//         }
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//     }

     @PutMapping()
     public ResponseEntity<?>UpdateUser(@RequestBody User user) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName = authentication.getName();
         User userInDb = userService.findByUserName(userName);
        if(userInDb!=null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
        }
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

     }
     @DeleteMapping
     public ResponseEntity<?>deleteUser(@RequestBody User user) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         userRepository.deleteByUserName(user.getUserName());
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);

     }






 }

