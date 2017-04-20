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
import model.Buku;

/**
 *
 * @author ades
 */
public class BukuDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into buku(kodebuku,judul,pengarang,tahun)"
            + " values(?,?,?,?)";
    private final String updateQuery = "update buku set kodebuku=?, "
            + " judul=?, pengarang=?, tahun=? where kodebuku=?";
    private final String deleteQuery = "delete from buku where kodebuku=?";
    private final String getByIdQuery = "select * from buku where kodebuku =?";
    private final String getAllQuery = "select * from buku";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public Buku save(Buku buku) throws SQLException{
        if (buku.getKodebuku() == 0) {
            insertStatement.setString(1, buku.getJudul());
            insertStatement.setString(2, buku.getPengarang());
            insertStatement.setInt(3, buku.getTahun());
            int id = (int) insertStatement.executeUpdate();
            buku.setKodebuku(id);
        } else {
            insertStatement.setString(1, buku.getJudul());
            insertStatement.setString(2, buku.getPengarang());
            insertStatement.setInt(3, buku.getTahun());
            updateStatement.setInt(4, buku.getKodebuku());
            updateStatement.executeUpdate();
        }
        return buku;
    }
    
    public Buku delete(Buku buku) throws SQLException {
        deleteStatement.setInt(1, buku.getKodebuku());
        deleteStatement.executeUpdate();
        return buku;
    }
    
    public Buku getById(int id) throws SQLException{
        getByIdStatement.setLong(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        if (rs.next()) {
            Buku buku = new Buku();
            buku.setKodebuku(rs.getInt("kodebuku"));
            buku.setJudul(rs.getString("judul"));
            buku.setPengarang(rs.getString("pengarang"));
            buku.setTahun(rs.getInt("tahun"));
            return buku;
        }
        return null;
    }
    
    public List<Buku> getAll() throws SQLException{
        List<Buku> bukuR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Buku buku = new Buku();
            buku.setKodebuku(rs.getInt("kodebuku"));
            buku.setJudul(rs.getString("judul"));
            buku.setPengarang(rs.getString("pengarang"));
            buku.setTahun(rs.getInt("tahun"));
            bukuR.add(buku);
        }
        return bukuR;
    }
}   
