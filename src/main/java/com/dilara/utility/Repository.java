package com.dilara.utility;

import com.dilara.entity.Urun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Repository<T, ID> implements ICrud<T, ID> {
    private final EntityManagerFactory emf;
    private EntityManager em;
    private T t; //bu olmasaydı T.class çalışmazdı
    public Repository(T entity){
        emf = Persistence.createEntityManagerFactory("hmarathon");
        em = emf.createEntityManager();
        this.t = entity;
    }
    private void openSession(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    private void closeSession(){
        em.getTransaction().commit();
        em.close();
    }
    private void rollback(){
        em.getTransaction().rollback();
        em.close();
    } //veritabanı başladığı yere geri döner, işlemleri geri alır

    public void openEntityManager(){
        em = emf.createEntityManager();
    }



    @Override
    public T save(T entity) {
        openSession();
        em.persist(entity);
        closeSession();
        return entity;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        try{
            openSession();
            entities.forEach(em::persist);
            closeSession();
        }catch(Exception ex){
            rollback();
        }
        return entities;
    }

    /**
     * select * from tbl??? where id = ?
     * @param id
     * @return
     */
    @Override
    public Optional<T> findById(ID id) {
        openSS();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        //bu şekilde kast ettik çünkü belli bir sınıfımız olmadığı için ne döneceğimiz belli değildi
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root); //select * from
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id)); //where id = ?
        T result;
        try{
            /**
             * Burada sorgumuz zaten tek bir sonuç dönecek ya da hiç dönmeyeceği için bulduğu ilk
             * sonucu vermesini istiyoruz.
             */
            result = em.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(result);
        }catch (NoResultException exception){
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(ID id) {
        openSS();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        //bu şekilde kast ettik çünkü belli bir sınıfımız olmadığı için ne döneceğimiz belli değildi
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root); //select * from
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id)); //where id = ?
        try{
            em.createQuery(criteriaQuery).getSingleResult();
            return true;
        }catch (NoResultException exception){
            return false;
        }
    }

    private void openSS() {
        if(!em.isOpen())
            em = emf.createEntityManager();
    }

    @Override
    public List<T> findAll() {
        openSS();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        //bu şekilde kast ettik çünkü belli bir sınıfımız olmadığı için ne döneceğimiz belli değildi
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root); //select * from
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * select * from tbl??? where columnName = value
     * @param columnName
     * @param value
     * @return
     */
    @Override
    public List<T> findAllByFromColumnAndValue(String columnName, Object value) {
        openSS();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        //bu şekilde kast ettik çünkü belli bir sınıfımız olmadığı için ne döneceğimiz belli değildi
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root); //select * from
        criteriaQuery.where(criteriaBuilder.equal(root.get(columnName),value)); //where id = ?
        return em.createQuery(criteriaQuery).getResultList();
        //getResultList boşsa da boş liste döner, o yüzden null check yapmamıza gerek yok
    }
    public List<T> ilkOnElemaniGetir(String ad) {
        openSS();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("ad"),"%"+ad+"%"));
        List<T> results = em.createQuery(criteriaQuery).setMaxResults(10).getResultList();
        return results;
    }

    @Override
    public void deleteById(ID id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        //bu şekilde kast ettik çünkü belli bir sınıfımız olmadığı için ne döneceğimiz belli değildi
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root); //select * from
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id)); //where id = ?
        T removeElement;
        try{
            removeElement = em.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException exception){
            removeElement = null;
        }
        try{
            openSession();
            em.remove(removeElement);
            closeSession();
        }catch (Exception e){
            if (em.isOpen())
                rollback();
        }
        //veya
        /**
         try{
         openSession();
         em.remove(removeElement);
         closeSession();
         }catch (NoResultException exception){
         if (em.isOpen())
         rollback();
         }
         */
    }

    /**
     * Java Reflection
     * Long userid -> if(userid!=null) sorguya dahil et -> userid, value
     * @param entity
     * @return
     */
    @Override
    public List<T> findAllByEntity(T entity) {
        openSS();
        List<T> result;
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root);
        /**
         * where
         * içeriği null olmayan değişkenlerin where içerisine predicate olarak
         * eklenmesini sağlamak
         */
        List<Predicate> predicateList = new ArrayList<>();
        for (int i=1; i< fields.length; i++){ //entity içinden aldığımız alanları dönüyoruz
            /**
             * DİKKAT !!!
             * Bir field erişim belirteçleri ile erişime kapalı olabilir.
             * Bu nedenle öncelikle bunları açmak gerekir.
             */
            fields[i].setAccessible(true);
            try{
                /**
                 * Erişime açtığımı field'ların adlarını ve değerlerini okuyoruz
                 */
                String column = fields[i].getName();
                Object value = fields[i].get(entity);
                if (value!=null){
                    if (value instanceof String){
                        predicateList.add(criteriaBuilder.like(root.get(column),"%"+value+"%"));
                    }else{
                        predicateList.add(criteriaBuilder.equal(root.get(column),value));
                    }
                }
            }catch(Exception exception){
                System.out.println("Hata oluştu: "+exception);
            }
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[]{}));
        result = em.createQuery(criteriaQuery).getResultList();
        return result;
    }
}
