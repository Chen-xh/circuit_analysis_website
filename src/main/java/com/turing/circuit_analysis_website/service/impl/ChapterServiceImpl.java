package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.ChapterDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.service.ChapterService;
import com.turing.circuit_analysis_website.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:29
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterDao chapterDao;

    @Override
    public JsonResult findAll() {
        List<Chapter> chapters=chapterDao.findAll();
        if(chapters==null||chapters.size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_CHAPTER);
        }
        return JsonResult.success().addObject("chapter",chapters);
    }
}
