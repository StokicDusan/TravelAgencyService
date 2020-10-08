package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Adresa;
import com.etf.zadatak2.data.Korisnik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class AdresaDao {

    private static final AdresaDao instance = new AdresaDao();

    public AdresaDao() {
    }

    public static AdresaDao getInstance() {
        return instance;
    }

    public int insert(Adresa adresa, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `adresa`(`drzava`,`grad`,`ulica`,`broj`) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, adresa.getDrzava());
            ps.setString(2, adresa.getGrad());
            ps.setString(3, adresa.getUlica());
            ps.setInt(4, adresa.getBroj());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Adresa find(int adresa_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Adresa adresa = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `adresa` WHERE `adresa_id`=?");
            ps.setInt(1, adresa_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                adresa = new Adresa(rs.getInt("adresa_id"), rs.getString("drzava"), rs.getString("grad"), rs.getString("ulica"), rs.getInt("broj"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return adresa;
    }

    public void update(Adresa adresa, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `adresa` SET `drzava`=?, `grad`=?, `ulica`=?, `broj`=? WHERE `adresa_id`=?");
            ps.setString(1, adresa.getDrzava());
            ps.setString(2, adresa.getGrad());
            ps.setString(3, adresa.getUlica());
            ps.setInt(4, adresa.getBroj());
            ps.setInt(5, adresa.getAdresa_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int adresa_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `adresa` WHERE `adresa_id`=?");
            ps.setInt(1, adresa_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

}
