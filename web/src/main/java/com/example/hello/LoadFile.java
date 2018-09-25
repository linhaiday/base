package com.example.hello;

import org.aspectj.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class LoadFile {

    /**
     * SpringMvc  批量上传
     */
    @PostMapping("/SpringMVC006/fileUpload")
    @ResponseBody
    public String  upload(@RequestParam("file") MultipartFile[] files) throws IOException {


        System.out.println("上传");

        String basePath="E:/";


        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            //获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);

            //目标文件
            File targetFile = new File(basePath,fileName);

            //转存文件
            file.transferTo(targetFile);

            System.out.println(targetFile.getAbsolutePath());
        }
        return "success";
    }

    //批量文件压缩后下载
    @GetMapping("/downLoad")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {

        //需要压缩的文件
        List<String> list = new ArrayList<String>();
        list.add("《IT项目管理》中文第5版.pdf");
        list.add("PMBOK第六版-中文版.pdf");
        list.add("PMP最新报考流程.ppt");

        //压缩后的文件
        String resourcesName = "test.zip";

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("E:/"+resourcesName));
        InputStream input = null;

        for (String str : list) {
            String name = "E:/"+str;
            input = new FileInputStream(new File(name));
            zipOut.putNextEntry(new ZipEntry(str));
            int temp = 0;
            while((temp = input.read()) != -1){
                zipOut.write(temp);
            }
            input.close();
        }
        zipOut.close();
        File file = new File("E:/"+resourcesName);
        HttpHeaders headers = new HttpHeaders();
        String filename = new String(resourcesName.getBytes("utf-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtil.readAsByteArray(file),headers,HttpStatus.CREATED);
    }
}
