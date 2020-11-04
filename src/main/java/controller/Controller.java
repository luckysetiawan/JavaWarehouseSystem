/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Lucky Setiawan
 */
public class Controller {
    static DBHandler conn = new DBHandler();
    
     //SELECT WHERE
    
    public static Person getPerson(int intuid) {
        conn.connect();
        Person person = new Person();
        String query = "SELECT * FROM user WHERE uid='" + intuid + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setUid(rs.getString("uid"));
                user.setNama(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setUser_Type(rs.getString("user_type"));
                user.setMembership_Status(rs.getString("membership_status"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (person);
    }
    
    // INSERT
    
    public static boolean insertNewPerson(int intuid, String strusername, String strpassword, String stremail, String strddress, boolean boolmembership_status) {
        conn.connect();
        int id;
        String query = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, intuid);
            stmt.setString(2, strusername);
            stmt.setString(3, strpassword);
            stmt.setString(4, stremail);
            stmt.setString(5, straddress);
            stmt.setString(6, boolmembership);            
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    // UPDATE
    
    public static boolean updatePerson(int intuid, String strusername, String strpassword, String stremail, String straddress, boolean boolmembership_status) {
        conn.connect();
        String query = "UPDATE user SET uid='" + intuid + "', "
                + "username='" + strusername + "', "
                + "password='" + strpassword + "', "
                + "email='" + stremail + "', "
                + "address='" + straddress + "', "
                + "membership_status='" + boolmembership_status + "', "
                + "alamat='" + stralamat + "', "
                + "rt='" + strrt + "', "
                + "kel='" + strkel + "', "
                + "kec='" + strkec + "', "
                + "agama='" + stragama + "', "
                + "kawin='" + strnikah + "', "
                + "kerja='" + strkerja + "', "
                + "warga='" + strwarga + "', "
                + "berlaku='" + strberlaku + "', "
                + "foto='" + pathFoto + "', "
                + "kota_buat='" + strkotapem + "', "
                + "tanggal_buat='" + strtanggalpem + "', "
                + "ttd='" + pathTtd + "' "
                + "WHERE nik='" + strnik + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    // DELETE
    
    public static boolean deletePerson(int intuid) {
        conn.connect();

        String query = "DELETE FROM user WHERE nik='" + intuid + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
}
