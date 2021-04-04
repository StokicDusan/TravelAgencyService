package com.etf.zadatak2.sevice;

import com.etf.zadatak2.dao.AranzmanDao;
import com.etf.zadatak2.dao.ResourcesManager;
import com.etf.zadatak2.data.Aranzman;
import com.etf.zadatak2.data.Korisnik;
import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.exception.Zadatak2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class AranzmanService {

    private static final AranzmanService instance = new AranzmanService();

    public AranzmanService() {
    }

    public static AranzmanService getInstance() {
        return instance;
    }

    public void makeAranzman(Korisnik korisnik, Ponuda ponuda) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Aranzman aranzman = new Aranzman(korisnik, ponuda);
            AranzmanDao.getInstance().insert(aranzman, con);

            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno pravljenje aranžmana ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }

    }

    public List<Aranzman> findAllAranzman() throws Zadatak2Exception {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return AranzmanDao.getInstance().findAll(con);
        } catch (SQLException ex) {
            throw new Zadatak2Exception("Neuspešno pronalaženje aranžmana ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteAranzman(int aranzman_id) throws Zadatak2Exception, SQLException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Aranzman aranzman = AranzmanDao.getInstance().find(aranzman_id, con);
            if (aranzman != null) {
                AranzmanDao.getInstance().delete(aranzman, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje aranzmana ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
