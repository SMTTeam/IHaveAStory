package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.ReleaseDao;
import com.smtteam.smt.model.Release;
import com.smtteam.smt.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    private ReleaseDao releaseDao;

    @Override
    public List<Release> findIterList(int proId){
        return releaseDao.findByProIdOrderByPosId(proId);
    }

    @Override
    public int findMaxID(){
        Integer num = releaseDao.findMaxID();
        if(num==null){
            return 0;
        }else {
            return num;
        }
    }

    @Override
    @Transactional
    public Release createRelease(Release release) {
        releaseDao.updateCreatePosID(release.getPosId()-1,release.getProId());
        return releaseDao.save(release);
    }
}
