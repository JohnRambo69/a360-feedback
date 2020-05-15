package com.avenga.a360.controller;

import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.UserDto;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.User;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.SessionService;
import com.avenga.a360.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @Path("{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUserName(@PathParam("userName") String userName) {
        User user = userService.getUserByUsername(userName);
        UserDto userDto = userService.userToUserDto(user);

        return Application.validator(user, userDto,"User dosen't exist" );
    }

    @Path("/getallusers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDto> userDtoList = userService.userListToUserDtoList(userList);
        return Application.validator(userList, userDtoList,"No users found." );
    }


    @Path("/adduser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserDto userDto){
        boolean resp = userService.addUser(userDto);
        if(resp == true) {
            return Response.status(Response.Status.OK)
                    .entity(Response.Status.OK)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Response.Status.BAD_REQUEST)
                .build();

    }

    @Path("/delete/{userName}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("userName") String userName){
        boolean resp = userService.removeUser(userName);
        if(resp == true) {
            return Response.status(Response.Status.OK)
                    .entity(Response.Status.OK)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Response.Status.BAD_REQUEST)
                .build();

    }

    @Path("/updateUser/{userName}/{role}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userName") String userName, @PathParam("role") String role){
        boolean resp = userService.updateUserRole(userName, role);
        if(resp == true) {
            return Response.status(Response.Status.OK)
                    .entity(Response.Status.OK)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Response.Status.BAD_REQUEST)
                .build();

    }

}
