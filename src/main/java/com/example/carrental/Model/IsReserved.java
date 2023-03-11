package com.example.carrental.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class IsReserved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate reserved_Date;

    @ManyToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    @JsonIgnore
    private Car car;
}
