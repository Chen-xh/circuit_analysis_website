package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.CourseWareDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.CourseWare;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.service.CourseWareService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CourseWareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.xml.ws.Action;
import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/15  19:17
 */
@Transactional
@Service
public class CourseWareServiceImpl implements CourseWareService {
    @Autowired
    CourseWareDao courseWareDao;
    @Autowired
    FileUploadUtil fileUploadUtil;
    @Override
    public List<CourseWare> findAll() {
        List<CourseWare> courseWares=courseWareDao.findAll();
        if(courseWares==null||courseWares.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        return courseWares;
    }

    @Override
    public Page<CourseWare> findAll(int size, int page) {
        Pageable pageable= PageRequest.of(page, size);
        Page<CourseWare> courseWares=courseWareDao.findAll(pageable);
        if(courseWares.getContent()==null||courseWares.getContent().size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        return courseWares;
    }

    @Override
    public CourseWare findById(long id) {
        CourseWare courseWare=courseWareDao.findByCourId(id);
        if(courseWare==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);

        }
        return courseWare;
    }

    @Override
    public void add(CourseWareVo courseWare, MultipartFile file) {
        if(file==null) {
            //文件不能为空
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FILE_IS_NULL);
        }
        String fileName = fileUploadUtil.getNewFileName(file);
        fileUploadUtil.uploadOneFile(file, "ppt/"+fileName);
        fileName="/static/ppt/"+fileName;

        CourseWare newOne=new CourseWare();
        newOne.setDestination(courseWare.getDestination());
        newOne.setTitle(courseWare.getTitle());
        newOne.setTime(new Date());
        newOne.setWare_url(fileName);

        courseWareDao.saveAndFlush(newOne);

    }

    @Override
    public void update(CourseWareVo courseWare, MultipartFile file) {
        CourseWare tem=courseWareDao.findByCourId(courseWare.getId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getWare_url());

            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "ppt/"+fileName);
            fileName="/static/ppt/"+fileName;
            tem.setWare_url(fileName);
        }

        tem.setDestination(courseWare.getDestination());
        tem.setTitle(courseWare.getTitle());
        tem.setTime(new Date());
        courseWareDao.saveAndFlush(tem);

    }


    @Override
    public void delete(Long id) {
        CourseWare courseWare=courseWareDao.findByCourId(id);
        if(courseWare==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        fileUploadUtil.deletePhoto(courseWare.getWare_url());
        courseWareDao.deleteById(id);
    }
}
