package com.etf.zadatak2.sevice;

import com.etf.zadatak2.dao.PonudaDao;
import com.etf.zadatak2.dao.PonudaSlikaDao;
import com.etf.zadatak2.dao.PonudaVrstaDao;
import com.etf.zadatak2.dao.ResourcesManager;
import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.data.PonudaSlika;
import com.etf.zadatak2.data.PonudaVrsta;
import com.etf.zadatak2.exception.Zadatak2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class PonudaService {

    private static final PonudaService instance = new PonudaService();

    public PonudaService() {
    }

    public static PonudaService getInstance() {
        return instance;
    }

    /*
    Deo vezan za Ponudu
     */
    public int addNewPonuda(Ponuda ponuda) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaDao.getInstance().insert(ponuda, con);
        } catch (SQLException ex) {
            throw new Zadatak2Exception("Neuspešno dodavanje ponude " + ponuda, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Ponuda findPonuda(int ponuda_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaDao.getInstance().find(ponuda_id, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji ponuda " + ponuda_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Ponuda> findPonudaDrzava(String drzava, String opis) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaDao.getInstance().findAllByDrzava(drzava, opis, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoje ponude u drzavi " + drzava, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Ponuda> findPonudaVrsta(String vrsta) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            PonudaVrsta ponuda_vrsta = PonudaVrstaDao.getInstance().find(vrsta, con);
            return PonudaDao.getInstance().findAllByPonudaVrsta(ponuda_vrsta, con);

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Ne postoje vrste ponuda " + vrsta, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Ponuda> findPonudaMesto(String mesto) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaDao.getInstance().findAllByMesto(mesto, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji ponuda u mestu " + mesto, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    public Ponuda findPonudaOpis(String opis) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaDao.getInstance().findByOpis(opis, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji ponuda sa reči " + opis + " u opisu", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
     */
    public List<Ponuda> findAllPonuda() throws Zadatak2Exception {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return PonudaDao.getInstance().findAll(con);
        } catch (SQLException ex) {
            throw new Zadatak2Exception("Greska u nalazenju ponuda ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updatePonuda(Ponuda ponuda) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            PonudaDao.getInstance().update(ponuda, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno ažuriranje ponude " + ponuda, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deletePonuda(int ponuda_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Ponuda ponuda = PonudaDao.getInstance().find(ponuda_id, con);
            if (ponuda != null) {
                PonudaDao.getInstance().delete(ponuda, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje ponude " + ponuda_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za vrstu ponude
     */
    public void addNewPonudaVrsta(PonudaVrsta ponuda_vrsta) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            PonudaVrstaDao.getInstance().insert(ponuda_vrsta, con);
        } catch (SQLException ex) {
            throw new Zadatak2Exception("Neuspešno dodavanje vrste ponude " + ponuda_vrsta, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public PonudaVrsta findPonudaVrsta(int ponuda_vrsta_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaVrstaDao.getInstance().find(ponuda_vrsta_id, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji vrsta ponude " + ponuda_vrsta_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<PonudaVrsta> findAllPonudaVrsta() throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaVrstaDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoje vrste ponuda ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updatePonudaVrsta(PonudaVrsta ponudaVrsta) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            PonudaVrstaDao.getInstance().update(ponudaVrsta, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno ažuriranje vrste ponude " + ponudaVrsta, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deletePonudaVrsta(int ponuda_vrsta_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            PonudaVrsta ponudaVrsta = PonudaVrstaDao.getInstance().find(ponuda_vrsta_id, con);
            if (ponudaVrsta != null) {
                PonudaVrstaDao.getInstance().delete(ponuda_vrsta_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje vrste ponude " + ponuda_vrsta_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za sliku ponude
     */
    public void addNewPonudaSlika(PonudaSlika ponuda_slika) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            PonudaSlikaDao.getInstance().insert(ponuda_slika, con);
        } catch (SQLException ex) {
            throw new Zadatak2Exception("Neuspešno dodavanje slike ponude " + ponuda_slika, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public PonudaSlika findPonudaSlika(int ponuda_slika_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return PonudaSlikaDao.getInstance().find(ponuda_slika_id, con);

        } catch (SQLException ex) {
            throw new Zadatak2Exception("Ne postoji slika ponude " + ponuda_slika_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<PonudaSlika> findAllPonudaSlika(int ponuda_id) throws Zadatak2Exception {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return PonudaSlikaDao.getInstance().findAllPonudaSlikaByPonudaId(ponuda_id, con);
        } catch (SQLException ex) {
            throw new Zadatak2Exception("Greska u nalazenju slika ponude ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updatePonudaSlika(PonudaSlika ponudaSlika) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            PonudaSlikaDao.getInstance().update(ponudaSlika, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno ažuriranje slike ponude " + ponudaSlika, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deletePonudaSlika(int ponuda_slika_id) throws Zadatak2Exception {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            PonudaSlika ponudaSlika = PonudaSlikaDao.getInstance().find(ponuda_slika_id, con);
            if (ponudaSlika != null) {
                PonudaSlikaDao.getInstance().delete(ponuda_slika_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new Zadatak2Exception("Neuspešno brisanje slike ponude " + ponuda_slika_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
