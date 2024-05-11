package com.dilara.controller;

import com.dilara.entity.Urun;
import com.dilara.service.KategoriService;
import com.dilara.service.UrunService;
import com.dilara.utility.Response;

import java.util.List;
import java.util.Scanner;

public class UrunController {
    private final UrunService urunService;
    private final KategoriService kategoriService;
    public UrunController(){
        this.urunService = new UrunService();
        this.kategoriService = new KategoriService();
    }

    public void urunEkle() {
        boolean isAdded = false;
        do {
            System.out.println("""
                    ******************************
                    ******** URUN EKLEME  ********
                    ******************************
                    """);
            System.out.print("Ürün adı: ");
            String ad = new Scanner(System.in).nextLine();
            System.out.println("Kategori id: ");
            Long kategoriId = new Scanner(System.in).nextLong();
            System.out.println("Alt Kategori id: ");
            Long altKategoriId = new Scanner(System.in).nextLong();
            System.out.println("Ürün açıklaması: ");
            String aciklama = new Scanner(System.in).nextLine();
            System.out.println("ürün fiyatı: ");
            double fiyat = new Scanner(System.in).nextDouble();
            System.out.println("Stok: ");
            int stok = new Scanner(System.in).nextInt();
            if (ad.isEmpty()){
                System.out.println("Lütfen ürün adı giriniz.");
            }else{
                Response<Boolean> response = urunService.urunEkle(ad, kategoriId, altKategoriId, aciklama, fiyat, stok);
                isAdded = response.getData();
                System.out.println(response.getMessage());
            }
        }while(!isAdded);
    }

    public void ilkOnUrunuGetir() {
        System.out.println("Aramak istediğiniz ürünü girin: ");
        String ad = new Scanner(System.in).nextLine();

        List<Urun> urunler = urunService.ilkOnElemaniGetir(ad);

        if (urunler.isEmpty()) {
            System.out.println("Arama sonucunda ürün bulunamadı.");
            return;
        }

        System.out.println("Kategori\t| Ürün Adı\t| Fiyat\t| Stok");
        System.out.println("-----------------------------------------------");
        for (Urun urun : urunler) {
            String kategoriAdi = kategoriService.findById(urun.getKategoriId()).getClass().getSimpleName();
            String satir = String.format("%s\t| %s\t| %.2f\t| %d",
                    kategoriAdi, urun.getAd(), urun.getFiyat(), urun.getStok());
            System.out.println(satir);
        }
    }

}
