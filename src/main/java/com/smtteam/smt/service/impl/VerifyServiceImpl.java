package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.dao.UserDao;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.VerifyService;
import com.smtteam.smt.util.EncryptUtil;
import com.smtteam.smt.util.MailUtil;
import com.smtteam.smt.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 验证的具体实现
 * 创建者：Weishixin
 * 创建时间：2019-01-06
 * 包名：com.smtteam.smt.service.impl
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MailUtil mailUtil;

    @Value("${deploy.url}")
    private String server;

    /**
     * 发送验证邮件
     * @param email
     * @param psw
     * @param username
     * @return
     * @throws ExistException
     */
    @Override
    public User sendVerifyEmail(String email, String psw, String username)throws ExistException{
        //在发送前保存记录到数据库
//        System.out.println("11111111111");
        User user1 = userDao.findByEmail(email);
//        System.out.println("2222222222222");
        if(user1 != null && user1.getStatus()== Constants.USEREMAIL_VERIFYING) throw new ExistException("该邮箱已注册但未验证，请前往验证！");
        if(user1 != null && user1.getStatus()== Constants.USEREMAIL_VERIFIED) throw new ExistException("该邮箱已经注册验证了！");


        String verify = StringUtil.getSalt();
        User user = new User( email,psw, username, Constants.USER_DEFAULT_GENDA, verify, Constants.USEREMAIL_VERIFYING);
        user = userDao.save(user);

        String url = email + "&" + EncryptUtil.SHA256(verify);
        url = server + "/register/acceptverifyemail/" + Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String content = "<html><head><title></title></head><body>亲爱的SMT用户您好，<br> &nbsp;&nbsp;&nbsp;您刚刚注册成为SMT用户，请点击以下链接完成邮箱验证：<br> &nbsp;&nbsp; <a href = \""+ url +"\">"+ url +"</a></body></html>";

        System.out.println(content);
        String[] tos = new String[]{email};
        mailUtil.sendHtmlMail(tos, "邮箱验证", content);

        return user;
    }

    /**
     * 用户接受、完成验证
     * @param code
     * @return
     * @throws NoAccessException
     */
    @Override
    public User acceptVerifyEmail(String code)throws NoAccessException {
        byte[] bytes = Base64.getDecoder().decode(code);
        String source = new String(bytes, StandardCharsets.UTF_8);
        String[] array = source.split("&");
//        Integer userid = Integer.parseInt(array[0]);
        String email = array[0];
        //校验
        User user = userDao.findByEmail(email);
        if( user==null ){
            throw new NoAccessException("验证邮箱不存在");
        }else if(  !user.getStatus().equals(Constants.USEREMAIL_VERIFYING) ){
            throw new NoAccessException("邮箱已经验证");
        }else if(!array[1].equals(EncryptUtil.SHA256(user.getVerify()))){
            throw new NoAccessException("验证信息被串改，验证失败");
        }
        //更新状态
        user.setStatus(Constants.USEREMAIL_VERIFIED);
        return userDao.saveAndFlush(user);


    }
}
