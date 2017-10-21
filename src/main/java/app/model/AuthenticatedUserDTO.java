package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by softish on 2017-10-05.
 */
public class AuthenticatedUserDTO {

    private long id;
    private String userName;

    @JsonCreator
    public AuthenticatedUserDTO(@JsonProperty("id") long id,
                                @JsonProperty("username")String username) {
        this.id = id;
        this.userName = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
