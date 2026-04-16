package com.mamta.springwebdemo.api;

import com.mamta.springwebdemo.entity.Error;
import com.mamta.springwebdemo.entity.User;
import com.mamta.springwebdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAPI {
    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    @Operation(summary = "Get All Users",description = "Returns list  of all users in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "External Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )
    })

    public ResponseEntity<?> getUser(){
        List<User> allUsers = userService.getAllUser();
        if(allUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus. NOT_FOUND).body(
                    Error.builder().code("NOT-FOUND 404").message(
                            "Not found Addrs").build()

            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);


    }
}
