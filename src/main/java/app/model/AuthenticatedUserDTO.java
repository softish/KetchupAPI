package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Domain Transfer Object representation of a authenticated user.
 */
public class AuthenticatedUserDTO {

    private long id;
    private String username;

    @JsonCreator
    public AuthenticatedUserDTO(@JsonProperty("id") long id,
                                @JsonProperty("username") String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
