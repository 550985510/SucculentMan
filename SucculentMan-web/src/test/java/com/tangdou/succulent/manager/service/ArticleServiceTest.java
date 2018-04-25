package com.tangdou.succulent.manager.service;

import com.tangdou.succulent.manager.api.article.Article;
import com.tangdou.succulent.manager.service.article.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 14:22
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ArticleService articleService;

    @Test
    public void findList() {
        Article article = new Article();
        article.setPage(1);
        article.setPageSize(5);
        List<Article> list = articleService.findByList(article).getList();
        for (Article item : list) {
            logger.info("{}", item);
        }
    }
}
