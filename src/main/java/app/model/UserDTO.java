package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by softish on 2017-10-05.
 */
public class UserDTO {

    private long id;
    private String userName;
    private String password;

    /*
    @JsonCreator
    public UserDTO(@JsonProperty("userName")String userName,
                   @JsonProperty("password")String password) {
        this.userName = userName;
        this.password = password;
    }
    */

    @JsonCreator
    public UserDTO(@JsonProperty("userId") long id,
                   @JsonProperty("userName")String userName) {
        this.id = id;
        this.userName = userName;
    }

    /*
    @JsonCreator
    public UserDTO(@JsonProperty("userId") long id,
                   @JsonProperty("userName")String userName,
                   @JsonProperty("password")String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    */

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
