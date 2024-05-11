package com.dilara.service;

import com.dilara.entity.AltKategori;
import com.dilara.entity.Kategori;
import com.dilara.repository.AltKategoriRepository;
import com.dilara.repository.KategoriRepository;
import com.dilara.utility.Response;

import java.util.List;

public class AltKategoriService {
    private final AltKategoriRepository altKategoriRepository;
    public AltKategoriService(){
        this.altKategoriRepository = new AltKategoriRepository();
        this.kategoriRepository = new KategoriRepository();
    }
    private KategoriRepository kategoriRepository;
    public Response<Boolean> altKategoriEkle(String ad, Long kategoriId){
        Response<Boolean> response = new Response<>();
        if (altKategoriRepository.isExist(ad)){
            response.setData(false);
            response.setMessage("Bu alt kategori adı daha önce girilmiştir.");
            response.setStatusCode(400);
            return response;
        }
        if (!kategoriRepository.existsById(kategoriId)) {
            response.setData(false);
            response.setMessage("Bu kategori mevcut değildir.");
            response.setStatusCode(400);
            return response;
        }
        altKategoriRepository.save(AltKategori.builder()
                .ad(ad)
                .kategoriId(kategoriId)
                .build());
        response.setData(true);
        response.setStatusCode(200);
        response.setMessage("Alt kategori başarıyla kaydedilmiştir");
        return response;
    }
    public List<AltKategori> findAll(){
        return altKategoriRepository.findAll();
    }
}
