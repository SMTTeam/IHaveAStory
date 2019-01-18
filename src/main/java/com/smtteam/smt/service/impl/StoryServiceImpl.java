package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.IterationVO;
import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryDao storyDao;

    @Override
    @Transactional
    public Story createStory(Story story){
        storyDao.updateCreatePosID(story.getPosId()-1);
        return storyDao.save(story);
    }

    @Override
    public Story getStoryById(int id){
        return storyDao.findById(id).get();
    }

    @Override
    public Story modifyStory(Story story){
        return storyDao.save(story);
    }

    @Override
    public void deleteStory(int id){
        Story story = storyDao.findById(id).get();
        storyDao.delete(story);
    }

    @Override
    public List<Story> getByTask(int taskId){
        return storyDao.findByTaskIdOrderByPosId(taskId);
    }

    @Override
    public int findMaxID(){
        return storyDao.findMaxID();
    }

    @Override
    public List<IterationVO> findIterNum(int proId){
        List<Map<String,IterationVO>> list = storyDao.findIterByProId(proId);
        List<IterationVO> vo = new ArrayList<>();
//        System.out.println("serviceimpl begin");
        for (Map<String,IterationVO> i:list){
//            System.out.println(i);
            List<Object> tmp=new ArrayList<>();
            for (String key:i.keySet()){
                tmp.add(i.get(key));
            }
            IterationVO tmpvo=new IterationVO(Integer.parseInt(tmp.get(0).toString()),tmp.get(1).toString());
            if (vo.contains(tmpvo)) {
//                System.out.println("contains");
                continue;
            }
            vo.add(tmpvo);
        }
//        System.out.println(vo);
//        System.out.println("serviceimpl end");
        return vo;
    }
}
