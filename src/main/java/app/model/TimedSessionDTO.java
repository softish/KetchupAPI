package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by softish on 2017-10-04.
 */
public class TimedSessionDTO {

    private long userId;
    private long duration;
    private String task;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ssX")
    private Date endDateTime;

    @JsonCreator
    public TimedSessionDTO(@JsonProperty("userId") long userId,
                           @JsonProperty("duration") long duration,
                           @JsonProperty("task") String task,
                           @JsonProperty("endDateTime") Date endDateTime) {
        this.userId = userId;
        this.duration = duration;
        this.task = task;
        this.endDateTime = endDateTime;
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

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
