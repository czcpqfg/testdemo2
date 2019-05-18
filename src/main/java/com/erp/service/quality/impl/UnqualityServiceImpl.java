package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.Unqualify;
import com.erp.mapper.quality.UnqualifyMapper;
import com.erp.service.quality.UnqualityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnqualityServiceImpl implements UnqualityService {

    @Autowired
    UnqualifyMapper unqualifyMapper;

    @Override
    public List<Unqualify> selectAllUnqualify(){
        return unqualifyMapper.selectAllUnqualify();
    }

    @Override
    public QueryVO selectPageUnqualify(int page, int rows){
        int total = unqualifyMapper.countAllUnqualify();
        PageHelper.startPage(page, rows);
        List<Unqualify> unqualifies = unqualifyMapper.selectAllPageUnqualifyLeft();
        return new QueryVO(total, unqualifies);
    }

    @Override
    public boolean insertUnqualify(Unqualify unqualify){
        int insert = unqualifyMapper.insert(unqualify);
        return insert == 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteUnqualifyById(String[] ids){
        for (String id : ids) {
            int key = unqualifyMapper.deleteByPrimaryKey(id);
            if (key != 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public QueryVO searchUnqualifyByUnqualifyId(String searchValue, int page, int rows){
        int total = unqualifyMapper.countAllUnqualify();
        PageHelper.startPage(page, rows);
        List<Unqualify> unqualifies = unqualifyMapper.selectAllPageUnqualifyLeftByUnqualifyId(searchValue);
        return new QueryVO(total, unqualifies);
    }

    @Override
    public boolean updateUnqualifyByUnqualifyId(Unqualify unqualify){
        int update = unqualifyMapper.updateByPrimaryKey(unqualify);
        return update == 1;
    }

    @Override
    public QueryVO searchUnqualifyByProductName(String searchValue, int page, int rows) {
        int total = unqualifyMapper.countAllUnqualify();
        PageHelper.startPage(page, rows);
        List<Unqualify> unqualifies = unqualifyMapper.selectAllPageUnqualifyLeftByProductName(searchValue);
        return new QueryVO(total, unqualifies);
    }
}
