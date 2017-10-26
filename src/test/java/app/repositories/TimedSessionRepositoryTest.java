package app.repositories;

import app.domain.TimedSession;
import app.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TimedSessionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TimedSessionRepository timedSessionRepository;

    @Test
    public void getRange() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
        Date rangeDate = simpleDateFormat.parse("2017-10-20 20:00:00+02:00");

        User user = new User("foo", "foo");
        entityManager.persist(user);
        createSessions(user);

        List<TimedSession> timedSessionList = timedSessionRepository.getRange(user, rangeDate);
        assertEquals(4, timedSessionList.size());
    }

    private Date getDate(int dayOfMonth, int hour) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
        return simpleDateFormat.parse("2017-10-" + dayOfMonth + " " + hour + ":00:00+02:00");
    }

    private void createSessions(User user) {
        for (int i = 0; i < 24; i++) {
            try {
                entityManager.persist(new TimedSession(user, 20 * 60 * 1000, "PR #" + i, getDate(i, i)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        entityManager.flush();
    }

}