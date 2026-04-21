package com.mamta.springwebdemo.api;

import com.mamta.springwebdemo.entity.Error;
import com.mamta.springwebdemo.entity.User;
import com.mamta.springwebdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    //    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET,consumes = {"application/json","application/xml"}, produces = "application/json")
    //    public ResponseEntity<?> getUserById(@PathVariable("id")Long id){
    //        try{
    //            User
    //        }
    //    }

    public ResponseEntity<?> getUser(@RequestHeader(value = "User-Agent",required = false) String userAgent,
        @RequestParam(value = "firstname",required = false) String firstname,
                                     @RequestParam(value ="lastname",required = false) String lastname
                                     ){
        List<User> allUsers = userService.getAllUser();
        System.out.println("User-Agent:" + userAgent + "");
        System.out.println("firstname: " + firstname + " " + "lastname:" +lastname);
        if(allUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus. NOT_FOUND).header("X-RESPONDER","PRAGRA").body(
                    Error.builder().code("NOT-FOUND 404").message(!userAgent.toUpperCase().contains("POSTMAN") ?
                            "No users found" : "No users  found for POSTMAN").build()

            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("/api/download")
    public ResponseEntity<?> downloadDoc(){
        try{
            byte[] bytes = Files.readAllBytes(Paths.get("D:/Java/Pragra/Notes/rest_api.pdf"));
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=rest_api.pdf")
                    .body(bytes);
        } catch (IOException e) {
            System.out.println("e.getMessage()" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(Error.builder().code("NOT FOUND").message("Files Not Found"));
    }




}
