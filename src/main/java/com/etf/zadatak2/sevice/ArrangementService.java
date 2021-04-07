package com.etf.zadatak2.sevice;

import com.etf.zadatak2.dao.ArrangementDao;
import com.etf.zadatak2.dao.ResourcesManager;
import com.etf.zadatak2.data.Arrangement;
import com.etf.zadatak2.data.Customer;
import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.exception.AgencyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class ArrangementService {

    private static final ArrangementService instance = new ArrangementService();

    public ArrangementService() {
    }

    public static ArrangementService getInstance() {
        return instance;
    }

    public void makeArrangement(Customer customer, Offer offer) throws AgencyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Arrangement arrangement = new Arrangement(customer, offer);
            ArrangementDao.getInstance().insert(arrangement, con);

            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error adding arrangement ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }

    }

    public List<Arrangement> findAllArrangement() throws AgencyException {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return ArrangementDao.getInstance().findAll(con);
        } catch (SQLException ex) {
            throw new AgencyException("Error finding arrangements ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteArrangement(int arrangement_id) throws AgencyException, SQLException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Arrangement arrangement = ArrangementDao.getInstance().find(arrangement_id, con);
            if (arrangement != null) {
                ArrangementDao.getInstance().delete(arrangement, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new AgencyException("Error deleting arrangement ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
