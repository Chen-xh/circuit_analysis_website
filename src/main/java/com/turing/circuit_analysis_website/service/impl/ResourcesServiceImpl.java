package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.ResourcesDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Resources;
import com.turing.circuit_analysis_website.service.ResourcesService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.vo.ResourcesVo;
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
 * @date 2020/10/15  19:16
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    ResourcesDao resourcesDao;
    @Autowired
    FileUploadUtil fileUploadUtil;

    @Override
    public List<Resources> findAll() {
        List<Resources> resources = resourcesDao.findAll();
        if (resources == null || resources.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOUND_RESOURCES);
        }
        return resources;
    }

    @Override
    public List<Resources> findAllByType(String type) {
        List<Resources> resources = resourcesDao.findAllByType(type);
        if (resources == null || resources.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOUND_RESOURCES);
        }
        return resources;
    }

    @Override
    public Page<Resources> findAllByType(String type,int size, int page) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Resources> resources = resourcesDao.findAllByType(type,pageable);
        if (resources.getContent() == null || resources.getContent().size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOUND_RESOURCES);
        }
        return resources;
    }

    @Override
    public Resources findById(Long id) {
       Resources Resources = resourcesDao.findByResId(id);
        if (Resources == null ) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOUND_RESOURCES);
        }
        return Resources;
    }


    @Override
    public void add(ResourcesVo Resources, MultipartFile file) {
        if(file==null) {
            //文件不能为空
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FILE_IS_NULL);
        }
        String fileName = fileUploadUtil.getNewFileName(file);
        fileUploadUtil.uploadOneFile(file, "resources/"+fileName);
        fileName="/static/resources/"+fileName;

        Resources newOne=new Resources();
        newOne.setDestination(Resources.getDestination());
        newOne.setTitle(Resources.getTitle());
        newOne.setTime(new Date());
        newOne.setRes_url(fileName);
        if(Resources.getType()!=null)newOne.setType(Resources.getType());
        resourcesDao.saveAndFlush(newOne);

    }

    @Override
    public void update(ResourcesVo Resources, MultipartFile file) {
        Resources tem=resourcesDao.findByResId(Resources.getId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getRes_url());

            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "resources/"+fileName);
            fileName="/static/resources/"+fileName;
            tem.setRes_url(fileName);
        }

        tem.setDestination(Resources.getDestination());
        tem.setTitle(Resources.getTitle());
        tem.setTime(new Date());
        if(Resources.getType()!=null)tem.setType(Resources.getType());
        resourcesDao.saveAndFlush(tem);

    }

    @Override
    public void delete(Long id) {
        Resources Resources=resourcesDao.findByResId(id);
        if(Resources==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOUND_RESOURCES);
        }
        fileUploadUtil.deletePhoto(Resources.getRes_url());
        resourcesDao.deleteById(id);
    }
}
