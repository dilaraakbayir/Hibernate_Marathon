package com.dilara.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NamedQuery(name="AltKategori.isExist",query="select count(a)>0 from AltKategori a where a.ad= :ad")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tblalt_kategori")
public class AltKategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String ad;

    private Long kategoriId;

}
