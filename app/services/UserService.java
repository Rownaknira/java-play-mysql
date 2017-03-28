package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import daos.UserDAO;
import exceptions.ApplicationException;
import models.User;

/**
 * Created by rownak on 3/27/17.
 */
@Singleton
public class UserService {
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void create(User user) {
        if (userDAO.selectByEmail(user.getEmail()) != null) {
            throw new ApplicationException("User with this email exists");
        }
        user.setUserType(0);
        userDAO.createUser(user);
    }
}
