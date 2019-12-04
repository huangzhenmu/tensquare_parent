package com.hzm.service;

import com.hzm.dao.EnterpriseDao;
import com.hzm.dao.RecuitDao;
import com.hzm.entity.EnterpriseEntity;
import com.hzm.entity.EnterpriseEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private IdWorker idWorker;

    public List<EnterpriseEntity> hotList(String ishot){
        return enterpriseDao.findByIshot(ishot);
    }

    public List<EnterpriseEntity> findAll(){
        return enterpriseDao.findAll();
    }

    public EnterpriseEntity findById(String id){
        return enterpriseDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(EnterpriseEntity enterprise){
        enterprise.setId(idWorker.nextId()+"");//生成id
        enterpriseDao.save(enterprise);
    }

    public void update(EnterpriseEntity enterprise){
        enterpriseDao.save(enterprise);
    }

    public void deleteById(String id){
        enterpriseDao.deleteById(id);
    }

    /*public List<EnterpriseEntity> findSearch(final EnterpriseEntity enterprise){
        //条件查询
        return enterpriseDao.findAll(new Specification<EnterpriseEntity>() {
            *//**
     * @param root 根对象，也就是要把条件封装到哪个对象中，然后查询时就用，比如where id = enterprise.id
     * @param criteriaQuery 封装的都是查询关键字（一般不会用到），比如group by ，order by
     * @param criteriaBuilder 用来封装条件对象 如果方法返回null表示不用任何条件
     * @return
     *//*
            @Override
            public Predicate toPredicate(Root<EnterpriseEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //新建list存放所有条件
                List<Predicate> predicateList = new ArrayList<>();
                if(enterprise.getEnterpriseEntityname() != null &&!"".equals(enterprise.getEnterpriseEntityname())){
                    //where enterprisename like "%小明%"
                    Predicate predicate = criteriaBuilder.like(root.get("enterprisename").as(String.class),"%"+enterprise.getEnterpriseEntityname()+"%");
                    predicateList.add(predicate);//保存条件
                }
                if(enterprise.getState() != null &&!"".equals(enterprise.getState())){
                    //where state = "1"
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),"%"+enterprise.getState()+"%");
                    predicateList.add(predicate);//保存条件
                }
                //数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[predicateList.size()];
                //把list转为数组
                predicateList.toArray(predicates);//直接转到predicates数组了
                return criteriaBuilder.and(predicates);//将所有的条件用and连接
            }
        });
    }*/

    /*public Page<EnterpriseEntity> findQuery(EnterpriseEntity enterprise, int page, int size){
        //封装分页对象
        Pageable pageable = PageRequest.of(page-1,size);//pageable的页码是从0开始的
        return enterpriseDao.findAll(new Specification<EnterpriseEntity>() {
            *//**
     * @param root 根对象，也就是要把条件封装到哪个对象中，然后查询时就用，比如where id = enterprise.id
     * @param criteriaQuery 封装的都是查询关键字（一般不会用到），比如group by ，order by
     * @param criteriaBuilder 用来封装条件对象 如果方法返回null表示不用任何条件
     * @return
     *//*
            @Override
            public Predicate toPredicate(Root<EnterpriseEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //新建list存放所有条件
                List<Predicate> predicateList = new ArrayList<>();
                if(enterprise.getEnterpriseEntityname() != null &&!"".equals(enterprise.getEnterpriseEntityname())){
                    //where enterprisename like "%小明%"
                    Predicate predicate = criteriaBuilder.like(root.get("enterprisename").as(String.class),"%"+enterprise.getEnterpriseEntityname()+"%");
                    predicateList.add(predicate);//保存条件
                }
                if(enterprise.getState() != null &&!"".equals(enterprise.getState())){
                    //where state = "1"
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),"%"+enterprise.getState()+"%");
                    predicateList.add(predicate);//保存条件
                }
                //数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[predicateList.size()];
                //把list转为数组
                predicateList.toArray(predicates);//直接转到predicates数组了
                return criteriaBuilder.and(predicates);//将所有的条件用and连接
            }
        },pageable);
    }*/
}
