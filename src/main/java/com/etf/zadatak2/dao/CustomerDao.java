package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Address;
import com.etf.zadatak2.data.Contact;
import com.etf.zadatak2.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dušan Stokić
 */
public class CustomerDao {

    private static final CustomerDao instance = new CustomerDao();

    public CustomerDao() {
    }

    public static CustomerDao getInstance() {
        return instance;
    }

    public void insert(Customer customer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `customer`(`address_id`,`contact_id`,`name`,`surname`) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            Integer address_id = AddressDao.getInstance().insert(customer.getAddress(), con);
            Integer contact_id = ContactDao.getInstance().insert(customer.getContact(), con);
            ps.setInt(1, address_id);
            ps.setInt(2, contact_id);
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getSurname());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public Customer find(int customer_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `customer` WHERE `customer_id`=?");
            ps.setInt(1, customer_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Address address = AddressDao.getInstance().find(rs.getInt("address_id"), con);
                Contact contact = ContactDao.getInstance().find(rs.getInt("contact_id"), con);
                customer = new Customer(rs.getInt("customer_id"), contact, address, rs.getString("name"), rs.getString("surname"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }

    public Customer find(Address address, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `customer` WHERE `address_id`=?");
            ps.setInt(1, address.getAddress_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                Contact contact = ContactDao.getInstance().find(rs.getInt("contact_id"), con);
                customer = new Customer(rs.getInt("customer_id"), contact, address, rs.getString("name"), rs.getString("surname"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }

    public Customer find(Contact contact, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `customer` WHERE `contact_id`=?");
            ps.setInt(1, contact.getContact_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                Address address = AddressDao.getInstance().find(rs.getInt("address_id"), con);
                customer = new Customer(rs.getInt("customer_id"), contact, address, rs.getString("name"), rs.getString("surname"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }

    public void update(Customer customer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE `customer` SET `name`=?, `surname`=? WHERE `customer_id`=?");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setInt(3, customer.getCustomer_id());
            ps.executeUpdate();

            AddressDao.getInstance().update(customer.getAddress(), con);
            ContactDao.getInstance().update(customer.getContact(), con);

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Customer customer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ArrangementDao.getInstance().delete(customer, con);

            ps = con.prepareStatement("DELETE FROM `customer` WHERE `customer_id`=?");
            ps.setInt(1, customer.getCustomer_id());
            ps.executeUpdate();

            AddressDao.getInstance().delete(customer.getAddress().getAddress_id(), con);
            ContactDao.getInstance().delete(customer.getContact().getContact_id(), con);

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

}
