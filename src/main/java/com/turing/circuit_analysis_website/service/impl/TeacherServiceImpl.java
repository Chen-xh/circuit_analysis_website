package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.RoleDao;
import com.turing.circuit_analysis_website.dao.TeacherDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Resources;
import com.turing.circuit_analysis_website.pojo.Role;
import com.turing.circuit_analysis_website.pojo.Teacher;
import com.turing.circuit_analysis_website.service.TeacherService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.util.MD5Util;
import com.turing.circuit_analysis_website.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  19:08
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    FileUploadUtil fileUploadUtil;
    @Override
    public Teacher getTeacherById(Long id) {
        Teacher Teacher = teacherDao.findByTeacherId(id);
        if (Teacher == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEACHER);
        }
        return Teacher;
    }

  

    @Override
    public List<Teacher> findAll() {
        List<Teacher> Teachers = teacherDao.findAll();
        if (Teachers == null || Teachers.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEACHER);
        }
        return Teachers;
    }

    @Override
    public void add(TeacherVo Teacher, MultipartFile file) {
        if(file==null) {
            //文件不能为空
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FILE_IS_NULL);
        }
        String fileName = fileUploadUtil.getNewFileName(file);
        fileUploadUtil.uploadOneFile(file, "resources/"+fileName);
        fileName="/static/resources/"+fileName;

        Teacher newOne = new Teacher();
        newOne.setTeacherAwardIntroduction(Teacher.getTeacherAwardIntroduction());
        newOne.setTeacherBorn(Teacher.getTeacherBorn());
        newOne.setTeacherEmail(Teacher.getTeacherEmail());
        newOne.setTeacherGraduation(Teacher.getTeacherGraduation());
        newOne.setTeacherJob(Teacher.getTeacherJob());
        newOne.setTeacherName(Teacher.getTeacherName());
        newOne.setTeacherPosition(Teacher.getTeacherPosition());
        newOne.setTeacherResearch(Teacher.getTeacherResearch());
        newOne.setTeacherScientificResearch(Teacher.getTeacherScientificResearch());
        newOne.setTeacherImg(fileName);
        teacherDao.saveAndFlush(newOne);

    }

    @Override
    public void update(TeacherVo teacherVo, MultipartFile file) {
        System.out.println(teacherVo.getTeacherId());
        Teacher tem=teacherDao.findByTeacherId(teacherVo.getTeacherId());
        if (tem == null ) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEACHER);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getTeacherImg());

            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "resources/"+fileName);
            fileName="/static/resources/"+fileName;
            tem.setTeacherImg(fileName);
        }
        tem.setTeacherAwardIntroduction(teacherVo.getTeacherAwardIntroduction());
        tem.setTeacherBorn(teacherVo.getTeacherBorn());
        tem.setTeacherEmail(teacherVo.getTeacherEmail());
        tem.setTeacherGraduation(teacherVo.getTeacherGraduation());
        tem.setTeacherJob(teacherVo.getTeacherJob());
        tem.setTeacherName(teacherVo.getTeacherName());
        tem.setTeacherPosition(teacherVo.getTeacherPosition());
        tem.setTeacherResearch(teacherVo.getTeacherResearch());
        tem.setTeacherScientificResearch(teacherVo.getTeacherScientificResearch());
        teacherDao.saveAndFlush(tem);
    }

    @Override
    public void delete(Long id) {

        Teacher Teacher = teacherDao.findByTeacherId(id);
        if (Teacher == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEACHER);
        }
        teacherDao.delete(Teacher);

    }
}
