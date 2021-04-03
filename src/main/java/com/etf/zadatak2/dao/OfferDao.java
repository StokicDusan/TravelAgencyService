package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferType;
import com.etf.zadatak2.exception.AgencyException;
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
public class OfferDao {

    private static final OfferDao instance = new OfferDao();

    public OfferDao() {
    }

    public static OfferDao getInstance() {
        return instance;
    }

    public int insert(Offer offer, Connection con) throws SQLException, AgencyException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `offer`(`offer_type_id`, `country`, `location`,`name`,`description`, `active`) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            OfferType offer_type = OfferTypeDao.getInstance().find(offer.getOffer_type().getOffer_type_id(), con);
            if (offer_type == null) {
                throw new AgencyException("Offer " + offer + " does not exist in the database.");
            }
            ps.setInt(1, offer_type.getOffer_type_id());
            ps.setString(2, offer.getCountry());
            ps.setString(3, offer.getLocation());
            ps.setString(4, offer.getName());
            ps.setString(5, offer.getDescription());
            ps.setBoolean(6, offer.getActive());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Offer find(int offer_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Offer offer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `offer` WHERE `offer_id`=?");
            ps.setInt(1, offer_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                OfferType offer_type = OfferTypeDao.getInstance().find(rs.getInt("offer_type_id"), con);
                offer = new Offer(rs.getInt("offer_id"), offer_type, rs.getString("country"), rs.getString("location"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offer;
    }

    public List<Offer> findAllByLocation(String location, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Offer> offerList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `offer` WHERE `location`=? ORDER BY `name` ASC");
            ps.setString(1, location);
            rs = ps.executeQuery();
            while (rs.next()) {
                OfferType offer_type = OfferTypeDao.getInstance().find(rs.getInt("offer_type_id"), con);
                Offer offer = new Offer(rs.getInt("offer_id"), offer_type, rs.getString("country"), rs.getString("location"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
                offerList.add(offer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offerList;
    }

    public List<Offer> findAllByOfferType(OfferType offer_type, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Offer> offerList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `offer` WHERE `offer_type_id`=?  ORDER BY `country` ASC");
            ps.setInt(1, offer_type.getOffer_type_id());
            rs = ps.executeQuery();
            while (rs.next()) {
                Offer offer = new Offer(rs.getInt("offer_id"), offer_type, rs.getString("country"), rs.getString("location"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
                offerList.add(offer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offerList;
    }

    public List<Offer> findAllByCountry(String country, String description, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Offer> offerList = new ArrayList<>();
        try {
            if (description != null) {
                ps = con.prepareStatement("SELECT * FROM `offer` WHERE `country`=? AND `description` LIKE ? ORDER BY `location` ASC");
                ps.setString(1, country);
                ps.setString(2, "%" + description + "%");
            } else {
                ps = con.prepareStatement("SELECT * FROM `offer` WHERE `country`=?");
                ps.setString(1, country);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                OfferType offer_type = OfferTypeDao.getInstance().find(rs.getInt("offer_type_id"), con);
                Offer offer = new Offer(rs.getInt("offer_id"), offer_type, rs.getString("country"), rs.getString("location"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
                offerList.add(offer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offerList;
    }

    public List<Offer> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Offer> offerList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `offer`");
            rs = ps.executeQuery();
            while (rs.next()) {
                OfferType offer_type = OfferTypeDao.getInstance().find(rs.getInt("offer_type_id"), con);
                Offer offer = new Offer(rs.getInt("offer_id"), offer_type, rs.getString("country"), rs.getString("location"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
                offerList.add(offer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offerList;
    }

    public void update(Offer offer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `offer` SET `country`=?, `location`=?,`name`=?, `description`=?, `active`=? WHERE `offer_id`=?");
            ps.setString(1, offer.getCountry());
            ps.setString(2, offer.getLocation());
            ps.setString(3, offer.getName());
            ps.setString(4, offer.getDescription());
            ps.setBoolean(5, offer.getActive());
            ps.setInt(6, offer.getOffer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Offer offer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ArrangementDao.getInstance().delete(offer, con);
            OfferPictureDao.getInstance().delete(offer, con);

            ps = con.prepareStatement("DELETE FROM `offer` WHERE `offer_id`=?");
            ps.setInt(1, offer.getOffer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
