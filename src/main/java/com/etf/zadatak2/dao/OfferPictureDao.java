package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferPicture;
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
public class OfferPictureDao {

    private static final OfferPictureDao instance = new OfferPictureDao();

    public OfferPictureDao() {
    }

    public static OfferPictureDao getInstance() {
        return instance;
    }

    public int insert(OfferPicture offer_picture, Connection con) throws SQLException, AgencyException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `offer_picture`(`offer_id`, `name`, `short_description`,`active`) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            Offer offer = OfferDao.getInstance().find(offer_picture.getOffer().getOffer_id(), con);
            if (offer == null) {
                throw new AgencyException("Offer " + offer_picture.getOffer() + " does not exist in the database.");
            }
            ps.setInt(1, offer.getOffer_id());
            ps.setString(2, offer_picture.getName());
            ps.setString(3, offer_picture.getShort_description());
            ps.setBoolean(4, offer_picture.getActive());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public OfferPicture find(int offer_picture_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OfferPicture offer_picture = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `offer_picture` WHERE `offer_picture_id`=?");
            ps.setInt(1, offer_picture_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Offer offer = OfferDao.getInstance().find(rs.getInt("offer_id"), con);
                offer_picture = new OfferPicture(rs.getInt("offer_picture_id"), offer, rs.getString("name"), rs.getString("short_description"), rs.getBoolean("active"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offer_picture;
    }

    public List<OfferPicture> findAllOfferPictureByOfferId(int offer_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OfferPicture> offerPictureList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `offer_picture` WHERE `offer_id`=? ORDER BY `name` ASC");
            ps.setInt(1, offer_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Offer offer = OfferDao.getInstance().find(rs.getInt("offer_id"), con);
                OfferPicture offer_picture = new OfferPicture(rs.getInt("offer_picture_id"), offer, rs.getString("name"), rs.getString("short_description"), rs.getBoolean("active"));
                offerPictureList.add(offer_picture);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offerPictureList;
    }

    public void update(OfferPicture offer_picture, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `offer_picture` SET `name`=?,`short_description`=?,`active`=? WHERE `offer_picture_id`=?");
            ps.setString(1, offer_picture.getName());
            ps.setString(2, offer_picture.getShort_description());
            ps.setBoolean(3, offer_picture.getActive());
            ps.setInt(4, offer_picture.getOffer_picture_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int offer_picture_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("DELETE FROM `offer_picture` WHERE `offer_picture_id`=?");
            ps.setInt(1, offer_picture_id);
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Offer offer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("DELETE FROM `offer_picture` WHERE `offer_id`=?");
            ps.setInt(1, offer.getOffer_id());
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
