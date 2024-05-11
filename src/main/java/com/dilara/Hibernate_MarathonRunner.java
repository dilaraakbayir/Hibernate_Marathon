package com.dilara;

import com.dilara.controller.AltKategoriController;
import com.dilara.controller.KategoriController;
import com.dilara.controller.UrunController;
import com.dilara.entity.Urun;
import com.dilara.repository.KategoriRepository;
import com.dilara.repository.UrunRepository;
import com.dilara.service.KategoriService;
import com.dilara.service.UrunService;

import java.util.Scanner;

public class Hibernate_MarathonRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KategoriController kategoriController = new KategoriController();
        AltKategoriController altKategoriController = new AltKategoriController();
        UrunController urunController = new UrunController();

        while (true) {
            System.out.println("""
                    ****** URUN YONETİM SİSTEMİ ******
                    **********************************
                    Yapmak istediğiniz işlemi seçiniz:
                    1- Kategori Ekle
                    2- Alt Kategori Ekle
                    3- Kategorileri Listele
                    4- Ürün Ekle
                    5- Ürün Ara
                    0- Çıkış
                    ***********************************
                    """);
            int secim = scanner.nextInt();
            switch (secim) {
                case 1:
                    kategoriController.kategoriEkle();
                    break;
                case 2:
                    altKategoriController.altKategoriEkle();
                    break;
                case 3:
                    kategoriController.kategoriListele();
                    altKategoriController.altKategoriListele();
                    break;
                case 4:
                    urunController.urunEkle();
                    break;
                case 5:
                    urunController.ilkOnUrunuGetir();
                    break;
                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }

    }
}
