package com.dilara.controller;

import com.dilara.entity.Kategori;
import com.dilara.entity.Urun;
import com.dilara.service.KategoriService;
import com.dilara.utility.Response;

import java.util.List;
import java.util.Scanner;

public class KategoriController {
    private final KategoriService kategoriService;
    public KategoriController(){
        this.kategoriService = new KategoriService();
    }
    public void kategoriEkle(){
        boolean isAdded = false;
        do{
            System.out.println("""
                ************************
                ******** KATEGORİ EKLE ********
                ************************
                """);
            System.out.println("Kategori adı: ");
            String ad = new Scanner(System.in).nextLine();

            if (ad.isEmpty()){
                System.out.println("Lütfen kategori adı giriniz.");
            }else{
                Response<Boolean> response = kategoriService.kategoriEkle(ad);
                isAdded = response.getData();
                System.out.println(response.getMessage());
            }
        }while(!isAdded);
    }
    public List<Kategori> kategoriListele(){
        System.out.println(kategoriService.findAll());
        return kategoriService.findAll();
    }
}
