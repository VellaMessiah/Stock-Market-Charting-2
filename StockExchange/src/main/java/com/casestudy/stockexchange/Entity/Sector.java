package com.casestudy.stockexchange.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Sector {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "sector_name", nullable = false)
    private String sectorName;

    private String brief;

    @JsonManagedReference
    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, targetEntity = Company.class, fetch = FetchType.LAZY)
    private List<Company> companyList;
}
