package net.engineeringdigest.journalApp.Repository;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByuserName(String userName);

    void deleteByUserName(String userName);
}
