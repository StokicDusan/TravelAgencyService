package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.data.PonudaVrsta;
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
public class PonudaVrstaDao {

    private static final PonudaVrstaDao instance = new PonudaVrstaDao();

    public PonudaVrstaDao() {
    }

    public static PonudaVrstaDao getInstance() {
        return instance;
    }

    public int insert(PonudaVrsta ponuda_vrsta, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `ponuda_vrsta`(`naziv`) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ponuda_vrsta.getNaziv());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public PonudaVrsta find(int ponuda_vrsta_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PonudaVrsta ponuda_vrsta = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda_vrsta` WHERE `ponuda_vrsta_id`=?");
            ps.setInt(1, ponuda_vrsta_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ponuda_vrsta = new PonudaVrsta(rs.getInt("ponuda_vrsta_id"), rs.getString("naziv"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponuda_vrsta;
    }

    public PonudaVrsta find(String naziv, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PonudaVrsta ponuda_vrsta = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda_vrsta` WHERE `naziv`=?");
            ps.setString(1, naziv);
            rs = ps.executeQuery();
            if (rs.next()) {
                ponuda_vrsta = new PonudaVrsta(rs.getInt("ponuda_vrsta_id"), rs.getString("naziv"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponuda_vrsta;
    }

    public List<PonudaVrsta> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PonudaVrsta> listPonudaVrsta = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda_vrsta` ORDER BY `naziv` ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                PonudaVrsta ponuda_vrsta = new PonudaVrsta(rs.getInt("ponuda_vrsta_id"), rs.getString("naziv"));
                listPonudaVrsta.add(ponuda_vrsta);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return listPonudaVrsta;
    }

    public void update(PonudaVrsta ponuda_vrsta, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `ponuda_vrsta` SET `naziv`=? WHERE `ponuda_vrsta_id`=?");
            ps.setString(1, ponuda_vrsta.getNaziv());
            ps.setInt(2, ponuda_vrsta.getPonuda_vrsta_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int ponuda_vrsta_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            PonudaVrsta ponudaVrsta = PonudaVrstaDao.getInstance().find(ponuda_vrsta_id, con);
            List<Ponuda> ponudaList = PonudaDao.getInstance().findAllByPonudaVrsta(ponudaVrsta, con);

            /*
            Brisanje svih ponuda po toj vrsti ponude
             */
            for (Ponuda temp : ponudaList) {
                PonudaDao.getInstance().delete(temp, con);
            }

            ps = con.prepareStatement("DELETE FROM `ponuda_vrsta` WHERE `ponuda_vrsta_id`=?");
            ps.setInt(1, ponuda_vrsta_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
