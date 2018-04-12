package com.tangdou.succulent.manager.api.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
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

    /**
     * 分页查询文章信息
     * @param article 查询条件
     * @return 文章列表信息
     */
    @PostMapping("/list")
    public ResponseResult<PageInfo<Article>> findList(@RequestBody Article article) {
        PageInfo<Article> pageInfo = articleService.findByList(article);
        return new ResponseResult<>(pageInfo);
    }

    /**
     * 新增文章
     * @param article 文章信息
     * @param session 当前用户信息
     * @return 操作状态
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Article article, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        article.setModifiedBy(currentUser.getRealName());
        article.setCreatedBy(currentUser.getRealName());
        articleService.add(article);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 文章发布状态
     * @param article 发布状态
     * @param session 当前用户
     * @return 操作状态
     */
    @PostMapping("/examine")
    public ResponseResult examine(@RequestBody Article article, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        article.setModifiedBy(currentUser.getRealName());
        articleService.examine(article);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 文章详情
     * @param id 文章编号
     * @return 文章详情信息
     */
    @GetMapping("/detail")
    public ResponseResult<Article> detail(Integer id) {
        Article article = articleService.detail(id);
        return new ResponseResult<>(article);
    }

    /**
     * 编辑文章
     * @param article 文章信息
     * @param session 当前用户
     * @return 操作状态
     */
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody Article article, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        article.setModifiedBy(currentUser.getRealName());
        articleService.edit(article);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 通过作者查询文章列表
     * @param article 作者信息
     * @return 文章列表信息
     */
    @PostMapping("/findByAuthor")
    public ResponseResult<PageInfo<Article>> findByAuthor(@RequestBody Article article) {
        PageInfo<Article> pageInfo = articleService.findByList(article);
        return new ResponseResult<>(pageInfo);
    }
}
