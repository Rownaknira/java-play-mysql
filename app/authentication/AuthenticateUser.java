package authentication;

import com.google.inject.Inject;
import models.Session;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Security;
import services.SessionService;

/**
 * Created by rownak on 3/28/17.
 */
public class AuthenticateUser extends Security.Authenticator {
    public final SessionService sessionService;

    @Inject
    public AuthenticateUser(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String getUsername(Http.Context ctx) {
        String authToken = ctx.request().getHeader("Authorization");

        if (authToken == null || authToken.trim().isEmpty() ||
                !authToken.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            return null;
        }

        Session session = sessionService.authenticate(authToken);
        if (session != null && session.getStatus() == 1) {
            return authToken;
        } else {
            return null;
        }
    }

    @Override
    public play.mvc.Result onUnauthorized(Http.Context ctx) {
        return unauthorized(Json.toJson("Unauthorized user"));
    }
}
