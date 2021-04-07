package com.etf.zadatak2.sevice;

import com.etf.zadatak2.dao.AddressDao;
import com.etf.zadatak2.dao.ContactDao;
import com.etf.zadatak2.dao.CustomerDao;
import com.etf.zadatak2.dao.ResourcesManager;
import com.etf.zadatak2.data.Address;
import com.etf.zadatak2.data.Contact;
import com.etf.zadatak2.data.Customer;
import com.etf.zadatak2.exception.AgencyException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dušan Stokić
 */
public class CustomerService {

    private static final CustomerService instance = new CustomerService();

    public CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }

    /*
    Part related to Customer
     */
    public void addNewCustomer(Customer customer) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            CustomerDao.getInstance().insert(customer, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error adding customer" + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Customer findCustomer(int customer_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return CustomerDao.getInstance().find(customer_id, con);

        } catch (SQLException ex) {
            throw new AgencyException("Customer with id: " + customer_id +" does not exist", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateCustomer(Customer customer) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            CustomerDao.getInstance().update(customer, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error updating customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteCustomer(int customer_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Customer customer = CustomerDao.getInstance().find(customer_id, con);
            if (customer != null) {
                CustomerDao.getInstance().delete(customer, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting customer " + customer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Part related to Address
     */
    public void addNewAddress(Address address) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            AddressDao.getInstance().insert(address, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error adding address " + address, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Address findAddress(int address_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return AddressDao.getInstance().find(address_id, con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding address " + address_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateAddress(Address address) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            AddressDao.getInstance().update(address, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error updating address " + address, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    /*
    Deleting addresss deletes customers
    on that address if they exist
    */
    public void deleteAddress(int address_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Address address = AddressDao.getInstance().find(address_id, con);
            Customer customer = CustomerDao.getInstance().find(address, con);
            if (customer != null) {
                CustomerDao.getInstance().delete(customer, con);
            } else {
                AddressDao.getInstance().delete(address_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting address ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Part related to Contact
     */
    public void addNewContact(Contact contact) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            ContactDao.getInstance().insert(contact, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error adding contact " + contact, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Contact findContact(int contact_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ContactDao.getInstance().find(contact_id, con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding contact with id: " + contact_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateContact(Contact contact) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ContactDao.getInstance().update(contact, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error updating contact " + contact, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
   /*
    Deleting contacts deletes customers
    with that contact if they exist
    */
    public void deleteContact(int contact_id) throws AgencyException ,SQLException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Contact contact = ContactDao.getInstance().find(contact_id, con);
            Customer customer = CustomerDao.getInstance().find(contact, con);
            if (customer != null) {
                CustomerDao.getInstance().delete(customer, con);
            } else {
                ContactDao.getInstance().delete(contact_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting contact ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
