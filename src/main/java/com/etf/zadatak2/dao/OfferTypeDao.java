package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferType;
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
public class OfferTypeDao {

    private static final OfferTypeDao instance = new OfferTypeDao();

    public OfferTypeDao() {
    }

    public static OfferTypeDao getInstance() {
        return instance;
    }

    public int insert(OfferType offer_type, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `offer_type`(`name`) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, offer_type.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public OfferType find(int offer_type_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OfferType offer_type = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `offer_type` WHERE `offer_type_id`=?");
            ps.setInt(1, offer_type_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                offer_type = new OfferType(rs.getInt("offer_type_id"), rs.getString("name"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offer_type;
    }

    public OfferType find(String name, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OfferType offer_type = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `offer_type` WHERE `name`=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                offer_type = new OfferType(rs.getInt("offer_type_id"), rs.getString("name"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return offer_type;
    }

    public List<OfferType> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OfferType> listOfferType = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `offer_type` ORDER BY `name` ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                OfferType offer_type = new OfferType(rs.getInt("offer_type_id"), rs.getString("name"));
                listOfferType.add(offer_type);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return listOfferType;
    }

    public void update(OfferType offer_type, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `offer_type` SET `name`=? WHERE `offer_type_id`=?");
            ps.setString(1, offer_type.getName());
            ps.setInt(2, offer_type.getOffer_type_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int offer_type_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            OfferType offerType = OfferTypeDao.getInstance().find(offer_type_id, con);
            List<Offer> offerList = OfferDao.getInstance().findAllByOfferType(offerType, con);

            /*
            delete every offer with the given offer type
             */
            for (Offer temp : offerList) {
                OfferDao.getInstance().delete(temp, con);
            }

            ps = con.prepareStatement("DELETE FROM `offer_type` WHERE `offer_type_id`=?");
            ps.setInt(1, offer_type_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
