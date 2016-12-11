package com.zzl.ssm.web.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhangzl
 * 描述：文件操作视图
 * 日期：  2016/12/10.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 上传文件视图
     * @return
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String showFileUploadPage() {
        return "file/fileUpload";
    }

    /**
     * 多文件上传
     * @return
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.GET)
    public String uploads() {
        return "file/uploads";
    }

    /**
     * 单文件上传
     * @return
     */
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) {
        try {
            dealFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("---doUploadFile---", e.getMessage());
        }
        return "file/success";
    }

    /**
     * 多文件上传
     * @param files
     * @return
     */
    @RequestMapping(value = "/doUploads", method = RequestMethod.POST)
    public String doUploadFiles(@RequestParam("file") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                dealFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("---doUploadFile---", e.getMessage());
        }

        return "file/success";
    }

    /**
     * 保存文件
     * @param file
     * @throws IOException
     */
    private void dealFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("G:\\install\\Tomcat\\download\\", file.getOriginalFilename()));
        }
    }
}
