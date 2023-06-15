/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Safe
 */
public class Donation {
    int id,emergency_id;
    String description,don_location,email,phone_number;
    LocalDate donation_date;

      public Donation(int id, int emergency_id, String description, String don_location, String email, String phone_number, LocalDate donation_date) {
            this.id = id;
            this.emergency_id = emergency_id;
            this.description = description;
            this.don_location = don_location;
            this.email = email;
            this.phone_number = phone_number;
            this.donation_date = donation_date;
      }

      public Donation(String description, String don_location, String email, String phone_number, LocalDate donation_date) {
            this.description = description;
            this.don_location = don_location;
            this.email = email;
            this.phone_number = phone_number;
            this.donation_date = donation_date;
      }

      public Donation(int id) {
            this.id = id;
      }

      public Donation(int id, String description, String don_location, String email, String phone_number, LocalDate donation_date) {
            this.id = id;
            this.description = description;
            this.don_location = don_location;
            this.email = email;
            this.phone_number = phone_number;
            this.donation_date = donation_date;
      }

      

      public Donation() {
      }

     

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public int getEmergency_id() {
            return emergency_id;
      }

      public void setEmergency_id(int emergency_id) {
            this.emergency_id = emergency_id;
      }

      public String getDescription() {
            return description;
      }

      public void setDescription(String description) {
            this.description = description;
      }

      public String getDon_location() {
            return don_location;
      }

      public void setDon_location(String don_location) {
            this.don_location = don_location;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getPhone_number() {
            return phone_number;
      }

      public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
      }

      public LocalDate getDonation_date() {
            return donation_date;
      }

      public void setDonation_date(LocalDate donation_date) {
            this.donation_date = donation_date;
      }

      

      @Override
      public String toString() {
            return "Donation{" + "id=" + id + ", emergency_id=" + emergency_id + ", description=" + description + ", don_location=" + don_location + ", email=" + email + ", phone_number=" + phone_number + ", donation_date=" + donation_date + '}';
      }

    
   
}
