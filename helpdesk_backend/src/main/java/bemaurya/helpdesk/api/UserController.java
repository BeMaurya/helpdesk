package bemaurya.helpdesk.api;

import bemaurya.helpdesk.business.abstracts.UserServices;
import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dto.request.user.*;
import bemaurya.helpdesk.entities.concreates.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@Tag(name = "User APIs")
public class UserController {

    // this is field injection
    private final UserServices userServices;

    // this is constructor injection
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    // this method is used to add user
    @Operation(summary = "Add User")
    @PostMapping("/")
    public Result add(@RequestBody UserRequest user, @RequestHeader String token) {
        return userServices.add(user, token);
    }

    // this method is used to get all users
    @Operation(summary = "Get All Users")
    @GetMapping("/")
    public ResultData<List<User>> getAll(@RequestHeader String token) {
        return userServices.getAll(token);
    }

    // this method is used to delete user
    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id, @RequestHeader String token) {
        return userServices.delete(id, token);
    }


    // this method is used to update user basic info
    @Operation(summary = "Update User Basic Info")
    @PutMapping("/{id}")
    public Result updateBasicInfo(@RequestBody UpdateUserBasicInfo user, @RequestHeader String token) {
        return userServices.updateBasicInfo(user, token);

    }

    // this method is used to update user password
    @Operation(summary = "Update User Password")
    @PutMapping("/{id}/password")
    public Result updatePassword(@RequestBody UpdateUserPasswordRequest user, @RequestHeader String token) {
        return userServices.updatePassword(user, token);
    }

    // this method is used to get user by id
    @Operation(summary = "Get User By Id")
    @GetMapping("/{id}")
    public ResultData<User> getById(@PathVariable Long id, @RequestHeader String token) {
        return userServices.getById(id, token);
    }

    // this method used to fetch user information with tokens.
    @Operation
    @GetMapping("/me")
    public ResultData<User> getMyInfo(@RequestHeader String token) {
        return userServices.getMyInfo(token);
    }

    // this method get all supporters
    @Operation(summary = "get all supporters")
    @GetMapping("/supporters")
    public ResultData<List<User>> getAllSupporters(@RequestHeader String token) {
        return userServices.getAllSupporters(token);
    }
}
