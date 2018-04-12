package com.tangdou.succulent.manager.api.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 上传图片相关
 * @author 木叶丸
 * @date 2018/3/19
 */
@RestController
@RequestMapping("/api/upload")
public class UploadApiController {

    @Value("${img.location}")
    private String location;

    @Value("${app.host}")
    private String APP_PATH;

    @PostMapping("/img")
    public ResponseResult uploadImg(@RequestParam("myFileName") MultipartFile file) throws ServletException, IOException {
        String fileName = UUID.randomUUID().toString() + "-" + (new SimpleDateFormat("yyyyMMddHHmm")).format(new Date()) + ".jpg";
        File dir = new File(location);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File save = new File(dir,fileName);
        file.transferTo(save);
        ResponseResult result = new ResponseResult();
        result.setMsg(APP_PATH + "/" + fileName);
        return result;
    }
}
