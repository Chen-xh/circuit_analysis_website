package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.ChapterDao;
import com.turing.circuit_analysis_website.dao.TestInfoDao;
import com.turing.circuit_analysis_website.dao.TestSetDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.TestSet;
import com.turing.circuit_analysis_website.service.TestSetService;
import com.turing.circuit_analysis_website.util.TestCommitResponse;
import com.turing.circuit_analysis_website.vo.TestSetDto;
import com.turing.circuit_analysis_website.vo.TestSetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:29
 */
@Service
@Transactional
public class TestSetServiceImpl implements TestSetService {
    @Autowired
    TestSetDao testSetDao;

    @Autowired
    ChapterDao chapterDao;


    @Override
    public List<TestSetDto> findAll() {
        List<TestSet> testSets = testSetDao.findAll();
        if (testSets == null || testSets.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }
        List<TestSetDto> list = new ArrayList<>();
        for (TestSet tem : testSets) {
            TestSetDto testSetDto = new TestSetDto();
            testSetDto.setTesId(tem.getTesId());
            testSetDto.setQuestion(tem.getQuestion());
            testSetDto.setType(tem.getType());
            testSetDto.setAnswer(tem.getAnswer().trim().split("&"));
            if (tem.getImg_urls() != null && tem.getImg_urls().length() > 0)
                testSetDto.setImg_urls(tem.getImg_urls().trim().split("&"));
            if (tem.getType() != 2) testSetDto.setOptions(tem.getOptions().trim().split("&"));
            list.add(testSetDto);
        }

        return list;
    }

    @Override
    public TestSetDto findById(Long id) {
        TestSet tem = testSetDao.findByTesId(id);
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }
        TestSetDto testSetDto = new TestSetDto();
        testSetDto.setTesId(tem.getTesId());
        testSetDto.setQuestion(tem.getQuestion());
        testSetDto.setType(tem.getType());
        testSetDto.setAnswer(tem.getAnswer().trim().split("&"));
        if (tem.getImg_urls() != null && tem.getImg_urls().length() > 0)
            testSetDto.setImg_urls(tem.getImg_urls().trim().split("&"));
        if (tem.getType() != 2) testSetDto.setOptions(tem.getOptions().trim().split("&"));
        return testSetDto;
    }

    @Override
    public List<TestSetDto> findByChapter(Long id,int size) {
//        Chapter chapter = new Chapter();
//        chapter.setChaId(id);
        List<TestSet> testSets = testSetDao.selectByChapter(id,size);
        if (testSets == null || testSets.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }
        List<TestSetDto> list = new ArrayList<>();
        for (TestSet tem : testSets) {
            TestSetDto testSetDto = new TestSetDto();
            testSetDto.setTesId(tem.getTesId());
            testSetDto.setQuestion(tem.getQuestion());
            testSetDto.setType(tem.getType());
            testSetDto.setAnswer(tem.getAnswer().trim().split("&"));
            if (tem.getImg_urls() != null && tem.getImg_urls().length() > 0)
                testSetDto.setImg_urls(tem.getImg_urls().trim().split("&"));
            if (tem.getType() != 2) testSetDto.setOptions(tem.getOptions().trim().split("&"));
            list.add(testSetDto);
        }

        return list;
    }

    @Override
    public List<TestSetDto> findByType(int type) {
        List<TestSet> testSets = testSetDao.findByType(type);
        if (testSets == null || testSets.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }
        List<TestSetDto> list = new ArrayList<>();
        for (TestSet tem : testSets) {
            TestSetDto testSetDto = new TestSetDto();
            testSetDto.setTesId(tem.getTesId());
            testSetDto.setQuestion(tem.getQuestion());
            testSetDto.setType(tem.getType());
            testSetDto.setAnswer(tem.getAnswer().trim().split("&"));
            if (tem.getImg_urls() != null && tem.getImg_urls().length() > 0)
                testSetDto.setImg_urls(tem.getImg_urls().trim().split("&"));
            if (tem.getType() != 2) testSetDto.setOptions(tem.getOptions().trim().split("&"));
            list.add(testSetDto);
        }

        return list;
    }

    @Override
    public TestCommitResponse commitTest(String[] answers ,int score) {
        int sum = 0;
        String[] myAnswer = new String[answers.length];

        String[] answer = new String[answers.length];
        int[] mark = new int[answers.length];

        for (int i = 0; i < answers.length; i++) {

            String[] tem = answers[i].split("#");
            myAnswer[i]=tem[1];
            TestSet testSet = testSetDao.findByTesId(Long.valueOf(tem[0]));
            if(testSet==null)throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
            answer[i] = testSet.getAnswer();
            if (tem[1].equals(testSet.getAnswer())) {
                mark[i] = score;
                sum+=score;
            } else {
                mark[i] = 0;
            }
        }
        TestCommitResponse response = new TestCommitResponse();
        response.setMyAnswer(myAnswer);
        response.setAnswer(answer);
        response.setSum(sum);
        response.setMark(mark);
        return response;
    }



    @Override
    public List<TestSetDto> getFinalExam(int type,int size) {

        List<TestSet> test1 = testSetDao.select(type,size);

        if (test1 == null || test1.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }

        List<TestSetDto> list = new ArrayList<>();
        for (TestSet tem : test1) {
            TestSetDto testSetDto = new TestSetDto();
            testSetDto.setTesId(tem.getTesId());
            testSetDto.setQuestion(tem.getQuestion());
            testSetDto.setType(tem.getType());
            testSetDto.setAnswer(tem.getAnswer().trim().split("&"));
            if (tem.getImg_urls() != null && tem.getImg_urls().length() > 0)
                testSetDto.setImg_urls(tem.getImg_urls().trim().split("&"));
            System.out.println(tem.getTesId());
            if (tem.getType() != 2) testSetDto.setOptions(tem.getOptions().trim().split("&"));
            list.add(testSetDto);
        }
        return list;
    }

    @Override
    public void add(TestSetVo testSet) {
        Chapter chapter = chapterDao.findByChaId(testSet.getCId());
        if (chapter == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_CHAPTER);
        }
        if (testSet.getType() == 1 && testSet.getAnswer().contains("&")) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.ONLY_HAVE_ANSWER);
        }
        TestSet newOne = new TestSet();
        newOne.setType(testSet.getType());
        newOne.setQuestion(testSet.getQuestion());
        newOne.setAnswer(testSet.getAnswer());
        newOne.setChapter(chapter);
        newOne.setDate(new Date());
        if (testSet.getType() != 2) {
            newOne.setOptions(testSet.getOptions());
        }
        if (testSet.getImg_urls() != null && testSet.getImg_urls().length() > 0)
            newOne.setImg_urls(testSet.getImg_urls());
        testSetDao.saveAndFlush(newOne);
    }

    @Override
    public void update(TestSetVo testSet) {
        TestSet tem = testSetDao.findByTesId(testSet.getTesId());
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }
        Chapter chapter = chapterDao.findByChaId(testSet.getCId());
        if (chapter == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_CHAPTER);
        }
        if (testSet.getType() == 1 && testSet.getAnswer().contains("&")) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.ONLY_HAVE_ANSWER);
        }
        tem.setType(testSet.getType());
        tem.setQuestion(testSet.getQuestion());
        tem.setAnswer(testSet.getAnswer());
        tem.setChapter(chapter);
        tem.setDate(new Date());
        if (testSet.getType() != 2) {
            tem.setOptions(testSet.getOptions());
        }
        if (testSet.getImg_urls() != null && testSet.getImg_urls().length() > 0) tem.setImg_urls(testSet.getImg_urls());
        testSetDao.saveAndFlush(tem);
    }

    @Override
    public void delete(Long id) {
        TestSet testSet = testSetDao.findByTesId(id);
        if (testSet == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST);
        }
        testSetDao.deleteById(id);
    }

}

