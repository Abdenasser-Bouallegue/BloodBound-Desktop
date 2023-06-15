/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Donation;
import Utils.MyDB;
import java.sql.Connection;
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
public class ServicesDonation implements Services.IServices<Donation> {
    Connection cnx;
    @Override
    public void add(Donation t) {
      try {
        String qry ="INSERT INTO `donation`( `description`, `don_location`, `email`,`phone_number`,`donation_date`) VALUES ('"+t.getDescription()+"','"+t.getDon_location()+"','"+t.getEmail()+"','"+t.getPhone_number()+"','"+t.getDonation_date()+"')";
        cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Donation> afficher() {
 List<Donation> eme = new ArrayList();
        try {
            String qry ="SELECT * FROM `donation`";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Donation m =new Donation();
                m.setId(rs.getInt("id"));
                m.setDescription(rs.getString("description"));
                m.setDon_location(rs.getString("don_location"));
                m.setEmail(rs.getString("email"));
                m.setPhone_number(rs.getString("phone_number"));
                m.setDonation_date(rs.getDate("donation_date").toLocalDate());
                eme.add(m);
            }
            return eme;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eme;    }

    @Override
    public void modifier(Donation t) {
      try {
            String qry = "UPDATE `donation` SET `description`='" +t.getDescription()+"','"+ "', `don location`='" + t.getDon_location()+  "', `email`='" + t.getEmail()+ "', `phone`='" + t.getPhone_number()+ "', `date`='" + t.getDonation_date()+"'  WHERE `id`='" + t.getId()+ "'";
      
      cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            System.out.println("Success!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    public void supprimer(Donation t) {
 try {
           
            String qry="delete from `donation`  WHERE `id`='" + t.getId()+ "'";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
              System.out.println("Row deleted successfully!");
        }   catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }   }
    
    
     public int foundOneByEmail(String t) {
 List<Donation> eme = new ArrayList();
                 Donation test =new Donation();

        try {
            String qry ="SELECT * FROM `donation` WHERE `email`='"+t+"'";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Donation m =new Donation();
                m.setId(rs.getInt("id"));
                m.setDescription(rs.getString("description"));
                m.setDon_location(rs.getString("don_location"));
                m.setEmail(rs.getString("email"));
                m.setPhone_number(rs.getString("phone_number"));
                m.setDonation_date(rs.getDate("donation_date").toLocalDate());
                eme.add(m);
                
                test=m;
            }
           
            return  eme.size();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eme.size();    }
    
}
