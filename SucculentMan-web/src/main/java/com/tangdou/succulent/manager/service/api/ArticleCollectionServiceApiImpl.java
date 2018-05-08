package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.ArticleCollectionServiceApi;
import com.tangdou.succulent.manager.api.article.model.Article;
import com.tangdou.succulent.manager.api.article.model.ArticleCollection;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.mapper.ArticleCollectionMapper;
import com.tangdou.succulent.manager.service.article.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章收藏相关接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:30
 */
@Service
public class ArticleCollectionServiceApiImpl implements ArticleCollectionServiceApi {

    @Resource
    private ArticleCollectionMapper articleCollectionMapper;

    @Resource
    private ArticleService articleService;

    /**
     * 用户收藏
     *
     * @param userId    用户id
     * @param articleId 文章id
     * @return 响应状态
     */
    @Override
    public ResponseResult collection(Integer userId, Integer articleId) {
        ArticleCollection info = new ArticleCollection();
        info.setUserId(userId);
        info.setArticleId(articleId);
        ArticleCollection articleCollection = articleCollectionMapper.selectByBothId(info);
        if (articleCollection == null) {
            articleCollectionMapper.insert(info);
        } else {
            articleCollection.setStatus(0);
            articleCollectionMapper.updateStatusById(articleCollection);
        }
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 取消收藏
     *
     * @param userId    用户id
     * @param articleId 文章id
     * @return 响应状态
     */
    @Override
    public ResponseResult unCollection(Integer userId, Integer articleId) {
        ArticleCollection info = new ArticleCollection();
        info.setUserId(userId);
        info.setArticleId(articleId);
        ArticleCollection articleCollection = articleCollectionMapper.selectByBothId(info);
        articleCollection.setStatus(1);
        articleCollectionMapper.updateStatusById(articleCollection);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 判断是否收藏
     *
     * @param userId    用户id
     * @param articleId 文章id
     * @return 响应状态
     */
    @Override
    public ResponseResult<Boolean> isCollected(Integer userId, Integer articleId) {
        Boolean isCollected = false;
        ArticleCollection info = new ArticleCollection();
        info.setUserId(userId);
        info.setArticleId(articleId);
        if (articleCollectionMapper.countByBothId(info) != 0) {
            isCollected = true;
        }
        return new ResponseResult<>(isCollected);
    }

    /**
     * 查询文章收藏数量
     *
     * @param articleId 文章id
     * @return 用户粉丝数量
     */
    @Override
    public ResponseResult<Integer> findCollectedNum(Integer articleId) {
        return new ResponseResult<>(articleCollectionMapper.countByArticleId(articleId));
    }

    /**
     * 查询用户收藏列表
     *
     * @param articleCollection 查询条件
     * @return 收藏列表信息
     */
    @Override
    public ResponseResult<PageInfo<ArticleCollection>> findList(ArticleCollection articleCollection) {
        PageHelper.startPage(articleCollection.getPage(), articleCollection.getPageSize());
        List<ArticleCollection> list = articleCollectionMapper.selectByUserId(articleCollection.getUserId());
        for (ArticleCollection item : list) {
            item.setArticle(articleService.detail(item.getArticleId(),2));
        }
        return new ResponseResult<>(new PageInfo<>(list));
    }
}
