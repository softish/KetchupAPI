package app.domain;

import java.util.Date;

/**
 * Created by softish on 2017-11-05.
 */
public class Statistic {

    private User user;
    private Date endDateTime;
    private long totalDuration;
    private String task;

    public Statistic(User user, Date endDateTime, long totalDuration, String task) {
        this.user = user;
        this.endDateTime = endDateTime;
        this.totalDuration = totalDuration;
        this.task = task;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "user=" + user +
                ", endDateTime=" + endDateTime +
                ", totalDuration=" + totalDuration +
                ", task='" + task + '\'' +
                '}';
    }
}
