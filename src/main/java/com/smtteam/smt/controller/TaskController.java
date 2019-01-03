package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.model.Activity;
import com.smtteam.smt.model.Task;
import com.smtteam.smt.service.ActivityService;
import com.smtteam.smt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Kimone
 * date 2018/12/31
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ActivityService activityService;

    /**
     * 新增task
     * @param activityId
     * @param name
     * @param posId 前一个task的pos_id, 如果当前没有task，传入0
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Task> createTask(@RequestParam int activityId, @RequestParam String name, @RequestParam int posId) {
        Task task = new Task(activityId, name, posId+1);
        Task result = taskService.createTask(task);
        return new ResultVO<>(result);
    }

    /**
     * 修改task名字
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/modify")
    public ResultVO<Task> modifyTask(@RequestParam int id, @RequestParam String name) {
        Task task = taskService.getTaskById(id);
        task.setName(name);
        Task result = taskService.modifyTask(task);
        return new ResultVO<>(result);
    }

    /**
     * 删除task
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVO<Task> deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);
        return new ResultVO<>();
    }

    /**
     * 根据activity获取对应的所有task
     * @param activityId
     * @return
     */
    @GetMapping("/list/{activityId}")
    public ResultVO<List<Task>> getByActivity(@PathVariable int activityId) {
        List<Task> tasks = taskService.getByActivity(activityId);
        return new ResultVO<>(tasks);
    }


    @GetMapping("/maxId")
    public ResultVO<Integer> getActivityNum() {
        int num = taskService.findMaxID();
        return new ResultVO<>(num);
    }
}
