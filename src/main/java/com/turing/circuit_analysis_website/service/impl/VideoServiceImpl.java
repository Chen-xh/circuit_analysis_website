package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.CourseWareDao;
import com.turing.circuit_analysis_website.dao.VideoDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.CourseWare;
import com.turing.circuit_analysis_website.pojo.Video;
import com.turing.circuit_analysis_website.service.VideoService;
import com.turing.circuit_analysis_website.util.FileUploadUtil;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.VideoVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoDao videoDao;
    @Autowired
    FileUploadUtil fileUploadUtil;

    @Override
    public List<Video> findAll() {
        List<Video> videos = videoDao.findAll();
        if (videos == null || videos.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_VIDEO);
        }
        return videos;
    }

    @Override
    public Video findById(Long id) {
       Video video = videoDao.findByVidId(id);
        if (video == null ) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_VIDEO);
        }
        return video;
    }


    @Override
    public void add(VideoVo video, MultipartFile file) {
        if(file==null) {
            //文件不能为空
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FILE_IS_NULL);
        }
        String fileName = fileUploadUtil.getNewFileName(file);
        fileUploadUtil.uploadOneFile(file, "video/"+fileName);
        fileName="/static/video/"+fileName;

        Video newOne=new Video();
        newOne.setDestination(video.getDestination());
        newOne.setTitle(video.getTitle());
        newOne.setTime(new Date());
        newOne.setVideo_url(fileName);

        videoDao.saveAndFlush(newOne);

    }

    @Override
    public void update(VideoVo video, MultipartFile file) {
        Video tem=videoDao.findByVidId(video.getId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_COURSE_WARE);
        }
        if(file!=null) {
            //文件不为空
            fileUploadUtil.deletePhoto(tem.getVideo_url());

            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "video/"+fileName);
            fileName="/static/video/"+fileName;
            tem.setVideo_url(fileName);
        }

        tem.setDestination(video.getDestination());
        tem.setTitle(video.getTitle());
        tem.setTime(new Date());
        videoDao.saveAndFlush(tem);

    }

    @Override
    public void delete(Long id) {
        Video video=videoDao.findByVidId(id);
        if(video==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_VIDEO);
        }
        fileUploadUtil.deletePhoto(video.getVideo_url());
        videoDao.deleteById(id);
    }
}
