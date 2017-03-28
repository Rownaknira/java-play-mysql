package controllers;

import authentication.AuthenticateUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dtos.UserRequestDTO;
import models.User;
import org.modelmapper.ModelMapper;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;

/**
 * Created by rownak on 3/27/17.
 */
@Security.Authenticated(AuthenticateUser.class)
public class UserController extends Controller {
    private final FormFactory formFactory;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Inject
    public UserController(FormFactory formFactory, ModelMapper modelMapper, UserService userService) {
        this.formFactory = formFactory;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public Result createUser() {
        Form<UserRequestDTO> userForm = formFactory.form(UserRequestDTO.class).bindFromRequest();

        if (userForm.hasErrors()) {
            JsonNode jsonError = userForm.errorsAsJson();
            return badRequest(jsonError);
        }
        User user = modelMapper.map(userForm.get(), User.class);
        userService.create(user);

        return ok(Json.toJson("Successfully created"));
    }
}
