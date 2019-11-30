package com.hzm.service;

import com.hzm.dao.LabelDao;
import com.hzm.entity.Label;
import com.hzm.util.IdWorker;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    IdWorker idWorker;
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");//生成id
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label){
        //条件查询
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 根对象，也就是要把条件封装到哪个对象中，然后查询时就用，比如where id = label.id
             * @param criteriaQuery 封装的都是查询关键字（一般不会用到），比如group by ，order by
             * @param criteriaBuilder 用来封装条件对象 如果方法返回null表示不用任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //新建list存放所有条件
                List<Predicate> predicateList = new ArrayList<>();
                if(label.getLabelname() != null &&!"".equals(label.getLabelname())){
                    //where labelname like "%小明%"
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    predicateList.add(predicate);//保存条件
                }
                if(label.getState() != null &&!"".equals(label.getState())){
                    //where state = "1"
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),"%"+label.getState()+"%");
                    predicateList.add(predicate);//保存条件
                }
                //数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[predicateList.size()];
                //把list转为数组
                predicateList.toArray(predicates);//直接转到predicates数组了
                return criteriaBuilder.and(predicates);//将所有的条件用and连接
            }
        });
    }

    public Page<Label> findQuery(Label label, int page, int size){
        //封装分页对象
        Pageable pageable = PageRequest.of(page-1,size);//pageable的页码是从0开始的
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 根对象，也就是要把条件封装到哪个对象中，然后查询时就用，比如where id = label.id
             * @param criteriaQuery 封装的都是查询关键字（一般不会用到），比如group by ，order by
             * @param criteriaBuilder 用来封装条件对象 如果方法返回null表示不用任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //新建list存放所有条件
                List<Predicate> predicateList = new ArrayList<>();
                if(label.getLabelname() != null &&!"".equals(label.getLabelname())){
                    //where labelname like "%小明%"
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    predicateList.add(predicate);//保存条件
                }
                if(label.getState() != null &&!"".equals(label.getState())){
                    //where state = "1"
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),"%"+label.getState()+"%");
                    predicateList.add(predicate);//保存条件
                }
                //数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[predicateList.size()];
                //把list转为数组
                predicateList.toArray(predicates);//直接转到predicates数组了
                return criteriaBuilder.and(predicates);//将所有的条件用and连接
            }
        },pageable);
    }
}