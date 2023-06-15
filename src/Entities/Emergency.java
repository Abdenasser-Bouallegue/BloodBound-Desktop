package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.scene.control.Alert;

public class Emergency {
    private int id;
    private int user_id;
    private String title;
    private String description;
    private String bloodType;
    private String location;
    private String status;
    private LocalDateTime createAt;
    private LocalDate deadline;

    public Emergency() {
    }

    public Emergency(int user_id, String title, String description, String bloodType, String location, String status, LocalDateTime createAt, LocalDate deadline) {
        this.user_id = user_id;
        this.setTitle(title);
        this.setDescription(description);
        this.setBloodType(bloodType);
        this.setLocation(location);
        this.setStatus(status);
        this.createAt = createAt;
        this.setDeadline(deadline);
    }

    public Emergency(String title, String description, String bloodType, String location, String status, LocalDate deadline) {
        this.setTitle(title);
        this.setDescription(description);
        this.setBloodType(bloodType);
        this.setLocation(location);
        this.setStatus(status);
        this.setDeadline(deadline);
    }

    public Emergency(int id) {
        this.id = id;
    }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public int getUser_id() {
            return user_id;
      }

      public void setUser_id(int user_id) {
            this.user_id = user_id;
      }

      public String getTitle() {
            return title;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      public String getDescription() {
            return description;
      }

      public void setDescription(String description) {
            this.description = description;
      }

      public String getBloodType() {
            return bloodType;
      }

      public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
      }

      public String getLocation() {
            return location;
      }

      public void setLocation(String location) {
            this.location = location;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }

      public LocalDateTime getCreateAt() {
            return createAt;
      }

      public void setCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
      }

      public LocalDate getDeadline() {
            return deadline;
      }

      public void setDeadline(LocalDate deadline) {
            this.deadline = deadline;
      }

    

   

   

      @Override
      public String toString() {
            return "Emergency{" + "id=" + id + ", user_id=" + user_id + ", title=" + title + ", description=" + description + ", bloodType=" + bloodType + ", location=" + location + ", status=" + status + ", createAt=" + createAt + ", deadline=" + deadline + '}';
      }

  

   
}
