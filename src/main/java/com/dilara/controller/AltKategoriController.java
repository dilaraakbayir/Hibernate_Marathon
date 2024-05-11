package com.dilara.controller;

import com.dilara.entity.AltKategori;
import com.dilara.entity.Kategori;
import com.dilara.service.AltKategoriService;
import com.dilara.service.KategoriService;
import com.dilara.utility.Response;

import java.util.List;
import java.util.Scanner;

public class AltKategoriController {
    private final AltKategoriService altKategoriService;
    public AltKategoriController(){
        this.altKategoriService = new AltKategoriService();
    }
    public void altKategoriEkle(){
        boolean isAdded = false;
        do{
            System.out.println("""
                ************************
                ******** ALT KATEGORİ EKLE ********
                ************************
                """);
            System.out.println("Alt Kategori adı: ");
            String ad = new Scanner(System.in).nextLine();
            System.out.println("Kategori id: ");
            Long kategoriId = new Scanner(System.in).nextLong();

            if (ad.isEmpty()){
                System.out.println("Lütfen alt kategori adı giriniz.");
            }else{
                Response<Boolean> response = altKategoriService.altKategoriEkle(ad, kategoriId);
                isAdded = response.getData();
                System.out.println(response.getMessage());
            }
        }while(!isAdded);
    }
    public List<AltKategori> altKategoriListele(){
        System.out.println(altKategoriService.findAll());
        return altKategoriService.findAll();
    }
}
