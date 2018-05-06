package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.GoodsCommentServiceApi;
import com.tangdou.succulent.manager.api.goods.model.GoodsComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.mapper.GoodsCommentMapper;
import com.tangdou.succulent.manager.service.goods.GoodsCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品评论接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:36
 */
@Service
public class GoodsCommentServiceApiImpl implements GoodsCommentServiceApi {

    @Resource
    private GoodsCommentMapper goodsCommentMapper;

    @Resource
    private GoodsCommentService goodsCommentService;

    /**
     * 新增评论
     *
     * @param goodsComment 评论信息
     * @return 操作状态
     */
    @Override
    public ResponseResult add(GoodsComment goodsComment) {
        goodsCommentMapper.insert(goodsComment);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 分页查询评论信息列表
     *
     * @param goodsComment 查询条件
     * @return 评论内容列表信息
     */
    @Override
    public ResponseResult<PageInfo<GoodsComment>> findPage(GoodsComment goodsComment) {
        PageInfo<GoodsComment> pageInfo = goodsCommentService.findByList(goodsComment);
        return new ResponseResult<>(pageInfo);
    }
}
