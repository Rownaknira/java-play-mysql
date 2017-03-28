import com.google.inject.Inject;
import com.google.inject.Singleton;
import exceptions.ApplicationException;
import play.Configuration;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by rownak on 3/27/17.
 */
@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {
    @Inject
    public ErrorHandler(Configuration configuration, Environment environment, OptionalSourceMapper sourceMapper,
                        Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    @Override
    protected CompletionStage<Result> onNotFound(Http.RequestHeader request, String message) {
        return CompletableFuture.completedFuture(Results.notFound(Json.toJson("ROUTING ERROR")));
    }

    @Override
    public java.util.concurrent.CompletionStage<Result> onServerError(Http.RequestHeader request,
                                                                      java.lang.Throwable exception) {
        exception.printStackTrace();
        if (exception instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException)exception;
            return CompletableFuture.completedFuture(Results.ok(Json.toJson(ae.getMessage())));
        } else {
            return CompletableFuture.completedFuture(Results.ok(Json.toJson(exception.getMessage())));
        }
    }
}
