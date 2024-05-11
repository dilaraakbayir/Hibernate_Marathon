package com.dilara.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQuery(name="Kategori.isExist",query="select count(k)>0 from Kategori k where k.ad= :ad")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tblkategori")
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String ad;

}
