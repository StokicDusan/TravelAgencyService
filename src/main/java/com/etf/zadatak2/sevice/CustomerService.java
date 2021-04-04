package com.etf.zadatak2.sevice;

import com.etf.zadatak2.dao.AdresaDao;
import com.etf.zadatak2.dao.KontaktDao;
import com.etf.zadatak2.dao.KorisnikDao;
import com.etf.zadatak2.dao.ResourcesManager;
import com.etf.zadatak2.data.Adresa;
import com.etf.zadatak2.data.Kontakt;
import com.etf.zadatak2.data.Korisnik;
import com.etf.zadatak2.exception.Zadatak2Exception;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class KorisnikService {

    private static final KorisnikService instance = new KorisnikService();

    public KorisnikService() {
    }

    public static KorisnikService getInstance() {
        return instance;
    }

    /*
    Deo vezan za Korisnika
     */
    public void addNewKorisnik(Korisnik korisnik) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            KorisnikDao.getInstance().insert(korisnik, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neusešno dodavanje korisnika " + korisnik, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Korisnik findKorisnik(int korisnik_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return KorisnikDao.getInstance().find(korisnik_id, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji korisik sa id-jem " + korisnik_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateKorisnik(Korisnik korisnik) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            KorisnikDao.getInstance().update(korisnik, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno ažuriranje korisnika " + korisnik, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteKorisnik(int korisnik_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Korisnik korisnik = KorisnikDao.getInstance().find(korisnik_id, con);
            if (korisnik != null) {
                KorisnikDao.getInstance().delete(korisnik, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje korisnika da id-jem " + korisnik_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za adresu
     */
    public void addNewAdresa(Adresa adresa) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            AdresaDao.getInstance().insert(adresa, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neusešno dodavanje adrese " + adresa, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Adresa findAdresa(int adresa_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return AdresaDao.getInstance().find(adresa_id, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji adresa sa id-jem " + adresa_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateAdresa(Adresa adresa) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            AdresaDao.getInstance().update(adresa, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno ažuriranje adrese " + adresa, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteAdresa(int adresa_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Adresa adresa = AdresaDao.getInstance().find(adresa_id, con);
            Korisnik korisnik = KorisnikDao.getInstance().find(adresa, con);
            if (korisnik != null) {
                KorisnikDao.getInstance().delete(korisnik, con);
            } else {
                AdresaDao.getInstance().delete(adresa_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje korisnika ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za kontakt
     */
    public void addNewKontakt(Kontakt kontakt) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            KontaktDao.getInstance().insert(kontakt, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neusešno dodavanje kontakta " + kontakt, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Kontakt findKontkat(int kontakt_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return KontaktDao.getInstance().find(kontakt_id, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji kontakt sa id-jem " + kontakt_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateKontakt(Kontakt kontakt) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            KontaktDao.getInstance().update(kontakt, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno ažuriranje kontakt " + kontakt, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteKontakt(int kontakt_id) throws Zadatak2Exception ,SQLException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Kontakt kontakt = KontaktDao.getInstance().find(kontakt_id, con);
            Korisnik korisnik = KorisnikDao.getInstance().find(kontakt, con);
            if (korisnik != null) {
                KorisnikDao.getInstance().delete(korisnik, con);
            } else {
                KontaktDao.getInstance().delete(kontakt_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje korisnika ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
