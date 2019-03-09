package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.common.exception.NotExistException;
import com.smtteam.smt.dao.UserDao;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import com.smtteam.smt.util.EncryptUtil;
import com.smtteam.smt.util.MailUtil;
import com.smtteam.smt.util.StringUtil;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 类说明：用户具体实现
 * 创建者：Zeros
 * 创建时间：2019-01-01 21:27
 * 包名：com.smtteam.smt.service.impl
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MailUtil mailUtil;

    @Value("${deploy.url}")
    private String server;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    @Override
    public List<User> findByEmailLike(String email) {
        return userDao.findByEmailLike('%'+email+'%');
    }

    /**
     * 根据ID查找用户
     * @param userId
     * @return
     */
    @Override
    public User findById(Integer userId) {
        Optional<User> user = userDao.findById(userId);
        return user.orElse(null);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User addUser(User user){
        return userDao.save(user);
    }

    /**
     * 根据email查找用户（一个email对应1个用户）
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }

    /**
     * 根据email和psw来查找用户
     * @param email
     * @param psw
     * @return
     */
    //验证身份登录时要把 邮箱验证的 status（验证完成）考虑进去，仅当status为verified状态时才查找成功
    @Override
    public User findByEmailAndPsw(String email , String psw){
        if(email.equals("")){
            return null;
        }else {
            return userDao.findByEmailAndPsw(email,psw);
        }
    }

    @Override
    public User findByEmailAndStatus( String email ,Integer status ){
        return userDao.findByEmailAndStatus( email, status);
    }
    @Override
    public int updateUserInfoByEmail(String useremail , String username){
        return userDao.updateUserInfoByEmail(username,useremail);
    }

    /**
     * 查找验证过的邮箱用户
     * @param email
     * @return
     */
    @Override
    public User findByEmailAndStatus(String email) {
        return userDao.findByEmailAndStatus(email, Constants.USEREMAIL_VERIFIED);
    }

    @Override
    public int updateUserPswByEmail(String useremail , String newpsw){
        return userDao.updateUserPswByEmail(newpsw, useremail);
    }


    /**
     * 发送重置密码邮件
     * @param email
     * @return
     */
    @Override
    public User sendResetPswEmail(String email) throws NotExistException {
        User userFound = userDao.findByEmail(email);
        if( userFound != null && userFound.getStatus()== Constants.USEREMAIL_VERIFYING){
            logger.info("该邮箱尚未验证，请前往验证！");
            throw new NotExistException("该邮箱尚未验证，请前往验证！");
        }
        if( userFound ==null ){
            logger.info("该邮箱不存在！");
            throw new NotExistException("该邮箱不存在！");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String send_time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //将发送时间写到链接里面去包装起来

        logger.info("发送时间是："+send_time);

        String verify = StringUtil.getSalt();
        String url = email +"&"+ send_time + "&"+ EncryptUtil.SHA256(verify);
        url = server + "/userinfo/resetpsw/" + Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String content = "<html><head><title></title></head><body>亲爱的SMT用户，<br> &nbsp;&nbsp;&nbsp;您刚刚申请重置密码，请点击以下链接重置密码(有效期为8小时)：<br> &nbsp;&nbsp; <a href = \""+ url +"\">"+ url +"</a></body></html>";
        logger.info(userFound.getEmail());
        logger.info(content);
        String[] tos = new String[]{email};
        mailUtil.sendHtmlMail(tos , "重置密码" , content);
        return userFound;
    }

//    @Override
//    public User resetPsw(String code) throws NoAccessException{
//        byte[] bytes = Base64.getDecoder().decode(code);
//        String source = new String(bytes, StandardCharsets.UTF_8);
//        String[] array = source.split("&");
//        String email = array[0];
//        //校验
//        User user = userDao.findByEmail(email);
//        if( user == null){
//            throw new NoAccessException("验证邮箱不存在");
//        }else if(!array[1].equals(EncryptUtil.SHA256(user.getVerify()))){
//            throw new NoAccessException("重置信息被串改，重置失败");
//        }
//    }
}
