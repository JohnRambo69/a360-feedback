package com.avenga.a360.controller;

import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.dto.SmtpDto;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.SendService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/smtp")
public class SmtpController {
    @Inject
    SendService sendService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response setUpSmtp(SmtpDto smtpDto) {

       boolean isOk =  sendService.changeSmtpProperties(smtpDto.getSmtp(), smtpDto.getPort(),smtpDto.getSenderEmail(),smtpDto.getSenderPassword());
        if(isOk) {
            return Response.status(Response.Status.OK).entity("Smtp settings changed.").build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Error when Smtp settings changed.").build();
        }

    }

    @Path("/reset")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response resetSmtpToDefault() {

        boolean isOk =  sendService.resetSmtp();
        if(isOk) {
            return Response.status(Response.Status.OK).entity("Smtp settings reset.").build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Error when Smtp settings reset.").build();
        }

    }

    @GET()
    @Path("/checkSmtp")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkSmtpConnection(){
      boolean isOk =  sendService.checkSmtpServer();
        if(isOk) {
            return Response.status(Response.Status.OK).entity("Smtp connection ok.").build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Error with connection.").build();
        }
    }

}