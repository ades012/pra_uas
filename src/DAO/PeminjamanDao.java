/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Peminjaman;

/**
 *
 * @author ades
 */
public class PeminjamanDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    
    private final String insertQueryPinjam = "insert into peminjaman(id_peminjaman,nik,npm,tgl_pinjam,tgl_kembali)"
            + "values(?,?,?,?,?)";
    private final String insertQueryDetail = "insert into detail(id_detail,id_peminjaman,kodebuku)"
            + "values (?,?,?)";
    private final String updateQuery = "update peminjaman set tgl_kembali=? ";
    private final String deleteQueryPinjam = "delete from peminjaman where id_peminajaman=? ";
    private final String deleteQueryDetail = "delete from peminjaman where id_peminjaman=? ";
    
}
