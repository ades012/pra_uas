package service;


import dao.MahasiswaDao;
import dao.PerpusDao;
import model.Petugas;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Mahasiswa;
import dao.BukuDao;
import model.Buku;
/**
 *
 * @author ades
 */
public class Jdbc {


    private PerpusDao petugasDao;
    private MahasiswaDao mahasiswaDao;
    private BukuDao bukuDao;
    private Connection connection;
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            petugasDao = new PerpusDao();
            petugasDao.setConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Petugas save(Petugas petugas){
        try {
            connection.setAutoCommit(false);
            petugasDao.save(petugas);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return petugas;
    }
    public Petugas delete(Petugas petugas){
        try {
            connection.setAutoCommit(false);
            petugasDao.save(petugas);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return petugas;
    }
    public Petugas getPetugas(int id){
        try {
            return petugasDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Petugas checkLogin(String username, String password){
        try {
            return petugasDao.checkLogin(username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Mahasiswa save(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public Mahasiswa delete(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public Mahasiswa getMahasiswa(int npm) {
        try {
            return mahasiswaDao.getByNpm(npm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Mahasiswa> getAllMahasiswa() {
        try {
            return mahasiswaDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Buku> getAllBuku() {
        try {
            return bukuDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
