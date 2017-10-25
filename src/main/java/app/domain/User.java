package app.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by softish on 2017-10-05.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @Column(nullable = false, length = 80)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<TimedSession> timedSessions;

    protected User(){

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TimedSession> getTimedSessions() {
        return timedSessions;
    }

    public void setTimedSessions(List<TimedSession> timedSessions) {
        this.timedSessions = timedSessions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", timedSessions=" + timedSessions +
                '}';
    }
}
