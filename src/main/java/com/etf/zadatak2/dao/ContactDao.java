package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Kontakt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class KontaktDao {

    private static final KontaktDao instance = new KontaktDao();

    public KontaktDao() {
    }

    public static KontaktDao getInstance() {
        return instance;
    }

    public int insert(Kontakt kontakt, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `kontakt`(`telefon`,`email`) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kontakt.getTelefon());
            ps.setString(2, kontakt.getEmail());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public Kontakt find(int kontakt_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Kontakt kontakt = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `kontakt` WHERE `kontakt_id`=?");
            ps.setInt(1, kontakt_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                kontakt = new Kontakt(rs.getInt("kontakt_id"), rs.getString("telefon"), rs.getString("email"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return kontakt;
    }

    public void update(Kontakt kontakt, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `kontakt` SET `telefon`=?, `email`=? WHERE `kontakt_id`=?");
            ps.setString(1, kontakt.getTelefon());
            ps.setString(2, kontakt.getEmail());
            ps.setInt(3, kontakt.getKontakt_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int kontakt_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `kontakt` WHERE `kontakt_id`=?");
            ps.setInt(1, kontakt_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
