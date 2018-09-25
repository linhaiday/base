package com.example.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(){
        return "redirect:/user/list";
    }

    /**
     * 批量上传文件
     * @return
     */
    @GetMapping("/upload.do")
    @ResponseBody
    public ModelAndView upload(){
        System.out.println("上传");
        ModelAndView mav = new ModelAndView("/upload.html");
        return mav;
    }

    /**
     * 批量下载文件
     * @return
     */
    @GetMapping("/download.do")
    public String download(){
        System.out.println("下载");
        return "download";
    }

    @GetMapping("/groovy")
    @ResponseBody
    public int groovyTest(int x,int y){
        //return Hello.add(x,y);
        return 1;
    }
}
