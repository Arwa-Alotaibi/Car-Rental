package com.example.carrental.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String violation_detail;
    private String violation_image;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "insurance")
    private List<Car> carList;

}
