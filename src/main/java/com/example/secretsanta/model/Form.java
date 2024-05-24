package com.example.secretsanta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "forms")
public class Form {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User user;

  @ManyToOne
  @JoinColumn(name = "room_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Room room;

  @Column(name = "letter")
  private String letter;

  @ManyToOne
  @JoinColumn(name = "receiver_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User receiver;
}
