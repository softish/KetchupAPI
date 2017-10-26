package app.repositories;

import app.domain.TimedSession;
import app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by softish on 2017-10-04.
 */
public interface TimedSessionRepository extends CrudRepository<TimedSession, Long> {
    List<TimedSession> findByUserId(long userId);

    @Query("SELECT ts FROM TimedSession ts WHERE ts.user = :user and ts.endDateTime >= :endOfRangeDate")
    List<TimedSession> getRange(@Param("user") User user, @Param("endOfRangeDate") Date endOfRangeDate);
}
