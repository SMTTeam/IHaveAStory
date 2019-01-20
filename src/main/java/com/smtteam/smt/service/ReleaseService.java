package com.smtteam.smt.service;

import com.smtteam.smt.model.Release;

import java.util.List;

public interface ReleaseService {

    List<Release> findIterList(int proId);

    int findMaxID();

    Release createRelease(Release release);
}
