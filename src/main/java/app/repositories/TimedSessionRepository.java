package app.repositories;

import app.domain.TimedSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by softish on 2017-10-04.
 */
public interface TimedSessionRepository extends CrudRepository<TimedSession, Long> {
}
