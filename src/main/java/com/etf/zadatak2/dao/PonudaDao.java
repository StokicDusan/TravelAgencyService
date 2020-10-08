package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.data.PonudaVrsta;
import com.etf.zadatak2.exception.Zadatak2Exception;
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
public class PonudaDao {

    private static final PonudaDao instance = new PonudaDao();

    public PonudaDao() {
    }

    public static PonudaDao getInstance() {
        return instance;
    }

    public int insert(Ponuda ponuda, Connection con) throws SQLException, Zadatak2Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `ponuda`(`ponuda_vrsta_id`, `drzava`, `mesto`,`naziv`,`opis`, `aktivna`) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            PonudaVrsta ponuda_vrsta = PonudaVrstaDao.getInstance().find(ponuda.getPonuda_vrsta().getPonuda_vrsta_id(), con);
            if (ponuda_vrsta == null) {
                throw new Zadatak2Exception("Ponuda " + ponuda + " Ne postoji u bazi.");
            }
            ps.setInt(1, ponuda_vrsta.getPonuda_vrsta_id());
            ps.setString(2, ponuda.getDrzava());
            ps.setString(3, ponuda.getMesto());
            ps.setString(4, ponuda.getNaziv());
            ps.setString(5, ponuda.getOpis());
            ps.setBoolean(6, ponuda.getAktivna());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Ponuda find(int ponuda_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ponuda ponuda = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda` WHERE `ponuda_id`=?");
            ps.setInt(1, ponuda_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                PonudaVrsta ponuda_vrsta = PonudaVrstaDao.getInstance().find(rs.getInt("ponuda_vrsta_id"), con);
                ponuda = new Ponuda(rs.getInt("ponuda_id"), ponuda_vrsta, rs.getString("drzava"), rs.getString("mesto"), rs.getString("naziv"), rs.getString("opis"), rs.getBoolean("aktivna"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponuda;
    }

    public List<Ponuda> findAllByMesto(String mesto, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ponuda> ponudaList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda` WHERE `mesto`=? ORDER BY `naziv` ASC");
            ps.setString(1, mesto);
            rs = ps.executeQuery();
            while (rs.next()) {
                PonudaVrsta ponuda_vrsta = PonudaVrstaDao.getInstance().find(rs.getInt("ponuda_vrsta_id"), con);
                Ponuda ponuda = new Ponuda(rs.getInt("ponuda_id"), ponuda_vrsta, rs.getString("drzava"), rs.getString("mesto"), rs.getString("naziv"), rs.getString("opis"), rs.getBoolean("aktivna"));
                ponudaList.add(ponuda);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponudaList;
    }

    public List<Ponuda> findAllByPonudaVrsta(PonudaVrsta ponuda_vrsta, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ponuda> ponudaList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda` WHERE `ponuda_vrsta_id`=?  ORDER BY `drzava` ASC");
            ps.setInt(1, ponuda_vrsta.getPonuda_vrsta_id());
            rs = ps.executeQuery();
            while (rs.next()) {
                Ponuda ponuda = new Ponuda(rs.getInt("ponuda_id"), ponuda_vrsta, rs.getString("drzava"), rs.getString("mesto"), rs.getString("naziv"), rs.getString("opis"), rs.getBoolean("aktivna"));
                ponudaList.add(ponuda);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponudaList;
    }

    public List<Ponuda> findAllByDrzava(String drzava, String opis, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ponuda> ponudaList = new ArrayList<>();
        try {
            if (opis != null) {
                ps = con.prepareStatement("SELECT * FROM `ponuda` WHERE `drzava`=? AND `opis` LIKE ? ORDER BY `mesto` ASC");
                ps.setString(1, drzava);
                ps.setString(2, "%" + opis + "%");
            } else {
                ps = con.prepareStatement("SELECT * FROM `ponuda` WHERE `drzava`=?");
                ps.setString(1, drzava);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                PonudaVrsta ponuda_vrsta = PonudaVrstaDao.getInstance().find(rs.getInt("ponuda_vrsta_id"), con);
                Ponuda ponuda = new Ponuda(rs.getInt("ponuda_id"), ponuda_vrsta, rs.getString("drzava"), rs.getString("mesto"), rs.getString("naziv"), rs.getString("opis"), rs.getBoolean("aktivna"));
                ponudaList.add(ponuda);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponudaList;
    }

    public List<Ponuda> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ponuda> ponudaList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda`");
            rs = ps.executeQuery();
            while (rs.next()) {
                PonudaVrsta ponuda_vrsta = PonudaVrstaDao.getInstance().find(rs.getInt("ponuda_vrsta_id"), con);
                Ponuda ponuda = new Ponuda(rs.getInt("ponuda_id"), ponuda_vrsta, rs.getString("drzava"), rs.getString("mesto"), rs.getString("naziv"), rs.getString("opis"), rs.getBoolean("aktivna"));
                ponudaList.add(ponuda);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponudaList;
    }

    public void update(Ponuda ponuda, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `ponuda` SET `drzava`=?, `mesto`=?,`naziv`=?, `opis`=?, `aktivna`=? WHERE `ponuda_id`=?");
            ps.setString(1, ponuda.getDrzava());
            ps.setString(2, ponuda.getMesto());
            ps.setString(3, ponuda.getNaziv());
            ps.setString(4, ponuda.getOpis());
            ps.setBoolean(5, ponuda.getAktivna());
            ps.setInt(6, ponuda.getPonuda_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Ponuda ponuda, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            AranzmanDao.getInstance().delete(ponuda, con);
            PonudaSlikaDao.getInstance().delete(ponuda, con);

            ps = con.prepareStatement("DELETE FROM `ponuda` WHERE `ponuda_id`=?");
            ps.setInt(1, ponuda.getPonuda_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
