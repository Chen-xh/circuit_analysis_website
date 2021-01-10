package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.ChapterDao;
import com.turing.circuit_analysis_website.dao.TestInfoDao;
import com.turing.circuit_analysis_website.dao.TestSetDao;
import com.turing.circuit_analysis_website.dao.UserDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.TestInfo;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.service.TestInfoService;
import com.turing.circuit_analysis_website.util.TestCommitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class TestInfoServiceImpl implements TestInfoService {
    @Autowired
    TestInfoDao TestInfoDao;
    @Autowired
    TestSetDao testSetDao;
    @Autowired
    ChapterDao chapterDao;
    @Autowired
    UserDao userDao;

    @Override
    public Page<TestInfo> findAll(int size, int page) {
        Pageable pageable= PageRequest.of(page, size);
        Page<TestInfo> testInfos = TestInfoDao.findAll(pageable);
        testInfos.getContent();
        if (testInfos.getContent().size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST_INFO);
        }

        return testInfos;
    }

    @Override
    public TestInfo findById(Long id) {
        TestInfo tem = TestInfoDao.findByInfId(id);
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST_INFO);
        }

        return tem;
    }

    @Override
    public List<TestInfo> findByChapterOrUser(Long cid, Long uid) {
        List<TestInfo> testInfos ;
        if (cid != null && uid != null) {
            testInfos=findByChapterAndUser(cid,uid);
            return testInfos;
        }else if(cid != null){
            testInfos=findByChapter(cid);
            return testInfos;
        }else if(uid != null){
            testInfos=findByUser(uid);
            return testInfos;
        }
        return null;
    }

    @Override
    public void add(TestCommitResponse testCommitResponse,String studentNo,Long cid) {
        TestInfo testInfo=new TestInfo();
        testInfo.setDate(new Date());
        testInfo.setScore(testCommitResponse.getSum());
        testInfo.setNumber(testCommitResponse.getMark().length);
        User user = userDao.findUserByUsername(studentNo);
        if (user == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        testInfo.setUser(user);

        Chapter chapter=testSetDao.findByTesId(cid).getChapter();
        if(chapter==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_CHAPTER);
        }
        testInfo.setChapter(chapter);

        TestInfoDao.save(testInfo);
    }

    public List<TestInfo> findByChapter(Long cid) {
        Chapter chapter = new Chapter();
        chapter.setChaId(cid);
        List<TestInfo> TestInfos = TestInfoDao.findByChapter(chapter);
        if (TestInfos == null || TestInfos.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST_INFO);
        }
        return TestInfos;
    }
    public List<TestInfo> findByUser(Long uid) {
        User user = new User();
        user.setUserId(uid);
        List<TestInfo> TestInfos = TestInfoDao.findByUser(user);
        if (TestInfos == null || TestInfos.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST_INFO);
        }
        return TestInfos;
    }
    public List<TestInfo> findByChapterAndUser(Long cid,Long uid) {
        Chapter chapter = new Chapter();
        chapter.setChaId(cid);
        User user = new User();
        user.setUserId(uid);

        List<TestInfo> TestInfos = TestInfoDao.findByChapterAndUser(chapter,user);
        if (TestInfos == null || TestInfos.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST_INFO);
        }
        return TestInfos;
    }
    @Override
    public void delete(Long id) {
        TestInfo testInfo = TestInfoDao.findByInfId(id);
        if (testInfo == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_TEST_INFO);
        }
        TestInfoDao.deleteById(id);
    }
}
