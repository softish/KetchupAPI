package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by softish on 2017-11-05.
 */
public class StatisticDTO {

    private long userId;
    private String date;
    private long totalDuration;
    private String task;

    @JsonCreator
    public StatisticDTO(@JsonProperty("userId") long userId,
                                    @JsonProperty("date") String date,
                                    @JsonProperty("totalDuration") long totalDuration,
                                    @JsonProperty("task") String task) {
        this.userId = userId;
        this.date = date;
        this.totalDuration = totalDuration;
        this.task = task;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
