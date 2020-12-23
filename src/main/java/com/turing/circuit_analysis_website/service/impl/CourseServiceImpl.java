package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.ChapterDao;
import com.turing.circuit_analysis_website.dao.CourseDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.CourseWare;
import com.turing.circuit_analysis_website.service.CourseService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:11
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    FileUploadUtil fileUploadUtil;
    @Autowired
    ChapterDao chapterDao;
    @Override
    public  Page<Course> findAll(int size, int page) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Course> courses = courseDao.findAll(pageable);
        if(courses.getContent() ==null|| courses.getContent().size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSEWARE);
        }
        return courses;
    }

    @Override
    public Course findById(long id) {
        Course course=courseDao.findByCouId(id);
        if(course==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSEWARE);
        }
        return course;
    }

    @Override
    public List<Course> findByCid(Long cid) {
        Chapter chapter=new Chapter();
        chapter.setChaId(cid);
        List<Course> courses = courseDao.findByChapter(chapter);
        if(courses==null|| courses.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSEWARE);
        }

        return courses;
    }

    @Override
    public void add(CourseVo courseVo, MultipartFile file) {
        Chapter chapter=chapterDao.findByChaId(courseVo.getCId());
        if(chapter==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_CHAPTER);
        }
        if(file==null) {
            //文件不能为空
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FILE_IS_NULL);
        }
        String fileName = fileUploadUtil.getNewFileName(file);
        fileUploadUtil.uploadOneFile(file, "course/"+fileName);
        fileName="/static/course/"+fileName;

        Course newOne=new Course();
        newOne.setTitle(courseVo.getTitle());
        newOne.setRes_url(fileName);
        newOne.setChapter(chapter);
        newOne.setDate(new Date());

        courseDao.saveAndFlush(newOne);
    }

    @Override
    public void update(CourseVo courseVo, MultipartFile file) {
        Course tem=courseDao.findByCouId(courseVo.getId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSEWARE);
        }
        Chapter chapter=chapterDao.findByChaId(courseVo.getCId());
        if(chapter==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_CHAPTER);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getRes_url());
            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "course/"+fileName);
            fileName="/static/course/"+fileName;
            tem.setRes_url(fileName);
        }
        tem.setTitle(courseVo.getTitle());
        tem.setChapter(chapter);
        tem.setDate(new Date());
        courseDao.saveAndFlush(tem);

    }

    @Override
    public void delete(Long id) {
        Course course=courseDao.findByCouId(id);
        if(course==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        fileUploadUtil.deletePhoto(course.getRes_url());
        courseDao.deleteById(id);
    }
}
