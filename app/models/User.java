package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by rownak on 3/27/17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    private String email;
    private String name;
    private String password;
    @Column(name = "user_type")
    private Integer userType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
