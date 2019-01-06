package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.StoryVO;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    /**
     * 新增story
     * @param storyInput
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Story> createStory(@RequestBody StoryVO storyInput){
        Story story = new Story(storyInput.getTaskId(), storyInput.getName(), storyInput.getStoryPoint(), storyInput.getPriority(),
                storyInput.getDescription(), storyInput.getPosId()+1, storyInput.getAcceptance(), storyInput.getIteration());
        Story result = storyService.createStory(story);
        return new ResultVO<>(result);
    }

    /**
     * 修改story内容
     * @param storyInput
     * @return
     */
    @PostMapping("/modify")
    public ResultVO<Story> modifyStory(@RequestParam int id, @RequestBody StoryVO storyInput){
        Story story = storyService.getStoryById(id);
        story.setTaskId(storyInput.getTaskId());
        story.setName(storyInput.getName());
        story.setStoryPoint(storyInput.getStoryPoint());
        story.setPriority(storyInput.getPriority());
        story.setDescription(storyInput.getDescription());
        story.setPosId(storyInput.getPosId());
        story.setAcceptance(storyInput.getAcceptance());
        story.setIteration(storyInput.getIteration());
        Story result = storyService.modifyStory(story);
        return new ResultVO<>(result);
    }

    /**
     * 删除story
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVO<Story> deleteStory(@RequestParam int id){
        storyService.deleteStory(id);
        return new ResultVO<>();
    }

    /**
     * 根据task获取对应的所有story
     * @param
     * @return
     */
    @GetMapping("/list/{taskId}")
    public ResultVO<List<Story>> getByTask(@PathVariable int taskId){
        List<Story> stories = storyService.getByTask(taskId);
        return new ResultVO<>(stories);
    }

    /**
     * 获取story数量
     * @param
     * @return
     */
    @GetMapping("/maxId")
    public ResultVO<Integer> getStoryNum(){
        int num = storyService.findMaxID();
        return new ResultVO<>(num);
    }
}
