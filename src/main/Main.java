/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import model.Mahasiswa;
import model.Petugas;
import model.Buku;
import service.Jdbc;

/**
 *
 * @author ades
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("oop?serverTimezone=UTC");
        dataSource.setServerName("localhost");

        dataSource.setPortNumber(3306);

        Jdbc service = new Jdbc();
        service.setDataSource(dataSource);
        Scanner in = new Scanner(System.in);
        
        Boolean loggedin = false;
        Petugas petugas = null;
        
        while (!loggedin) {
            System.out.println("Silahkan Login!\n");
            System.out.print("Username : ");
            String username = in.nextLine();
            System.out.print("Password : ");
            String password = in.nextLine();

            petugas = service.checkLogin(username, password);

            if (petugas != null) {
                loggedin = true;
                System.out.println("\nAnda telah login sebagai " + petugas.getNama() + "!");
                
                while (loggedin) {
                    getMainMenu();
                    String pilihan = in.nextLine();
                    if (Integer.parseInt(pilihan)== 1) {
                    getPeminjamanMenu(petugas,service);
                    }
                    else if (Integer.parseInt(pilihan)== 2)
                    {getPengembalianMenu(petugas,service);
                    }
                    else if (Integer.parseInt(pilihan)== 3)
                    {getBukuMenu();
                    }
                    else if (Integer.parseInt(pilihan)== 4)
                    {getAnggotaMenu(service);
                    }
                    else if (Integer.parseInt(pilihan)== 5)
                    {System.out.println("\nBerhasil keluar!\n");
                            petugas = null;
                            loggedin = false;
                    }
                       
                    
                }
                
            } else {
                System.out.println("\nUsername / password salah!\n");
            }
        }
        
        try {
            dataSource.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getMainMenu() {
        System.out.println("\nSistem Aplikasi Perpustakaan : \n");
        System.out.println("1. Peminjaman Buku");
        System.out.println("2. Pengembalian Buku");
        System.out.println("3. Daftar Buku");
        System.out.println("4. Daftar Anggota");
        System.out.println("5. Logout");
        
        System.out.print("\nMasukkan no menu : ");
    }
    
    public static void getPeminjamanMenu(Petugas petugas,Jdbc service) {
        Scanner in = new Scanner(System.in);
        System.out.println("Menu Peminjaman Buku : \n");
//         List<Buku> bukuR = service.getAllBuku();
//                    System.out.println(bukuR.toString());
//                    if (bukuR == null) {
//                        System.out.println("Mohon maaf, buku kosong.");
//                    } else {
//                        System.out.println("Kodebuku\tJudul\t\t\tPengarang\t\t\tTahun");
//                        for (Buku book : bukuR) {
//                            System.out.println(book.getKodebuku()+"\t"+book.getJudul()+"\t\t\t"+book.getPengarang()+"\t\t\t"+book.getTahun());
//                        }                        
//                    }
        System.out.println("0. Kembali ke Menu Utama");
        
        System.out.print("\nPilihan : ");
        String pilih = in.nextLine();
        switch(pilih) {
            case "0":
                break;
        }
    }
    
    public static void getPengembalianMenu(Petugas petugas,Jdbc service) {
        System.out.println("Menu Pengembalian Buku : \n");
        System.out.println("0. Kembali ke Menu Utama");
        
        System.out.print("\nPilihan : ");
    }
    
    //public static void getHistoriMenu() {
     //   System.out.println("Menu Histori Peminjaman : \n");
      //  System.out.println("0. Kembali ke Menu Utama");

        //System.out.print("\nPilihan : ");
    //}
    
    public static void getAnggotaMenu(Jdbc service) {
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while(active == true) {
            System.out.println("\nMenu Daftar Anggota : \n");
            System.out.println("1. Lihat Daftar Anggota");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Ubah Biodata");
            System.out.println("4. Hapus Data Anggota");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<Mahasiswa> anggotaR = service.getAllMahasiswa();
                    //System.out.println(anggotaR.toString());
                    if (anggotaR ==null) {
                        System.out.println("Belum ada anggota yang terdaftar. Silakan tambahkan anggota terlebih dahulu!");
                    } else {
                        System.out.println("NPM\tNama\t\t\tJurusan\t\t\tAlamat");
                        for (Mahasiswa anggota : anggotaR) {
                            System.out.println(anggota.getNpm()+"\t"+anggota.getNama()+"\t\t\t"+anggota.getJurusan()+"\t\t\t"+anggota.getAlamat());
                        }                        
                    }

                    break;
                case "2":
                    System.out.print("NPM : ");
                    String npm = in.nextLine();
                    System.out.print("Nama : ");
                    String nama = in.nextLine();
                    System.out.print("Jurusan : ");
                    String jurusan = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                    Mahasiswa mhs = new Mahasiswa();
                     mhs.setNpm(Integer.parseInt(npm));
                        mhs.setNama(nama);
                        mhs.setAlamat(jurusan);
                        mhs.setJurusan(alamat);
                        service.save(mhs);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    
   public static void getBukuMenu() {
        System.out.println("Menu Pengelolaan Buku : \n");
        System.out.println("0. Kembali ke Menu Utama");
        
        System.out.print("\nPilihan : ");
    }
    
}
