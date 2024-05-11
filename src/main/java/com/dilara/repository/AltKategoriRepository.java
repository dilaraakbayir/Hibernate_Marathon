package com.dilara.repository;

import com.dilara.entity.AltKategori;
import com.dilara.entity.Kategori;
import com.dilara.entity.Urun;
import com.dilara.utility.ICrud;
import com.dilara.utility.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public class AltKategoriRepository extends Repository<AltKategori, Long> {
    public AltKategoriRepository(){
        super(new AltKategori());
    }

    public boolean isExist(String ad){
        openEntityManager();
        TypedQuery<Boolean> typedQuery = getEm().createNamedQuery("AltKategori.isExist",boolean.class);
        typedQuery.setParameter("ad",ad);
        return typedQuery.getSingleResult();
    }

}
