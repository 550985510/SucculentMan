package com.tangdou.succulent.manager.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.Article;
import com.tangdou.succulent.manager.api.article.ArticleContent;
import com.tangdou.succulent.manager.api.common.PageVo;
import com.tangdou.succulent.manager.mapper.ArticleContentMapper;
import com.tangdou.succulent.manager.mapper.ArticleMapper;
import com.tangdou.succulent.manager.mapper.ModuleMapper;
import com.tangdou.succulent.manager.mapper.StaffUserMapper;
import com.tangdou.succulent.manager.service.article.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 文章相关
 * @author 木叶丸
 * @date 2018/3/18
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    /**
     * 树路径分隔符
     */
    private static final String KEYWORD_SEPARATOR = ",";

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleContentMapper articleContentMapper;

    @Resource
    private StaffUserMapper staffUserMapper;

    @Resource
    private ModuleMapper moduleMapper;

    /**
     * 分页查询文章信息
     *
     * @param article 查询条件
     * @return 文章分页列表信息
     */
    @Override
    public PageInfo<Article> findByList(Article article) {
        PageHelper.startPage(article.getPage(), article.getPageSize());
        List<Article> list = articleMapper.selectByList(article);
        for (Article item : list) {
            List<String> keywordList = new ArrayList<>();
            String[] keywords = StringUtils.split(item.getKeyword(), KEYWORD_SEPARATOR);
            if (keywords != null) {
                Collections.addAll(keywordList, keywords);
            }
            item.setKeywordList(keywordList);
            item.setAuthor(staffUserMapper.selectById(item.getStaffId()).getNickName());
            item.setModuleName(moduleMapper.selectById(item.getModuleId()).getName());
        }
        return new PageInfo<>(list);
    }

    /**
     * 新增文章
     *
     * @param article 文章信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Article article) {
        StringBuilder keyword = new StringBuilder();
        for (String item : article.getKeywordList()) {
            keyword.append(item).append(",");
        }
        article.setKeyword(keyword.toString());
        articleMapper.insert(article);
        Integer articleId = article.getId();
        ArticleContent content = new ArticleContent();
        content.setArticleId(articleId);
        content.setContent(article.getContent());
        articleContentMapper.insert(content);
    }

    /**
     * 文章审核
     *
     * @param article 审核信息
     */
    @Override
    public void examine(Article article) {
        articleMapper.updateStatusById(article);
    }

    /**
     * 文章详情
     *
     * @param id 文章编号
     * @return 文章详情信息
     */
    @Override
    public Article detail(Integer id) {
        Article article = articleMapper.selectById(id);
        ArticleContent content = articleContentMapper.selectByArticleId(id);
        article.setContent(content.getContent());
        List<String> keywordList = new ArrayList<>();
        String[] keywords = StringUtils.split(article.getKeyword(), KEYWORD_SEPARATOR);
        if (keywords != null) {
            Collections.addAll(keywordList, keywords);
        }
        article.setKeywordList(keywordList);
        article.setAuthor(staffUserMapper.selectById(article.getStaffId()).getNickName());
        article.setModuleName(moduleMapper.selectById(article.getModuleId()).getName());
        return article;
    }

    /**
     * 编辑文章
     *
     * @param article 文章信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(Article article) {
        StringBuilder keyword = new StringBuilder();
        for (String item : article.getKeywordList()) {
            keyword.append(item).append(",");
        }
        article.setKeyword(keyword.toString());
        articleMapper.update(article);
        ArticleContent content = new ArticleContent();
        content.setArticleId(article.getId());
        content.setContent(article.getContent());
        articleContentMapper.updateByArticleId(content);
    }

    /**
     * 修改文章首页轮播图显示状态
     *
     * @param article 修改信息
     */
    @Override
    public void updateBannerStatus(Article article) {
        articleMapper.updateBannerStatus(article);
    }
}
