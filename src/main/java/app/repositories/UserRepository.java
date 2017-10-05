package app.repositories;

import app.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by softish on 2017-10-05.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
