package org.arturs.SimpleServer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kids")
public class Kids {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String childName;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel userModel;

}
