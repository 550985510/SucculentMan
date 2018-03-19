package com.tangdou.succulent.manager.api.common;

import com.tangdou.succulent.manager.bean.common.ResponseResult;
import com.tangdou.succulent.manager.bean.common.RestResultEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/img")
    public ResponseResult uploadImg(@RequestParam("myFileName") MultipartFile file, HttpServletRequest request) throws ServletException, IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = UUID.randomUUID().toString() + "-" + (new SimpleDateFormat("yyyyMMddHHmm")).format(new Date()) + ".jpg";
        File dir = new File(path, fileName);
        String url = "http://localhost:8080/succulent/upload/";
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        ResponseResult result = new ResponseResult();
        result.setMsg(url + fileName);
        return result;
    }
}
