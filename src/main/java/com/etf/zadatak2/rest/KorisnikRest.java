package com.etf.zadatak2.rest;

import com.etf.zadatak2.data.Adresa;
import com.etf.zadatak2.data.Kontakt;
import com.etf.zadatak2.data.Korisnik;
import com.etf.zadatak2.exception.Zadatak2Exception;
import com.etf.zadatak2.sevice.KorisnikService;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("korisnik")
public class KorisnikRest {

    private final KorisnikService korisnikService = KorisnikService.getInstance();

    /*
    Deo vezan za Korisnika
     */
    @GET
    @Path("/{korisnik_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getKorisnikById(@PathParam("korisnik_id") int korisnik_id) throws Zadatak2Exception {
        return korisnikService.findKorisnik(korisnik_id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKorisnik(Korisnik korisnik) throws Zadatak2Exception {
        korisnikService.addNewKorisnik(korisnik);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKorisnik(Korisnik korisnik) throws Zadatak2Exception {
        korisnikService.updateKorisnik(korisnik);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{korisnik_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKorisnik(@PathParam("korisnik_id") int korisnik_id) throws Zadatak2Exception {
        korisnikService.deleteKorisnik(korisnik_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za adresu
     */
    @GET
    @Path("/adresa/{adresa_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Adresa getAdresaById(@PathParam("adresa_id") int adresa_id) throws Zadatak2Exception {
        return korisnikService.findAdresa(adresa_id);
    }

    @POST
    @Path("/adresa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdresa(Adresa adresa) throws Zadatak2Exception {
        korisnikService.addNewAdresa(adresa);
        return Response.ok().build();
    }

    @PUT
    @Path("/adresa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdresa(Adresa adresa) throws Zadatak2Exception {
        korisnikService.updateAdresa(adresa);
        return Response.ok().build();
    }

    @DELETE
    @Path("/adresa/{adresa_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAdresa(@PathParam("adresa_id") int adresa_id) throws Zadatak2Exception {
        korisnikService.deleteAdresa(adresa_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za kontakt
     */

    @GET
    @Path("/kontakt/{kontakt_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kontakt getKontaktById(@PathParam("kontakt_id") int kontakt_id) throws Zadatak2Exception {
        return korisnikService.findKontkat(kontakt_id);
    }

    @POST
    @Path("/kontakt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKontakt(Kontakt kontakt) throws Zadatak2Exception {
        korisnikService.addNewKontakt(kontakt);
        return Response.ok().build();
    }

    @PUT
    @Path("/kontakt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKontakt(Kontakt kontakt) throws Zadatak2Exception {
        korisnikService.updateKontakt(kontakt);
        return Response.ok().build();
    }

    @DELETE
    @Path("/kontakt/{kontakt_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKontakt(@PathParam("kontakt_id") int kontakt_id) throws Zadatak2Exception, SQLException {
        korisnikService.deleteKontakt(kontakt_id);
        return Response.ok().build();
    }
}
