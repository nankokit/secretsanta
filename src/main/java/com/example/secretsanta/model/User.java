package com.example.secretsanta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<Form> forms;

  @JsonIgnore
  @OneToMany(mappedBy = "master")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<Room> masterRooms;

  @JsonIgnore
  @OneToOne(mappedBy = "receiver")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Form mySantaForm;

  @JsonIgnore
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "forms",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "room_id"))
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<Room> rooms;
}
