package com.dilara.service;

import com.dilara.entity.Kategori;
import com.dilara.entity.Urun;
import com.dilara.repository.KategoriRepository;
import com.dilara.repository.UrunRepository;
import com.dilara.utility.Response;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class KategoriService {
    private final KategoriRepository kategoriRepository;
    public KategoriService(){
        this.kategoriRepository = new KategoriRepository();
    }
    public Response<Boolean> kategoriEkle(String ad){
        Response<Boolean> response = new Response<>();
        if (kategoriRepository.isExist(ad)){
            response.setData(false);
            response.setMessage("Bu kategori adı daha önce girilmiştir.");
            response.setStatusCode(400);
            return response;
        }
        kategoriRepository.save(Kategori.builder()
                .ad(ad)
                .build());
        response.setData(true);
        response.setStatusCode(200);
        response.setMessage("Kategori başarıyla kaydedilmiştir");
        return response;
    }

    public List<Kategori> findAll(){
        return kategoriRepository.findAll();
    }

    public Object findById(Long kategoriId) {
        return kategoriRepository.findById(kategoriId);
    }
}
