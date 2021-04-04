package com.etf.zadatak2.rest;

import com.etf.zadatak2.data.Aranzman;
import com.etf.zadatak2.exception.Zadatak2Exception;
import com.etf.zadatak2.sevice.AranzmanService;
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

@Path("aranzman")
public class AranzmanRest {

    private final AranzmanService aranzmanService = AranzmanService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aranzman> getAranzman() throws Zadatak2Exception {
        return aranzmanService.findAllAranzman();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeAranzman(Aranzman aranzman) throws Zadatak2Exception {
        aranzmanService.makeAranzman(aranzman.getKorisnik(), aranzman.getPonuda());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{aranzman_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAranzman(@PathParam("aranzman_id") int aranzman_id) throws Zadatak2Exception, SQLException {
        aranzmanService.deleteAranzman(aranzman_id);
        return Response.ok().build();
    }

}
