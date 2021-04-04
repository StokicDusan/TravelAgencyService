package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dušan Stokić
 */
public class ContactDao {

    private static final ContactDao instance = new ContactDao();

    public ContactDao() {
    }

    public static ContactDao getInstance() {
        return instance;
    }

    public int insert(Contact contact, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `contact`(`telephone`,`email`) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getTelephone());
            ps.setString(2, contact.getEmail());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Contact find(int contact_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Contact contact = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `contact` WHERE `contact_id`=?");
            ps.setInt(1, contact_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                contact = new Contact(rs.getInt("contact_id"), rs.getString("telephone"), rs.getString("email"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return contact;
    }

    public void update(Contact contact, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `contact` SET `telephone`=?, `email`=? WHERE `contact_id`=?");
            ps.setString(1, contact.getTelephone());
            ps.setString(2, contact.getEmail());
            ps.setInt(3, contact.getContact_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int contact_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `contact` WHERE `contact_id`=?");
            ps.setInt(1, contact_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
