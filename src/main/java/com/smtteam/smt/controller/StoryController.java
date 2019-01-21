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
        String priorityInput = storyInput.getPriority();
        int priority = 0;
        if (priorityInput.equals("中")) {
            priority = 1;
        }
        else if (priorityInput.equals("高")) {
            priority = 2;
        }
        Story story = new Story(storyInput.getTaskId(), storyInput.getName(), storyInput.getStoryPoint(), priority,
                storyInput.getDescription(), storyInput.getPosId()+1, storyInput.getAcceptance(), storyInput.getReleaseId());
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
        String priorityInput = storyInput.getPriority();
        int priority = 0;
        if (priorityInput.equals("中")) {
            priority = 1;
        }
        else if (priorityInput.equals("高")) {
            priority = 2;
        }
        story.setPriority(priority);
        story.setDescription(storyInput.getDescription());
        story.setPosId(storyInput.getPosId());
        story.setAcceptance(storyInput.getAcceptance());
        story.setReleaseId(storyInput.getReleaseId());
        Story result = storyService.modifyStory(story);
        return new ResultVO<>(result);
    }

    /**
     * 根据storyid获取内容
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO<Story> getStoryById(@PathVariable int id){
        Story result = storyService.getStoryById(id);
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

}
