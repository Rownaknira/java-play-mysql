package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dtos.loginRequestDTO;
import models.User;
import org.modelmapper.ModelMapper;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SessionService;

/**
 * Created by rownak on 3/28/17.
 */
public class SessionController extends Controller {
    private final FormFactory formFactory;
    private final ModelMapper modelMapper;
    private final SessionService sessionService;

    @Inject
    public SessionController(FormFactory formFactory, ModelMapper modelMapper, SessionService sessionService) {
        this.formFactory = formFactory;
        this.modelMapper = modelMapper;
        this.sessionService = sessionService;
    }

    public Result login() {
        Form<loginRequestDTO> loginForm = formFactory.form(loginRequestDTO.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            JsonNode jsonError = loginForm.errorsAsJson();
            return badRequest(jsonError);
        }
        User user = modelMapper.map(loginForm.get(), User.class);
        return ok(Json.toJson(sessionService.login(user)));
    }
}
