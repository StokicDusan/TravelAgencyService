package com.etf.zadatak2.rest;

import com.etf.zadatak2.data.Address;
import com.etf.zadatak2.data.Contact;
import com.etf.zadatak2.data.Customer;
import com.etf.zadatak2.exception.AgencyException;
import com.etf.zadatak2.sevice.CustomerService;
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

@Path("customer")
public class CustomerRest {

    private final CustomerService customerService = CustomerService.getInstance();

    /*
    Part related to Customer
     */
    @GET
    @Path("/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("customer_id") int customer_id) throws AgencyException {
        return customerService.findCustomer(customer_id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws AgencyException {
        customerService.addNewCustomer(customer);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) throws AgencyException {
        customerService.updateCustomer(customer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("customer_id") int customer_id) throws AgencyException {
        customerService.deleteCustomer(customer_id);
        return Response.ok().build();
    }

    /*
    Part related to Address
     */
    @GET
    @Path("/address/{address_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Address getAddressById(@PathParam("address_id") int address_id) throws AgencyException {
        return customerService.findAddress(address_id);
    }

    @POST
    @Path("/address")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAddress(Address address) throws AgencyException {
        customerService.addNewAddress(address);
        return Response.ok().build();
    }

    @PUT
    @Path("/address")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(Address address) throws AgencyException {
        customerService.updateAddress(address);
        return Response.ok().build();
    }

    @DELETE
    @Path("/address/{address_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddress(@PathParam("address_id") int address_id) throws AgencyException {
        customerService.deleteAddress(address_id);
        return Response.ok().build();
    }

    /*
    Part related to Contact
     */

    @GET
    @Path("/contact/{contact_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContactById(@PathParam("contact_id") int contact_id) throws AgencyException {
        return customerService.findContact(contact_id);
    }

    @POST
    @Path("/contact")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addContact(Contact contact) throws AgencyException {
        customerService.addNewContact(contact);
        return Response.ok().build();
    }

    @PUT
    @Path("/contact")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContact(Contact contact) throws AgencyException {
        customerService.updateContact(contact);
        return Response.ok().build();
    }

    @DELETE
    @Path("/contact/{contact_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteContact(@PathParam("contact_id") int contact_id) throws AgencyException, SQLException {
        customerService.deleteContact(contact_id);
        return Response.ok().build();
    }
}
