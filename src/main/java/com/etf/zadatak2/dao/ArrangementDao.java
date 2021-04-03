package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Aranzman;
import com.etf.zadatak2.data.Korisnik;
import com.etf.zadatak2.data.Ponuda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class AranzmanDao {

    private static final AranzmanDao instance = new AranzmanDao();

    public AranzmanDao() {
    }

    public static AranzmanDao getInstance() {
        return instance;
    }

    public int insert(Aranzman aranzman, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `aranzman`(`korisnik_id`, `ponuda_id`) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, aranzman.getKorisnik().getKorisnik_id());
            ps.setInt(2, aranzman.getPonuda().getPonuda_id());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Aranzman find(int aranzman_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Aranzman aranzman = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `aranzman` WHERE `aranzman_id`=?");
            ps.setInt(1, aranzman_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Korisnik korisnik = KorisnikDao.getInstance().find(rs.getInt("korisnik_id"), con);
                Ponuda ponuda = PonudaDao.getInstance().find(rs.getInt("ponuda_id"), con);
                aranzman = new Aranzman(rs.getInt("aranzman_id"), korisnik, ponuda);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return aranzman;
    }

    public List<Aranzman> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Aranzman> aranzmanList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `aranzman`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Korisnik korisnik = KorisnikDao.getInstance().find(rs.getInt("korisnik_id"), con);
                Ponuda ponuda = PonudaDao.getInstance().find(rs.getInt("ponuda_id"), con);
                Aranzman aranzman = new Aranzman(rs.getInt("aranzman_id"), korisnik, ponuda);
                aranzmanList.add(aranzman);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return aranzmanList;
    }

    public void delete(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `aranzman` WHERE `korisnik_id`=?");
            ps.setInt(1, korisnik.getKorisnik_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Ponuda ponuda, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `aranzman` WHERE `ponuda_id`=?");
            ps.setInt(1, ponuda.getPonuda_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Aranzman aranzman, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `aranzman` WHERE `aranzman_id`=?");
            ps.setInt(1, aranzman.getAranzman_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
