package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import daos.SessionDAO;
import daos.UserDAO;
import exceptions.ApplicationException;
import models.Session;
import models.User;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by rownak on 3/28/17.
 */
@Singleton
public class SessionService {
    private final SessionDAO sessionDAO;
    private final UserDAO userDAO;

    @Inject
    public SessionService(SessionDAO sessionDAO, UserDAO userDAO) {
        this.sessionDAO = sessionDAO;
        this.userDAO = userDAO;
    }

    public String login(User user) {
        User dbUser = userDAO.selectByEmail(user.getEmail());
        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())) {
            throw new ApplicationException("User doesn't exist with this credentials");
        }

        Session session = new Session();
        session.setAuthToken(UUID.randomUUID().toString());
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+06:00"));
        session.setLoginTime(new java.sql.Timestamp(c.getTime().getTime()));
        session.setStatus(1);
        session.setUserId(dbUser.getId());
        return sessionDAO.createSession(session);
    }

    public Session authenticate(String authToken) {
        return sessionDAO.selectByAuthToken(authToken);
    }
}
