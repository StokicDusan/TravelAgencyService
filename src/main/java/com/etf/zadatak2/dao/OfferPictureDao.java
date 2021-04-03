package com.etf.zadatak2.dao;

import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.data.PonudaSlika;
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
public class PonudaSlikaDao {

    private static final PonudaSlikaDao instance = new PonudaSlikaDao();

    public PonudaSlikaDao() {
    }

    public static PonudaSlikaDao getInstance() {
        return instance;
    }

    public int insert(PonudaSlika ponuda_slika, Connection con) throws SQLException, Zadatak2Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO `ponuda_slika`(`ponuda_id`, `naziv`, `kratak_opis`,`aktivna`) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            Ponuda ponuda = PonudaDao.getInstance().find(ponuda_slika.getPonuda().getPonuda_id(), con);
            if (ponuda == null) {
                throw new Zadatak2Exception("Ponuda " + ponuda_slika.getPonuda() + " Ne postoji u bazi.");
            }
            ps.setInt(1, ponuda.getPonuda_id());
            ps.setString(2, ponuda_slika.getNaziv());
            ps.setString(3, ponuda_slika.getKratak_opis());
            ps.setBoolean(4, ponuda_slika.getAktivna());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public PonudaSlika find(int ponuda_slika_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PonudaSlika ponuda_slika = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda_slika` WHERE `ponuda_slika_id`=?");
            ps.setInt(1, ponuda_slika_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Ponuda ponuda = PonudaDao.getInstance().find(rs.getInt("ponuda_id"), con);
                ponuda_slika = new PonudaSlika(rs.getInt("ponuda_slika_id"), ponuda, rs.getString("naziv"), rs.getString("kratak_opis"), rs.getBoolean("aktivna"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponuda_slika;
    }

    public List<PonudaSlika> findAllPonudaSlikaByPonudaId(int ponuda_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PonudaSlika> ponudaSlikaList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `ponuda_slika` WHERE `ponuda_id`=? ORDER BY `naziv` ASC");
            ps.setInt(1, ponuda_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ponuda ponuda = PonudaDao.getInstance().find(rs.getInt("ponuda_id"), con);
                PonudaSlika ponuda_slika = new PonudaSlika(rs.getInt("ponuda_slika_id"), ponuda, rs.getString("naziv"), rs.getString("kratak_opis"), rs.getBoolean("aktivna"));
                ponudaSlikaList.add(ponuda_slika);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return ponudaSlikaList;
    }

    public void update(PonudaSlika ponuda_slika, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `ponuda_slika` SET `naziv`=?,`kratak_opis`=?,`aktivna`=? WHERE `ponuda_slika_id`=?");
            ps.setString(1, ponuda_slika.getNaziv());
            ps.setString(2, ponuda_slika.getKratak_opis());
            ps.setBoolean(3, ponuda_slika.getAktivna());
            ps.setInt(4, ponuda_slika.getPonuda_slika_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int ponuda_slika_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("DELETE FROM `ponuda_slika` WHERE `ponuda_slika_id`=?");
            ps.setInt(1, ponuda_slika_id);
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Ponuda ponuda, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("DELETE FROM `ponuda_slika` WHERE `ponuda_id`=?");
            ps.setInt(1, ponuda.getPonuda_id());
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
