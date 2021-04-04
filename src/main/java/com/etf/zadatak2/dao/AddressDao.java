package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dušan Stokić
 */
public class AddressDao {

    private static final AddressDao instance = new AddressDao();

    public AddressDao() {
    }

    public static AddressDao getInstance() {
        return instance;
    }

    public int insert(Address address, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `address`(`country`,`city`,`street`,`number`) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setInt(4, address.getNumber());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Address find(int address_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address address = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `address` WHERE `address_id`=?");
            ps.setInt(1, address_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                address = new Address(rs.getInt("address_id"), rs.getString("country"), rs.getString("city"), rs.getString("street"), rs.getInt("number"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return address;
    }

    public void update(Address address, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `address` SET `country`=?, `city`=?, `street`=?, `number`=? WHERE `address_id`=?");
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setInt(4, address.getNumber());
            ps.setInt(5, address.getAddress_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int address_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `address` WHERE `address_id`=?");
            ps.setInt(1, address_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

}
