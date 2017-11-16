package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Domain Transfer Object representation of aggregated sessions.
 */
public class TimedSessionStatisticDTO {

    private long userId;
    private String date;
    private long totalDuration;

    @JsonCreator
    public TimedSessionStatisticDTO(@JsonProperty("userId") long userId,
                                    @JsonProperty("date") String date,
                                    @JsonProperty("totalDuration") long totalDuration) {
        this.userId = userId;
        this.date = date;
        this.totalDuration = totalDuration;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }
}
