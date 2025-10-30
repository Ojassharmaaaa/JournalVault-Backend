package net.engineeringdigest.journalApp.Service;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user)
    {
        userRepository.save(user);
    }
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public void saveNewAdmin(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> findbyId(ObjectId id)
    {
        return userRepository.findById(id);
    }

    public void deleteById(Object id)
    {
        userRepository.deleteById((ObjectId) id);
    }

    public User findByUserName(String userName)
    {
        return userRepository.findByuserName(userName);
    }






}
