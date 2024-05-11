package com.dilara.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tblurun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String ad;

    @Column(name = "kategoriId", nullable = false)
    private Long kategoriId;

    private Long altKategoriId;

    private String aciklama;

    private double fiyat;

    private int stok;
}
