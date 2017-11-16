package app.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by softish on 2017-10-04.
 */
@Entity
@Table(name = "timed_sessions")
public class TimedSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private long duration;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    private String task;

    protected TimedSession() {

    }

    public TimedSession(User user, long duration) {
        this.user = user;
        this.duration = duration;
    }

    public TimedSession(User user, long duration, String task, Date endDateTime) {
        this.user = user;
        this.duration = duration;
        this.task = task;
        this.endDateTime = endDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "TimedSession{" +
                "id=" + id +
                ", user=" + user +
                ", duration=" + duration +
                ", endDateTime=" + endDateTime +
                ", task='" + task + '\'' +
                '}';
    }
}
