package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Adresa;
import com.etf.zadatak2.data.Kontakt;
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
public class KorisnikDao {

    private static final KorisnikDao instance = new KorisnikDao();

    public KorisnikDao() {
    }

    public static KorisnikDao getInstance() {
        return instance;
    }

    public void insert(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `korisnik`(`adresa_id`,`kontakt_id`,`ime`,`prezime`) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            Integer adresa_id = AdresaDao.getInstance().insert(korisnik.getAdresa(), con);
            Integer kontakt_id = KontaktDao.getInstance().insert(korisnik.getKontakt(), con);
            ps.setInt(1, adresa_id);
            ps.setInt(2, kontakt_id);
            ps.setString(3, korisnik.getIme());
            ps.setString(4, korisnik.getPrezime());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public Korisnik find(int korisnik_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Korisnik korisnik = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `korisnik` WHERE `korisnik_id`=?");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Adresa adresa = AdresaDao.getInstance().find(rs.getInt("adresa_id"), con);
                Kontakt kontakt = KontaktDao.getInstance().find(rs.getInt("kontakt_id"), con);
                korisnik = new Korisnik(rs.getInt("korisnik_id"), kontakt, adresa, rs.getString("ime"), rs.getString("prezime"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnik;
    }

    public Korisnik find(Adresa adresa, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Korisnik korisnik = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `korisnik` WHERE `adresa_id`=?");
            ps.setInt(1, adresa.getAdresa_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                Kontakt kontakt = KontaktDao.getInstance().find(rs.getInt("kontakt_id"), con);
                korisnik = new Korisnik(rs.getInt("korisnik_id"), kontakt, adresa, rs.getString("ime"), rs.getString("prezime"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnik;
    }

    public Korisnik find(Kontakt kontakt, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Korisnik korisnik = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `korisnik` WHERE `kontakt_id`=?");
            ps.setInt(1, kontakt.getKontakt_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                Adresa adresa = AdresaDao.getInstance().find(rs.getInt("adresa_id"), con);
                korisnik = new Korisnik(rs.getInt("korisnik_id"), kontakt, adresa, rs.getString("ime"), rs.getString("prezime"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnik;
    }

    public void update(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE `korisnik` SET `ime`=?, `prezime`=? WHERE `korisnik_id`=?");
            ps.setString(1, korisnik.getIme());
            ps.setString(2, korisnik.getPrezime());
            ps.setInt(3, korisnik.getKorisnik_id());
            ps.executeUpdate();

            AdresaDao.getInstance().update(korisnik.getAdresa(), con);
            KontaktDao.getInstance().update(korisnik.getKontakt(), con);

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            AranzmanDao.getInstance().delete(korisnik, con);

            ps = con.prepareStatement("DELETE FROM `korisnik` WHERE `korisnik_id`=?");
            ps.setInt(1, korisnik.getKorisnik_id());
            ps.executeUpdate();

            AdresaDao.getInstance().delete(korisnik.getAdresa().getAdresa_id(), con);
            KontaktDao.getInstance().delete(korisnik.getKontakt().getKontakt_id(), con);

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

}
