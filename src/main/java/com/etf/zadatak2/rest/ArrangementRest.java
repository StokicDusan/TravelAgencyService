package com.etf.zadatak2.rest;

import com.etf.zadatak2.data.Arrangement;
import com.etf.zadatak2.exception.AgencyException;
import com.etf.zadatak2.service.ArrangementService;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("arrangement")
public class ArrangementRest {

    private final ArrangementService arrangementService = ArrangementService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Arrangement> getArrangement() throws AgencyException {
        return arrangementService.findAllArrangement();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeArrangement(Arrangement arrangement) throws AgencyException {
        arrangementService.makeArrangement(arrangement.getCustomer(), arrangement.getOffer());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{arrangement_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArrangement(@PathParam("arrangement_id") int arrangement_id) throws AgencyException, SQLException {
        arrangementService.deleteArrangement(arrangement_id);
        return Response.ok().build();
    }

}
