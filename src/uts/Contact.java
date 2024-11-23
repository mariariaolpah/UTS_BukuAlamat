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

public class Contact {
    private int id;
    private String nama;
    private String telepon;
    private String email;
    private String alamat;
    
    public Contact() {}

    public Contact(String nama, String telepon, String email, String alamat) {
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
        this.alamat = alamat;
    }
     // Getter and setter methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getTelepon() { return telepon; }
    public void setTelepon(String telepon) { this.telepon = telepon; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    @Override
    public String toString() {
        return nama + " - " + telepon;
    }
}
