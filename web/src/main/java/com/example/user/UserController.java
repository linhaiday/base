package com.example.user;

import com.example.common.R;
import com.example.filter.NotDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${application.active}")
    private String active;

    @NotDuplicate
    @GetMapping("/list.json")
    public R list() {
        try {
            System.out.println("目前使用的环境是："+active);
            R.isOk().getData();
            return R.isOk().data(userService.list());
        } catch (Exception e) {
            return R.isFail(e);
        }

    }

}
