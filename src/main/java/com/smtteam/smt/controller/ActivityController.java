package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.model.Activity;
import com.smtteam.smt.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Kimone
 * date 2018/12/31
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 新增一个activity
     * @param proId
     * @param name
     * @param posId 前一个activity的pos_id, 如果当前没有activity，传入0
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Activity> createActivity(@RequestParam int proId, @RequestParam String name, @RequestParam int posId) {
        Activity activity = new Activity(proId, name, posId+1);
        Activity result = activityService.createActivity(activity);
        return new ResultVO<>(result);
    }

    /**
     * 修改activity名字
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/modify")
    public ResultVO<Activity> modifyActivity(@RequestParam int id, @RequestParam String name) {
        Activity activity = activityService.getActivityById(id);
        activity.setName(name);
        Activity result = activityService.modifyActivity(activity);
        return new ResultVO<>(result);
    }

    /**
     * 删除activity
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVO<Activity> deleteActivity(@RequestParam int id) {
        activityService.deleteActivity(id);
        return new ResultVO<>();
    }

    /**
     * 获取所有activity
     * @return
     */
    @GetMapping("/list/{proId}")
    public ResultVO<List<Activity>> getAllActivity(@PathVariable int proId){
        List<Activity> activities =  activityService.getActivityList(proId);
        return new ResultVO<>(activities);
    }

}
