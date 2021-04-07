package com.etf.zadatak2.sevice;

import com.etf.zadatak2.dao.OfferDao;
import com.etf.zadatak2.dao.OfferPictureDao;
import com.etf.zadatak2.dao.OfferTypeDao;
import com.etf.zadatak2.dao.ResourcesManager;
import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferPicture;
import com.etf.zadatak2.data.OfferType;
import com.etf.zadatak2.exception.AgencyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dušan Stokić
 */
public class OfferService {

    private static final OfferService instance = new OfferService();

    public OfferService() {
    }

    public static OfferService getInstance() {
        return instance;
    }

    /*
    Part related to Offer
     */
    public int addNewOffer(Offer offer) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferDao.getInstance().insert(offer, con);
        } catch (SQLException ex) {
            throw new AgencyException("Error adding offer " + offer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Offer findOffer(int offer_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferDao.getInstance().find(offer_id, con);

        } catch (SQLException ex) {
            throw new AgencyException("Offer with id: " + offer_id +" does not exist", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Offer> findOfferCountry(String country, String description) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferDao.getInstance().findAllByCountry(country, description, con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding offer in country " + country, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Offer> findOfferType(String type) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            OfferType offer_type = OfferTypeDao.getInstance().find(type, con);
            return OfferDao.getInstance().findAllByOfferType(offer_type, con);

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error finding offer type " + type, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Offer> findOfferLocation(String location) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferDao.getInstance().findAllByLocation(location, con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding offer on location " + location, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Offer> findAllOffer() throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return OfferDao.getInstance().findAll(con);
        } catch (SQLException ex) {
            throw new AgencyException("Error finding offers ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateOffer(Offer offer) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OfferDao.getInstance().update(offer, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error updating offer " + offer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteOffer(int offer_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Offer offer = OfferDao.getInstance().find(offer_id, con);
            if (offer != null) {
                OfferDao.getInstance().delete(offer, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting offer " + offer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Part related to Offer Type
     */
    
    public void addNewOfferType(OfferType offer_type) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            OfferTypeDao.getInstance().insert(offer_type, con);
        } catch (SQLException ex) {
            throw new AgencyException("Error adding offer type " + offer_type, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public OfferType findOfferType(int offer_type_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferTypeDao.getInstance().find(offer_type_id, con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding offer type " + offer_type_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<OfferType> findAllOfferType() throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferTypeDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding offer typess", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateOfferType(OfferType offerType) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OfferTypeDao.getInstance().update(offerType, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error updating offer types " + offerType, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteOfferType(int offer_type_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OfferType offerType = OfferTypeDao.getInstance().find(offer_type_id, con);
            if (offerType != null) {
                OfferTypeDao.getInstance().delete(offer_type_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting offer types " + offer_type_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Part related to Offer Picture
     */
    public void addNewOfferPicture(OfferPicture offer_picture) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            OfferPictureDao.getInstance().insert(offer_picture, con);
        } catch (SQLException ex) {
            throw new AgencyException("Error adding offer picture " + offer_picture, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public OfferPicture findOfferPicture(int offer_picture_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OfferPictureDao.getInstance().find(offer_picture_id, con);

        } catch (SQLException ex) {
            throw new AgencyException("Error finding offer picture " + offer_picture_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<OfferPicture> findAllOfferPicture(int offer_id) throws AgencyException {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return OfferPictureDao.getInstance().findAllOfferPictureByOfferId(offer_id, con);
        } catch (SQLException ex) {
            throw new AgencyException("Error finding offer pictures ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateOfferPicture(OfferPicture offerPicture) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OfferPictureDao.getInstance().update(offerPicture, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error updating offer picture " + offerPicture, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteOfferPicture(int offer_picture_id) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OfferPicture offerPicture = OfferPictureDao.getInstance().find(offer_picture_id, con);
            if (offerPicture != null) {
                OfferPictureDao.getInstance().delete(offer_picture_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting offer picture  " + offer_picture_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
