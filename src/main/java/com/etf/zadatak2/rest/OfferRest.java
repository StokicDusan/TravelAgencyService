package com.etf.zadatak2.rest;

import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferPicture;
import com.etf.zadatak2.data.OfferType;
import com.etf.zadatak2.exception.AgencyException;
import com.etf.zadatak2.service.OfferService;
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

@Path("offer")
public class OfferRest {

    private final OfferService offerService = OfferService.getInstance();

    /*
    Part related to Offer
     */
    @GET
    @Path("/{offer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Offer getOfferById(@PathParam("offer_id") int offer_id) throws AgencyException {
        return offerService.findOffer(offer_id);
    }

    @GET
    @Path("/country/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> getOfferByCountry(@PathParam("country") String country) throws AgencyException {
        return offerService.findOfferCountry(country, null);
    }

    @GET
    @Path("/country/{country}/{description}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> getOfferByCountry(@PathParam("country") String country, @PathParam("description") String description) throws AgencyException {
        return offerService.findOfferCountry(country, description);
    }

    @GET
    @Path("/location/{location}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> getOfferByLocation(@PathParam("location") String location) throws AgencyException {
        return offerService.findOfferLocation(location);
    }

    @GET
    @Path("/type_of_offer/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> getOfferByVrsta(@PathParam("type") String type) throws AgencyException {
        return offerService.findOfferType(type);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOffer(Offer offer) throws AgencyException {
        offerService.addNewOffer(offer);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOffer(Offer offer) throws AgencyException {
        offerService.updateOffer(offer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{offer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOffer(@PathParam("offer_id") int offer_id) throws AgencyException {
        offerService.deleteOffer(offer_id);
        return Response.ok().build();
    }

    /*
    Part related to Offer Type
     */
    @GET
    @Path("/type/{type_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OfferType getOfferTypeById(@PathParam("type_id") int offer_type_id) throws AgencyException {
        return offerService.findOfferType(offer_type_id);
    }

    @GET
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OfferType> getOfferType() throws AgencyException {
        return offerService.findAllOfferType();
    }

    @POST
    @Path("/type")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOfferType(OfferType offerType) throws AgencyException {
        offerService.addNewOfferType(offerType);
        return Response.ok().build();
    }

    @PUT
    @Path("/type")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOfferType(OfferType offerType) throws AgencyException {
        offerService.updateOfferType(offerType);
        return Response.ok().build();
    }

    @DELETE
    @Path("/type/{type_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOfferType(@PathParam("type_id") int offer_type_id) throws AgencyException {
        offerService.deleteOfferType(offer_type_id);
        return Response.ok().build();
    }

    /*
    Part related to Offer Picture
     */
    @GET
    @Path("/picture/{picture_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OfferPicture getOfferPictureById(@PathParam("picture_id") int offer_picture_id) throws AgencyException {
        return offerService.findOfferPicture(offer_picture_id);
    }

    @GET
    @Path("/picture/offer/{offer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OfferPicture> getOfferPictureByOfferId(@PathParam("offer_id") int offer_id) throws AgencyException {
        return offerService.findAllOfferPicture(offer_id);
    }

    @POST
    @Path("/picture")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOfferPicture(OfferPicture offerPicture) throws AgencyException {
        offerService.addNewOfferPicture(offerPicture);
        return Response.ok().build();
    }

    @PUT
    @Path("/picture")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOfferPicture(OfferPicture offerPicture) throws AgencyException {
        offerService.updateOfferPicture(offerPicture);
        return Response.ok().build();
    }

    @DELETE
    @Path("/picture/{picture_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOfferPicture(@PathParam("picture_id") int offer_picture_id) throws AgencyException {
        offerService.deleteOfferPicture(offer_picture_id);
        return Response.ok().build();
    }
}
