/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Emergency;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Safe
 */
public class ServicesEmergency implements Services.IServices<Emergency> {

      Connection cnx;

      @Override
      public void add(Emergency t) {
            try {
                  String qry = "INSERT INTO `emergency`( `title`, `description`, `blood_type`,`location`,`deadline`,`status`,`created_at`) VALUES ('" + t.getTitle() + "','" + t.getDescription() + "','" + t.getBloodType() + "','" + t.getLocation() + "','" + t.getDeadline() + "','" + t.getStatus() + "','" + t.getCreateAt() + "')";
                  cnx = MyDB.getInstance().getCnx();

                  Statement stm = cnx.createStatement();

                  stm.executeUpdate(qry);

            } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
            }
      }

      @Override
      public List<Emergency> afficher() {
            List<Emergency> eme = new ArrayList();
            try {
                  String qry = "SELECT * FROM `emergency`";
                  cnx = MyDB.getInstance().getCnx();
                  Statement stm = cnx.createStatement();
                  ResultSet rs = stm.executeQuery(qry);
                  while (rs.next()) {
                        Emergency m = new Emergency();
                        m.setId(rs.getInt("id"));
                        m.setTitle(rs.getString("title"));
                        m.setDescription(rs.getString("description"));
                        m.setBloodType(rs.getString("blood_type"));
                        m.setLocation(rs.getString("location"));
                        m.setDeadline(rs.getDate("deadline").toLocalDate());
                        m.setStatus(rs.getString("status"));
                        eme.add(m);
                  }
                  return eme;

            } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
            }
            return eme;
      }

      public Emergency getByid(int id) {
            List<Emergency> eme = new ArrayList();
            Emergency mm = new Emergency();

            try {
                  String qry = "SELECT * FROM `emergency` WHERE `id`=" + id + "";
                  cnx = MyDB.getInstance().getCnx();
                  Statement stm = cnx.createStatement();
                  ResultSet rs = stm.executeQuery(qry);
                  while (rs.next()) {
                        Emergency m = new Emergency();
                        m.setId(rs.getInt("id"));
                        m.setTitle(rs.getString("title"));
                        m.setDescription(rs.getString("description"));
                        m.setBloodType(rs.getString("blood_type"));
                        m.setLocation(rs.getString("location"));
                        m.setDeadline(rs.getDate("deadline").toLocalDate());
                        m.setStatus(rs.getString("status"));
                        mm = m;
                        eme.add(m);
                  }
                  return mm;

            } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
            }
            return mm;
      }

      @Override
      public void modifier(Emergency t) {
            try {
                  String qry = "UPDATE `emergency` SET `title`='" + t.getTitle() + "', `description`='" + t.getDescription() + "', `blood_type`='" + t.getBloodType() + "', `location`='" + t.getLocation() + "', `status`='" + t.getStatus() + "' WHERE `id`='" + t.getId() + "'";

                  cnx = MyDB.getInstance().getCnx();

                  Statement stm = cnx.createStatement();

                  stm.executeUpdate(qry);
                  System.out.println("Success!");
            } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
            }
      }

      @Override
      public void supprimer(Emergency t) {
            try {

                  String qry = "delete from `emergency`  WHERE `id`='" + t.getId() + "'";
                  cnx = MyDB.getInstance().getCnx();

                  Statement stm = cnx.createStatement();

                  stm.executeUpdate(qry);
                  System.out.println("Row deleted successfully!");
            } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
            }
      }
       public void supprimerByID(int t) {
            try {

                  String qry = "delete from `emergency`  WHERE `id`='" + t + "'";
                  cnx = MyDB.getInstance().getCnx();

                  Statement stm = cnx.createStatement();

                  stm.executeUpdate(qry);
                  System.out.println("Row deleted successfully!");
            } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
            }
      }
       
       public int CalculC() throws SQLException {
    String sql = "SELECT COUNT(*) AS count FROM emergency WHERE status = 'completed'";
     cnx = MyDB.getInstance().getCnx();
        PreparedStatement stm = cnx.prepareStatement(sql);
        ResultSet rs = stm.executeQuery(); 
    rs.next();
    int completCount = rs.getInt("count");
    return completCount;
}
    public int CalculNonC() throws SQLException {
    String sql = "SELECT COUNT(*) AS count FROM emergency WHERE status = 'in progress'";
    cnx = MyDB.getInstance().getCnx();
        PreparedStatement stm = cnx.prepareStatement(sql);
        ResultSet rs = stm.executeQuery(); 
    rs.next();
    int nonCompletCount = rs.getInt("count");
    return nonCompletCount;
}
    
    public List<Emergency> afficherById(int id) {
        List<Emergency> emergencies = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `emergency` WHERE id= '"+ id +"'";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Emergency m = new Emergency();
                        m.setId(rs.getInt("id"));
                        m.setTitle(rs.getString("title"));
                        m.setDescription(rs.getString("description"));
                        m.setBloodType(rs.getString("blood_type"));
                        m.setLocation(rs.getString("location"));
                         m.setStatus(rs.getString("status"));
                        m.setDeadline(rs.getDate("deadline").toLocalDate());
                       
                emergencies.add(m);
            }
            return emergencies;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return emergencies;
    }

}
