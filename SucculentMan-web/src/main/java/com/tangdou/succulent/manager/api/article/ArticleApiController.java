package com.tangdou.succulent.manager.api.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.common.ResponseResult;
import com.tangdou.succulent.manager.bean.common.RestResultEnum;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.article.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 文章相关
 * @author 木叶丸
 * @date 2018/3/21
 */
@RestController
@RequestMapping("/api/article")
public class ArticleApiController {

    private StaffUser currentUser = new StaffUser();

    @Resource
    private ArticleService articleService;

    @PostMapping("/list")
    public ResponseResult<PageInfo<Article>> findList(@RequestBody Article article) {
        PageInfo<Article> pageInfo = articleService.findByList(article);
        return new ResponseResult<>(pageInfo);
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Article article, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        article.setModifiedBy(currentUser.getRealName());
        article.setCreatedBy(currentUser.getRealName());
        articleService.add(article);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    @PostMapping("/examine")
    public ResponseResult examine(@RequestBody Article article, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        article.setModifiedBy(currentUser.getRealName());
        articleService.examine(article);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    @GetMapping("/detail")
    public ResponseResult<Article> detail(Integer id) {
        Article article = articleService.detail(id);
        return new ResponseResult<>(article);
    }
}
