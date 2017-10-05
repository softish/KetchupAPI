package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by softish on 2017-10-04.
 */
public class TimedSessionDTO {

    private long userId;
    private long duration;

    @JsonCreator
    public TimedSessionDTO(@JsonProperty("userId") long userId,
                           @JsonProperty("duration") long duration) {
        this.userId = userId;
        this.duration = duration;
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
}
