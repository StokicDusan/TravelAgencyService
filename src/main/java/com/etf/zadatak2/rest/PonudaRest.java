package com.etf.zadatak2.rest;

import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.data.PonudaSlika;
import com.etf.zadatak2.data.PonudaVrsta;
import com.etf.zadatak2.exception.Zadatak2Exception;
import com.etf.zadatak2.sevice.PonudaService;
import java.util.List;
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

@Path("ponuda")
public class PonudaRest {

    private final PonudaService ponudaService = PonudaService.getInstance();

    /*
    Deo vezan za ponudu
     */
    @GET
    @Path("/{ponuda_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ponuda getPonudaById(@PathParam("ponuda_id") int ponuda_id) throws Zadatak2Exception {
        return ponudaService.findPonuda(ponuda_id);
    }

    @GET
    @Path("/drzava/{drzava}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ponuda> getPonudaByDrzava(@PathParam("drzava") String drzava) throws Zadatak2Exception {
        return ponudaService.findPonudaDrzava(drzava, null);
    }

    @GET
    @Path("/drzava/{drzava}/{opis}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ponuda> getPonudaByDrzava(@PathParam("drzava") String drzava, @PathParam("opis") String opis) throws Zadatak2Exception {
        return ponudaService.findPonudaDrzava(drzava, opis);
    }

    @GET
    @Path("/mesto/{mesto}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ponuda> getPonudaByMesto(@PathParam("mesto") String mesto) throws Zadatak2Exception {
        return ponudaService.findPonudaMesto(mesto);
    }

    /*
    @GET
    @Path("/opis/{opis}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ponuda getPonudaByOpis(@PathParam("opis") String opis) throws Zadatak2Exception {
        return ponudaService.findPonudaOpis(opis);
    }
     */
    @GET
    @Path("/vrsta_ponude/{vrsta}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ponuda> getPonudaByVrsta(@PathParam("vrsta") String vrsta) throws Zadatak2Exception {
        return ponudaService.findPonudaVrsta(vrsta);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPonuda(Ponuda ponuda) throws Zadatak2Exception {
        ponudaService.addNewPonuda(ponuda);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePonuda(Ponuda ponuda) throws Zadatak2Exception {
        ponudaService.updatePonuda(ponuda);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{ponuda_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePonuda(@PathParam("ponuda_id") int ponuda_id) throws Zadatak2Exception {
        ponudaService.deletePonuda(ponuda_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za vrstu ponude
     */
    @GET
    @Path("/vrsta/{vrsta_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PonudaVrsta getPonudaVrstaById(@PathParam("vrsta_id") int ponuda_vrsta_id) throws Zadatak2Exception {
        return ponudaService.findPonudaVrsta(ponuda_vrsta_id);
    }

    @GET
    @Path("/vrsta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PonudaVrsta> getPonudaVrsta() throws Zadatak2Exception {
        return ponudaService.findAllPonudaVrsta();
    }

    @POST
    @Path("/vrsta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPonudaVrsta(PonudaVrsta ponudaVrsta) throws Zadatak2Exception {
        ponudaService.addNewPonudaVrsta(ponudaVrsta);
        return Response.ok().build();
    }

    @PUT
    @Path("/vrsta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePonudaVrsta(PonudaVrsta ponudaVrsta) throws Zadatak2Exception {
        ponudaService.updatePonudaVrsta(ponudaVrsta);
        return Response.ok().build();
    }

    @DELETE
    @Path("/vrsta/{vrsta_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePonudaVrsta(@PathParam("vrsta_id") int ponuda_vrsta_id) throws Zadatak2Exception {
        ponudaService.deletePonudaVrsta(ponuda_vrsta_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za sliku ponude
     */
    @GET
    @Path("/slika/{slika_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PonudaSlika getPonudaSlikaById(@PathParam("slika_id") int ponuda_slika_id) throws Zadatak2Exception {
        return ponudaService.findPonudaSlika(ponuda_slika_id);
    }

    @GET
    @Path("/slika/ponuda/{ponuda_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PonudaSlika> getPonudaSlikaByPonudaId(@PathParam("ponuda_id") int ponuda_id) throws Zadatak2Exception {
        return ponudaService.findAllPonudaSlika(ponuda_id);
    }

    @POST
    @Path("/slika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPonudaSlika(PonudaSlika ponudaSlika) throws Zadatak2Exception {
        ponudaService.addNewPonudaSlika(ponudaSlika);
        return Response.ok().build();
    }

    @PUT
    @Path("/slika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePonudaSlika(PonudaSlika ponudaSlika) throws Zadatak2Exception {
        ponudaService.updatePonudaSlika(ponudaSlika);
        return Response.ok().build();
    }

    @DELETE
    @Path("/slika/{slika_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePonudaSlika(@PathParam("slika_id") int ponuda_slika_id) throws Zadatak2Exception {
        ponudaService.deletePonudaSlika(ponuda_slika_id);
        return Response.ok().build();
    }
}
