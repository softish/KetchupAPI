package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by softish on 2017-10-05.
 */
public class UserDTO {

    private String userName;
    private String password;

    @JsonCreator
    public UserDTO(@JsonProperty("username")String username,
                   @JsonProperty("password")String password) {
        this.userName = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
