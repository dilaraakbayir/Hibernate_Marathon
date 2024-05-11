package com.dilara.repository;

import com.dilara.entity.Kategori;
import com.dilara.entity.Urun;
import com.dilara.utility.ICrud;
import com.dilara.utility.Repository;
import com.dilara.utility.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class KategoriRepository extends Repository<Kategori, Long> {
    public KategoriRepository(){
        super(new Kategori());
    }
    public boolean isExist(String ad){
        openEntityManager();
        TypedQuery<Boolean> typedQuery = getEm().createNamedQuery("Kategori.isExist",boolean.class);
        typedQuery.setParameter("ad",ad);
        return typedQuery.getSingleResult();
    }

}
