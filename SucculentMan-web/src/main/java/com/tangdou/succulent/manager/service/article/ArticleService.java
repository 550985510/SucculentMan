package com.tangdou.succulent.manager.service.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.Article;
import com.tangdou.succulent.manager.api.common.PageVo; /**
 * 文章相关
 * @author 木叶丸
 * @date 2018/3/18
 */
public interface ArticleService {

    /**
     * 分页查询文章信息
     * @param article 查询条件
     * @return 文章分页列表信息
     */
    PageInfo<Article> findByList(Article article);

    /**
     * 新增文章
     * @param article 文章信息
     */
    void add(Article article);

    /**
     * 文章审核
     * @param article 审核信息
     */
    void examine(Article article);

    /**
     * 文章详情
     * @param id 文章编号
     * @return 文章详情信息
     */
    Article detail(Integer id);

    /**
     * 编辑文章
     * @param article 文章信息
     */
    void edit(Article article);

    /**
     * 修改文章首页轮播图显示状态
     * @param article 修改信息
     */
    void updateBannerStatus(Article article);
}
