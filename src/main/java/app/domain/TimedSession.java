package app.domain;

import javax.persistence.*;

/**
 * Created by softish on 2017-10-04.
 */
@Entity
@Table(name = "timed_sessions")
public class TimedSession {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private long duration;

    protected TimedSession() {

    }

    public TimedSession(User user, long duration) {
        this.user = user;
        this.duration = duration;
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
}
