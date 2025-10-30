package net.engineeringdigest.journalApp.Service;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        User user=userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);

    }
    public void saveEntry(JournalEntry journalEntry)
    {

        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findbyId(ObjectId id)
    {

        return journalEntryRepository.findById(id);
    }
    public void deleteById(Object id,String userName)
    {
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById((ObjectId) id);
    }
//    public List<JournalEntry> findByUserName(String userName){
//
//    }








}
