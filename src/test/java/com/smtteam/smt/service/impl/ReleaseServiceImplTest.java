package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.model.Release;
import com.smtteam.smt.service.ReleaseService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReleaseServiceImplTest extends SmtApplicationTests {
    @Autowired
    private ReleaseService releaseService;

    private static Integer id;

    @Test
    public void test01_createRelease() {
        Release release = new Release(3,"test_create",1);
        Release result = releaseService.createRelease(release);
        id = result.getId();
        assertNotNull(result.getId());
    }

    @Test
    public void test02_getReleaseById() {
        Release result = releaseService.getReleaseById(id);
        assertEquals("test_create",result.getName());
    }

    @Test
    public void test03_modifyRelease() {
        Release release = releaseService.getReleaseById(id);
        release.setName("test_modify");
        Release result = releaseService.modifyRelease(release);
        assertEquals("test_modify",result.getName());
    }

    @Test
    public void test04_findIterList() {
        List<Release> releaseList = releaseService.findIterList(3);
        assertEquals(1,releaseList.size());
    }

    @Test
    public void test05_deleteRelease() {
        releaseService.deleteRelease(id);
        Release result = releaseService.getReleaseById(id);
        assertNull(result);
    }
}
