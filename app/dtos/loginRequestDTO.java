package dtos;

import play.data.validation.Constraints;

/**
 * Created by rownak on 3/28/17.
 */
public class loginRequestDTO {
    @Constraints.Required
    private String email;
    @Constraints.Required
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
