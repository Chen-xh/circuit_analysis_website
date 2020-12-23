package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.CommentDao;
import com.turing.circuit_analysis_website.dao.ProblemDao;
import com.turing.circuit_analysis_website.dao.UserDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.*;
import com.turing.circuit_analysis_website.service.ProblemService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:29
 */
@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    ProblemDao problemDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    FileUploadUtil fileUploadUtil;



    @Override
    public List<Problem> findAll() {
        List<Problem> problems=problemDao.findAll();
        if(problems==null||problems.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_PROLEM);
        }
        return problems;
    }

    @Override
    public Problem findById(Long id) {
        Problem problem=problemDao.findByProId(id);
        if(problem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_PROLEM);
        }
        List<Comment> list1=problem.getComments();
        for(int i=0;i<list1.size();i++){
            Comment comment=list1.get(i);
            List<Comment> tem=commentDao.findByRoot(comment.getComId());
            comment.setComments(tem);
            list1.set(i,comment);
        }
        problem.setComments(list1);
        return problem;
    }

    @Override
    public void add(ProblemVo problem, MultipartFile file) {
        User user=userDao.findUserByUsername(problem.getUserID()+"");
        if(user==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        Problem newOne=new Problem();
        if(file!=null) {
            //文件不为空
            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "other/"+fileName);
            fileName="/static/other/"+fileName;
            newOne.setRes_url(fileName);
        }
        newOne.setMessage(problem.getMessage());
        newOne.setDate(new Date());
        newOne.setUser(user);
        problemDao.saveAndFlush(newOne);
    }

    @Override
    public void update(ProblemVo problem, MultipartFile file) {
        Problem tem=problemDao.findByProId(problem.getId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_PROLEM);
        }

        if(problem.getUserID()!=null) {
            User user=userDao.findUserByUsername(problem.getUserID()+"");
            if (user == null) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
            }
            tem.setUser(user);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getRes_url());

            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "other/"+fileName);
            fileName="/static/other/"+fileName;
            tem.setRes_url(fileName);
        }
        tem.setMessage(problem.getMessage());
        tem.setDate(new Date());
        problemDao.saveAndFlush(tem);
    }

    @Override
    public void delete(Long id) {
        Problem problem=problemDao.findByProId(id);
        if(problem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_PROLEM);
        }

        List<Comment> comments=commentDao.findByProblem(problem);
        for(Comment comment1:comments){
            List<Comment> childs=commentDao.findByComment(comment1);
            for(Comment child1:childs){
                if(child1.getRes_url()!=null)fileUploadUtil.deletePhoto(child1.getRes_url());
                commentDao.delete(child1);
            }
            if(comment1.getRes_url()!=null)fileUploadUtil.deletePhoto(comment1.getRes_url());
            commentDao.delete(comment1);
        }
        if(problem.getRes_url()!=null)fileUploadUtil.deletePhoto(problem.getRes_url());
        problemDao.delete(problem);

    }
}
