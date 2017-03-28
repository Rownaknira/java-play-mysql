package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by rownak on 3/27/17.
 */
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "auth_token")
    private String authToken;
    @Column(name = "user_id")
    private Integer userId;
    private Integer status;
    @Column(name = "login_time")
    private Timestamp loginTime;
    @Column(name = "logout_time")
    private Timestamp logoutTime;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
}
