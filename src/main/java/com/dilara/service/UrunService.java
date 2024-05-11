package com.dilara.service;

import com.dilara.entity.Kategori;
import com.dilara.entity.Urun;
import com.dilara.repository.AltKategoriRepository;
import com.dilara.repository.KategoriRepository;
import com.dilara.repository.UrunRepository;
import com.dilara.utility.Response;

import java.util.List;
import java.util.Optional;

public class UrunService {
    private final UrunRepository urunRepository;
    private final KategoriRepository kategoriRepository;
    private final AltKategoriRepository altKategoriRepository;
    public UrunService(){
        this.urunRepository = new UrunRepository();
        this.kategoriRepository = new KategoriRepository();
        this.altKategoriRepository = new AltKategoriRepository();
    }

    public Response<Boolean> urunEkle(String ad, Long kategoriId, Long altKategoriId, String aciklama, double fiyat, int stok){
        Response<Boolean> response = new Response<>();
        if (!kategoriRepository.existsById(kategoriId)) {
            response.setData(false);
            response.setMessage("Bu kategori mevcut değildir.");
            response.setStatusCode(400);
            return response;
        }
        if (!altKategoriRepository.existsById(altKategoriId)) {
            response.setData(false);
            response.setMessage("Bu alt kategori mevcut değildir.");
            response.setStatusCode(400);
            return response;
        }
        urunRepository.save(Urun.builder()
                .ad(ad)
                .kategoriId(kategoriId)
                .altKategoriId(altKategoriId)
                .aciklama(aciklama)
                .fiyat(fiyat)
                .stok(stok)
                .build());
        response.setData(true);
        response.setStatusCode(200);
        response.setMessage("Ürün başarıyla kaydedilmiştir");
        return response;
    }

    public List<Urun> ilkOnElemaniGetir(String ad) {
        return urunRepository.ilkOnElemaniGetir(ad);
    }
}
