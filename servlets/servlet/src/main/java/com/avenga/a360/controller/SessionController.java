package com.avenga.a360.controller;

import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.model.response.StatusMessage;
import com.avenga.a360.service.QuestionService;
import com.avenga.a360.service.SessionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sessions")
public class SessionController {
    @Inject
    SessionService sessionService;

    @Inject
    QuestionService questionService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(SessionDto sessionDto){

        Status status =  sessionService.createSession(sessionDto,sessionDto.getParticipantList(), questionService.questionListDtoToQuestionList(sessionDto.getQuestionList() ));
           if(status.getStatus().equals("success")) {
               return Response.status(Response.Status.OK)
                       .entity(Response.Status.OK)
                       .build();
           }
        else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST)
                    .build();
        }


    }

    @Path("/freeze/{sessionId}/{value}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSession(@PathParam("sessionId") Long id, @PathParam("value") Boolean value){
        boolean resp = sessionService.updateSession(id, value);
        if(resp == true) {
            return Response.status(Response.Status.OK)
                    .entity(Response.Status.OK)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Response.Status.BAD_REQUEST)
                .build();

    }





    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveSessions(){

        List<SessionDto> sessionDtoList = sessionService.findAllSessionsWhereIsSentFalse();

        return Response.status(Response.Status.OK).entity(sessionDtoList).build();
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendIfSessionSend(){
       // Email email = new Email();


        return Response.status(Response.Status.OK).entity("OK").build();
    }


    @GET()
    @Path("/allsessions")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllSessions() {
        List<SessionDto> sessionDtoList = sessionService.getAllSessions();
        if (!sessionDtoList.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(sessionDtoList).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Status("Fail", List.of(new StatusMessage("no sessions found"))))
                    .build();
    }

}
