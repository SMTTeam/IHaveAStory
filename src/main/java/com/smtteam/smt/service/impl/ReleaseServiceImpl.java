package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.ReleaseDao;
import com.smtteam.smt.model.Release;
import com.smtteam.smt.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    private ReleaseDao releaseDao;

    @Override
    public List<Release> findIterList(int proId){
        return releaseDao.findByProIdOrderByPosId(proId);
    }

    @Override
    @Transactional
    public Release createRelease(Release release) {
        releaseDao.updateCreatePosID(release.getPosId()-1,release.getProId());
        return releaseDao.save(release);
    }

    @Override
    public Release getReleaseById(int id){
        Optional<Release> release = releaseDao.findById(id);
        return release.orElse(null);
    }

    @Override
    public Release modifyRelease(Release release){
        return releaseDao.save(release);
    }

    @Override
    public void deleteRelease(int id){
        Optional<Release> optionalRelease = releaseDao.findById(id);
        Release release = optionalRelease.orElse(null);
        if( release != null){
            releaseDao.delete(release);
        }
    }
}
