/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

/**
 *
 * @author ASUS
 */

import uts.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DatabaseManajer {
    // URL koneksi untuk database SQLite
    private static final String DB_URL = "jdbc:sqlite:buku_alamat.db";
    // Metode untuk membuat tabel
    public DatabaseManajer() {
        initializeDatabase();
}
     private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS kontak (" + //Buat tabel
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " + //Id otomatis
                    "nama TEXT NOT NULL, " + //Nama
                    "telepon TEXT, " + //Telepon
                    "email TEXT, " + //Email
                    "alamat TEXT)"; //Alamat
            stmt.execute(sql); //Perintah execute
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     // Metode untuk menambahkan data kontak baru ke tabel
    public void tambahKontak(Contact kontak) {
        String sql = "INSERT INTO kontak(nama, telepon, email, alamat) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             // Mengisi parameter SQL dengan data dari objek Contact
            pstmt.setString(1, kontak.getNama());
            pstmt.setString(2, kontak.getTelepon());
            pstmt.setString(3, kontak.getEmail());
            pstmt.setString(4, kontak.getAlamat());
            pstmt.executeUpdate(); // Menyimpan data ke database
        } catch (SQLException e) {
            e.printStackTrace();  // Menangani kesalahan eksekusi SQL
        }
    }

    // Metode untuk mengambil semua data kontak dari tabel
    public List<Contact> ambilSemuaKontak() {
        List<Contact> kontakList = new ArrayList<>();  // Menampung daftar kontak
        String sql = "SELECT * FROM kontak";  // SQL untuk mengambil semua data
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             // Loop untuk membaca data dari ResultSet
            while (rs.next()) {
                Contact kontak = new Contact();
                kontak.setId(rs.getInt("id"));
                kontak.setNama(rs.getString("nama"));
                kontak.setTelepon(rs.getString("telepon"));
                kontak.setEmail(rs.getString("email"));
                kontak.setAlamat(rs.getString("alamat"));
                kontakList.add(kontak); // Menambahkan kontak ke daftar
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kontakList;
    }

    // Metode untuk memperbarui data kontak berdasarkan ID
    public void perbaruiKontak(Contact kontak) {
         // SQL untuk memperbarui data kontak di tabel
        String sql = "UPDATE kontak SET nama=?, telepon=?, email=?, alamat=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             // Mengisi parameter SQL dengan data baru dari objek Contact
            pstmt.setString(1, kontak.getNama());
            pstmt.setString(2, kontak.getTelepon());
            pstmt.setString(3, kontak.getEmail());
            pstmt.setString(4, kontak.getAlamat());
            pstmt.setInt(5, kontak.getId());
            pstmt.executeUpdate();  // Eksekusi pembaruan data
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     // Metode untuk menghapus data kontak berdasarkan ID
    public void hapusKontak(int id) {
         // SQL untuk menghapus data kontak dari tabel
        String sql = "DELETE FROM kontak WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // idnya
            pstmt.executeUpdate(); // Eksekusi penghapusan data
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
