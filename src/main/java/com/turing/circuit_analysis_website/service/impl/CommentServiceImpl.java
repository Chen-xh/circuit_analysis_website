package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.CommentDao;
import com.turing.circuit_analysis_website.dao.ProblemDao;
import com.turing.circuit_analysis_website.dao.UserDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.service.CommentService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CommentVo;
import org.apache.commons.beanutils.converters.CharacterArrayConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CHEN
 * @date 2020/10/14  18:06
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ProblemDao problemDao;
    @Autowired
    FileUploadUtil fileUploadUtil;

    @Override
    public List<Comment> findAll() {
        List<Comment> comments=commentDao.findAll();
        if(comments==null||comments.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COMMENT);
        }
        return comments;
    }





    @Override
    public Comment findById(Long id) {
        Comment comment=commentDao.findByComId(id);
        if(comment==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COMMENT);
        }
        return comment;
    }

    @Override
    public void addToP(CommentVo comment ,Long proID, MultipartFile file){
        User user=userDao.findUserByUsername(comment.getUserID()+"");
        if(user==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        if(proID==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_PROLEM);
        }
        Comment newOne=new Comment();
        if(file!=null) {
            //文件不为空
            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "other/"+fileName);
            fileName="/static/other/"+fileName;
            newOne.setRes_url(fileName);
        }
        newOne.setMessage(comment.getMessage());
        newOne.setDate(new Date());
        newOne.setUser(user);
        Problem parent=problemDao.findByProId(proID);
        newOne.setProblem(parent);
        newOne.setRoot(0L);
        commentDao.saveAndFlush(newOne);
    }
    @Override
    public void addToC(CommentVo comment ,Long comID, MultipartFile file){
        User user=userDao.findUserByUsername(comment.getUserID()+"");
        if(user==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        if(comID==null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COMMENT);
        }
        Comment newOne=new Comment();
        if(file!=null) {
            //文件不为空
            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "other/"+fileName);
            fileName="/static/other/"+fileName;
            newOne.setRes_url(fileName);
        }
        newOne.setMessage(comment.getMessage());
        newOne.setDate(new Date());
        newOne.setUser(user);

        Comment parent=commentDao.findByComId(comID);
        newOne.setRoot(parent.getRoot());
        if(parent.getRoot()==0L)newOne.setRoot(parent.getComId());
        newOne.setComment(parent);

        commentDao.saveAndFlush(newOne);
    }

    @Override
    public void goodComment(Long id) {
        commentDao.goodComment(id);
    }

    @Override
    public void update(CommentVo comment, MultipartFile file) {
        Comment tem = commentDao.findByComId(comment.getId());
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COMMENT);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getRes_url());

            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "other/"+fileName);
            fileName="/static/other/"+fileName;
            tem.setRes_url(fileName);
        }
        tem.setMessage(comment.getMessage());
        tem.setDate(new Date());
        commentDao.saveAndFlush(tem);
    }

    @Override
    public void delete(Long id) {
        Comment comment=commentDao.findByComId(id);
        if(comment==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COMMENT);
        }
        List<Comment> comments=commentDao.findByComment(comment);
        for(Comment comment1:comments){
            if(comment1.getRes_url()!=null)fileUploadUtil.deletePhoto(comment1.getRes_url());
            commentDao.delete(comment1);
        }
        if(comment.getRes_url()!=null)fileUploadUtil.deletePhoto(comment.getRes_url());
        commentDao.delete(comment);
    }
}
