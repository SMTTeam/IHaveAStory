package com.smtteam.smt.util;

import com.smtteam.smt.common.enums.ProjectRole;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnumUtilTest {

    @Test
    public void getListByEnum() {
        try {
            List<Object> a = EnumUtil.getListByEnum("role");
            assertTrue(a.isEmpty());
            List<Object> b = EnumUtil.getListByEnum("role", ProjectRole.Brower, ProjectRole.Map_Editor);

            assertEquals(1, ((Integer) b.get(0)).intValue());
            assertEquals(3, ((Integer) b.get(1)).intValue());
        } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFieldSetMethodName() {
    }

    @Test
    public void getEnumByField() {
    }
}