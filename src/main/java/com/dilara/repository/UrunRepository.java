package com.dilara.repository;

import com.dilara.entity.Kategori;
import com.dilara.entity.Urun;
import com.dilara.utility.ICrud;
import com.dilara.utility.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class UrunRepository extends Repository<Urun, Long> {
    public UrunRepository(){
        super(new Urun());
    }
}
