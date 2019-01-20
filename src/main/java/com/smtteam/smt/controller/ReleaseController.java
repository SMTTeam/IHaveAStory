package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.model.Release;
import com.smtteam.smt.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    @Autowired
    private ReleaseService releaseService;

    /**
     * 获取某个project的迭代
     * @param proId
     * @return
     */
    @GetMapping("/list/{proId}")
    public ResultVO<List<Release>> getIterList(@PathVariable int proId){
        List<Release> releases= releaseService.findIterList(proId);
        return new ResultVO<>(releases);
    }

    /**
     * 获取release最大id
     * @param
     * @return
     */
    @GetMapping("/maxId")
    public ResultVO<Integer> getReleaseMaxID(){
        int num = releaseService.findMaxID();
        return new ResultVO<>(num);
    }

    /**
     * 新增迭代
     * @param proId
     * @param name
     * @param posId 前一个release的pos_id, 如果当前没有，传入0
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Release> createRelease(@RequestParam int proId, @RequestParam String name, @RequestParam int posId) {
        Release release = new Release(proId,name,posId+1);
        Release result = releaseService.createRelease(release);
        return new ResultVO<>(result);
    }
}
