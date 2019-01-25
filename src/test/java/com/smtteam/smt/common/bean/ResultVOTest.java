package com.smtteam.smt.common.bean;

import com.smtteam.smt.common.enums.ResultCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResultVOTest {

    @Test
    public void test() {
        ResultVO<Integer> r1 = new ResultVO<>();
        ResultVO<Integer> r2 = new ResultVO<>("123");
        ResultVO<Integer> r3 = new ResultVO<>(null);
        ResultVO<Integer> r4 = new ResultVO<>(ResultCode.NOT_FOUND, "123");
        ResultVO<Integer> r5 = new ResultVO<>(ResultCode.NOT_FOUND, "123", null);

        r1.setCode(111);
        r1.getCode();
        r1.setMessage("");
        r1.getMessage();
        r1.setData(null);
        r2.getData();
    }

}