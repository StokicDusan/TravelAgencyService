package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Arrangement;
import com.etf.zadatak2.data.Customer;
import com.etf.zadatak2.data.Offer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dušan Stokić
 */
public class ArrangementDao {

    private static final ArrangementDao instance = new ArrangementDao();

    public ArrangementDao() {
    }

    public static ArrangementDao getInstance() {
        return instance;
    }

    public int insert(Arrangement arrangement, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `arrangement`(`customer_id`, `offer_id`) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, arrangement.getCustomer().getCustomer_id());
            ps.setInt(2, arrangement.getOffer().getOffer_id());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Arrangement find(int arrangement_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Arrangement arrangement = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `arrangement` WHERE `arrangement_id`=?");
            ps.setInt(1, arrangement_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(rs.getInt("customer_id"), con);
                Offer offer = OfferDao.getInstance().find(rs.getInt("offer_id"), con);
                arrangement = new Arrangement(rs.getInt("arrangement_id"), customer, offer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return arrangement;
    }

    public List<Arrangement> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Arrangement> arrangementList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `arrangement`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(rs.getInt("customer_id"), con);
                Offer offer = OfferDao.getInstance().find(rs.getInt("offer_id"), con);
                Arrangement arrangement = new Arrangement(rs.getInt("arrangement_id"), customer, offer);
                arrangementList.add(arrangement);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return arrangementList;
    }

    public void delete(Customer customer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `arrangement` WHERE `customer_id`=?");
            ps.setInt(1, customer.getCustomer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Offer offer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `arrangement` WHERE `offer_id`=?");
            ps.setInt(1, offer.getOffer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Arrangement arrangement, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `arrangement` WHERE `arrangement_id`=?");
            ps.setInt(1, arrangement.getArrangement_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
