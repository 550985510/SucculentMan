package com.tangdou.succulent.manager.api.staff;

import com.tangdou.succulent.manager.bean.ResponseResult;
import com.tangdou.succulent.manager.bean.RestResultEnum;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.staff.StaffUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author cuihang
 * Created by 木叶丸 on 2018/2/13
 */
@RestController
@RequestMapping("/api/staff")
public class StaffApiController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StaffUserService staffUserService;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session) {
        StaffUser staffUser = new StaffUser();
        staffUser.setUserName(username);
        staffUser.setPassWord(password);
        StaffUser info = staffUserService.findForLogin(staffUser);
        if (info != null) {
            info.setPassWord(null);
            session.setAttribute(AdminSecurityConfig.SESSION_KEY,info);
            return new ResponseResult(RestResultEnum.SUCCESS);
        }
        return new ResponseResult(RestResultEnum.ERROR);
    }
}
