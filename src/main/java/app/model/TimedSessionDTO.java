package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by softish on 2017-10-04.
 */
public class TimedSessionDTO {

    private long userId;
    private long duration;
    private String task;

    @JsonCreator
    public TimedSessionDTO(@JsonProperty("userId") long userId,
                           @JsonProperty("duration") long duration,
                           @JsonProperty("task") String task) {
        this.userId = userId;
        this.duration = duration;
        this.task = task;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}
