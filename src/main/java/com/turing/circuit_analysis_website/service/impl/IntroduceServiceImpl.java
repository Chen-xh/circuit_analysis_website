package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.CommentDao;
import com.turing.circuit_analysis_website.dao.IntroduceDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Introduce;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.service.IntroduceService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.IntroduceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:29
 */
@Service
@Transactional
public class IntroduceServiceImpl implements IntroduceService {
    @Autowired
    IntroduceDao introduceDao;
    @Override
    public List<Introduce> findAll() {
        List<Introduce> introduces=introduceDao.findAll();
        if(introduces==null||introduces.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        return introduces;
    }

    @Override
    public List<Introduce> findAllByType(String type) {
        List<Introduce> introduces=introduceDao.findAllByType(type);
        if(introduces==null||introduces.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        return introduces;
    }

    @Override
    public Introduce findById(Long id) {
        Introduce introduce=introduceDao.findByIntId(id);
        if(introduce==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        return introduce;
    }

    @Override
    public Introduce getTeacherIntroduce() {
        Introduce introduce=introduceDao.findByIntId(2L);
        if(introduce==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        return introduce;
    }

    @Override
    public Introduce getCourseIntroduce() {
        Introduce introduce=introduceDao.findByIntId(9L);
        if(introduce==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        return introduce;
    }

    @Override
    public void add(IntroduceVo introduce) {
        Introduce newOne=new Introduce();
        newOne.setMessage(introduce.getMessage());
        newOne.setTitle(introduce.getTitle());
        newOne.setDate(new Date());
        if(introduce.getType()!=null)newOne.setType(introduce.getType());
        introduceDao.saveAndFlush(newOne);
    }

    @Override
    public void update(IntroduceVo introduce) {
        Introduce tem=introduceDao.findByIntId(introduce.getId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        tem.setMessage(introduce.getMessage());
        tem.setTitle(introduce.getTitle());
        tem.setDate(new Date());
        if(introduce.getType()!=null)tem.setType(introduce.getType());
        introduceDao.saveAndFlush(tem);
    }

    @Override
    public void delete(Long id) {
        Introduce introduce=introduceDao.findByIntId(id);
        if(introduce==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        introduceDao.delete(introduce);

    }
}
