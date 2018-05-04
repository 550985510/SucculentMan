package com.tangdou.succulent.manager.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.GoodsComment;
import com.tangdou.succulent.manager.mapper.GoodsCommentMapper;
import com.tangdou.succulent.manager.mapper.GoodsMapper;
import com.tangdou.succulent.manager.mapper.UserMapper;
import com.tangdou.succulent.manager.service.goods.GoodsCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品评论相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:37
 */
@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {

    @Resource
    private GoodsCommentMapper goodsCommentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 分页查询商品评论列表信息
     *
     * @param goodsComment 查询条件
     * @return 商品评论列表信息
     */
    @Override
    public PageInfo<GoodsComment> findByList(GoodsComment goodsComment) {
        PageHelper.startPage(goodsComment.getPage(), goodsComment.getPageSize());
        List<GoodsComment> list = goodsCommentMapper.selectList(goodsComment);
        for (GoodsComment item : list) {
            item.setUserAvatar(userMapper.selectById(item.getUserId()).getAvatar());
            item.setUserNickName(userMapper.selectById(item.getUserId()).getNickName());
            item.setGoodsName(goodsMapper.selectById(item.getGoodsId(),null).getName());
        }
        return new PageInfo<>(list);
    }

    /**
     * 逻辑删除评论信息
     *
     * @param goodsComment 评论信息
     */
    @Override
    public void delete(GoodsComment goodsComment) {
        goodsCommentMapper.updateById(goodsComment);
    }
}
