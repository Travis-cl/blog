package com.chen.service;

import com.chen.dao.TypeDao;
import com.chen.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }
    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }
    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }
    @Transactional
    @Override
    public List<Type> getBlogType() {
        return typeDao.getBlogType();
    }
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }
    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
