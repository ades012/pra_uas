/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mahasiswa;

/**
 *
 * @author ades
 */
public class MahasiswaDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into mahasiswa(npm,nama,jurusan,alamat) "
            + " values(?,?,?,?)";
    private final String updateQuery = "update mahasiswa set nama=?, "
            + " jurusan=?, alamat=? where npm=?";
    private final String deleteQuery = "delete from mahasiswa where npm=?";
    private final String getByIdQuery = "select * from mahasiswa where npm =?";
    private final String getAllQuery = "select * from mahasiswa";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public Mahasiswa insert(Mahasiswa mahasiswa) throws SQLException{
//        if (mahasiswa.getNpm() == 0) {
//            insertStatement.setString(1, mahasiswa.getNama());
//            insertStatement.setString(2, mahasiswa.getJurusan());
//            insertStatement.setString(3, mahasiswa.getAlamat());
//            int npm = (int) insertStatement.executeUpdate();
//            mahasiswa.setNpm(npm);
//        } else {
            insertStatement.setInt(1, mahasiswa.getNpm());
            insertStatement.setString(2, mahasiswa.getNama());
            insertStatement.setString(3, mahasiswa.getJurusan());
            insertStatement.setString(4, mahasiswa.getAlamat());
            updateStatement.executeUpdate();
//        }
        return mahasiswa;
    }
    
    public Mahasiswa delete(Mahasiswa mahasiswa) throws SQLException {
        deleteStatement.setInt(1, mahasiswa.getNpm());
        deleteStatement.executeUpdate();
        return mahasiswa;
    }
     public Mahasiswa update(Mahasiswa mahasiswa) throws SQLException {
        updateStatement.setInt(1, mahasiswa.getNpm());
        updateStatement.setString(2, mahasiswa.getNama());
        updateStatement.setString(3, mahasiswa.getJurusan());
        updateStatement.setString(4, mahasiswa.getAlamat());
        updateStatement.executeUpdate();
        return mahasiswa;
    }
    public Mahasiswa getByNpm(int npm) throws SQLException{
        getByIdStatement.setLong(1, npm);
        ResultSet rs = getByIdStatement.executeQuery();
        if (rs.next()) {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNpm(rs.getInt("npm"));
            mahasiswa.setNama(rs.getString("nama"));
            mahasiswa.setJurusan(rs.getString("jurusan"));
            mahasiswa.setAlamat(rs.getString("alamat"));
            return mahasiswa;
        }
        return null;
    }
    
    public List<Mahasiswa> getAll() throws SQLException{
        List<Mahasiswa> mahasiswaR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNpm(rs.getInt("npm"));
            mahasiswa.setNama(rs.getString("nama"));
            mahasiswa.setJurusan(rs.getString("jurusan"));
            mahasiswa.setAlamat(rs.getString("alamat"));
            mahasiswaR.add(mahasiswa);
        }
        System.out.println(mahasiswaR);
        return mahasiswaR;
    }
}
