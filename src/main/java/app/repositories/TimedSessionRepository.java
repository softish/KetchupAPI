package app.repositories;

import app.domain.Statistic;
import app.domain.TimedSession;
import app.domain.TimedSessionStatistic;
import app.domain.User;
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

    @Query("SELECT new app.domain.TimedSessionStatistic(ts.user, ts.endDateTime, SUM(ts.duration)) " +
            "FROM TimedSession ts WHERE ts.user = :user and ts.endDateTime >= :endOfRangeDate " +
            "group by substring(ts.endDateTime, 1, 10)")
    List<TimedSessionStatistic> getCountInRange(@Param("user") User user, @Param("endOfRangeDate") Date endOfRangeDate);

    @Query("SELECT new app.domain.Statistic(ts.user, ts.endDateTime, SUM(ts.duration), ts.task) " +
            "FROM TimedSession ts WHERE ts.user = :user and ts.endDateTime >= :endOfRangeDate " +
            "group by substring(ts.endDateTime, 1, 10), ts.task")
    List<Statistic> getStatisticsInRange(@Param("user") User user, @Param("endOfRangeDate") Date endOfRangeDate);

    @Query("SELECT new app.domain.Statistic(ts.user, ts.endDateTime, SUM(ts.duration), ts.task) " +
            "FROM TimedSession ts WHERE ts.user = :user and substring(ts.endDateTime, 1, 10) = :date " +
            "group by substring(ts.endDateTime, 1, 10), ts.task")
    List<Statistic> getStatisticsForDate(@Param("user") User user, @Param("date") String date);
}
