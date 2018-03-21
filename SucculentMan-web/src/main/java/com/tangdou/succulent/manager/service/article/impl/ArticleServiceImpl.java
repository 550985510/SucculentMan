package com.tangdou.succulent.manager.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.Article;
import com.tangdou.succulent.manager.api.article.ArticleContent;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
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
    public static final String KEYWORD_SEPARATOR = ",";

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
            List<String> keywordList = new ArrayList<String>();
            String[] keywords = StringUtils.split(item.getKeyword(), KEYWORD_SEPARATOR);
            if (keywords != null) {
                for (String keyword : keywords) {
                    keywordList.add(keyword);
                }
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
        String keyword = "";
        for (String item : article.getKeywordList()) {
            keyword += item + ",";
        }
        article.setKeyword(keyword);
        articleMapper.insert(article);
        Integer articleId = article.getId();
        ArticleContent content = new ArticleContent();
        content.setArticleId(articleId);
        content.setContent(article.getContent());
        articleContentMapper.insert(content);
    }
}
