package com.revature.p1.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.custom_exceptions.InvalidUserException;
import com.revature.p1.dtos.requests.NewTicketRequest;
import com.revature.p1.dtos.requests.NewUserRequest;

import javax.naming.Context;

public class TicketsHandler {
    private final ObjectMapper mapper;

    public void createNewTicket(Context c) {
        NewTicketRequest req = mapper.readValue(c.req.getInputStream(), NewTicketRequest.class);

        try {
            ticketService.saveTicket(req);
            c.status(201);
        } catch (InvalidUserException e) {
            c.status(403);
            c.json(e);
        }
    }


    public TicketsHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
