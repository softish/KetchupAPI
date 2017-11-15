package app.domain;

import java.util.Date;

/**
 * Created by softish on 2017-10-28.
 */
public class TimedSessionStatistic {

    private User user;
    private Date endDateTime;
    private long totalDuration;

    public TimedSessionStatistic(User user, Date endDateTime, long totalDuration) {
        this.user = user;
        this.endDateTime = endDateTime;
        this.totalDuration = totalDuration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    @Override
    public String toString() {
        return "TimedSessionStatistic{" +
                "user=" + user +
                ", endDateTime=" + endDateTime +
                ", totalDuration=" + totalDuration +
                '}';
    }
}
