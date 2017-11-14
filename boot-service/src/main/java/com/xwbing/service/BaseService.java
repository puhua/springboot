package com.xwbing.service;

import com.xwbing.util.RestMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

/**
 * 说明:
 * 项目名称: boot-module-demo
 * 创建时间: 2017/5/5 16:44
 * 作者:  xiangwb
 */
public class BaseService<T> {
    private JpaRepository<T, String> jpaRepository;

    public BaseService(JpaRepository<T, String> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * 增
     *
     * @param t
     * @return
     */
    public RestMessage saveOrUpdate(T t) {
        RestMessage result = new RestMessage();
        t = jpaRepository.save(t);
        if (Objects.isNull(t)) {
            result.setMessage("保存数据失败");
        } else {
            result.setSuccess(true);
            result.setMessage("保存数据成功");
        }
        return result;
    }

    /**
     * 删
     *
     * @param id
     * @return
     */
    public RestMessage removeById(String id) {
        RestMessage result = new RestMessage();
        jpaRepository.delete(id);
        result.setSuccess(true);
        result.setMessage("删除数据成功");
        return result;
    }

    /**
     * 单个查找
     *
     * @param id
     * @return
     */
    public T findById(String id) {
        return jpaRepository.findOne(id);
    }

    /**
     * 列表查找
     *
     * @return
     */
    public List<T> findList() {
        return jpaRepository.findAll();
    }
}