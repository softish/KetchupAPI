package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Domain Transfer Object representation of a time range.
 */
public class SessionRangeDTO {

    private long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ssX")
    private Date endOfRangeDate;

    @JsonCreator
    public SessionRangeDTO(@JsonProperty("userId") long userId,
                           @JsonProperty("endOfRangeDate") Date endOfRangeDate) {
        this.userId = userId;
        this.endOfRangeDate = endOfRangeDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getEndOfRangeDate() {
        return endOfRangeDate;
    }

    public void setEndOfRangeDate(Date endOfRangeDate) {
        this.endOfRangeDate = endOfRangeDate;
    }
}
