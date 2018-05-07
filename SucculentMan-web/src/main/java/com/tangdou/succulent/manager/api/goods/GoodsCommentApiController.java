package com.tangdou.succulent.manager.api.goods;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.GoodsComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.service.goods.GoodsCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章评论相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:35
 */
@RestController
@RequestMapping("/api/goods/comment")
public class GoodsCommentApiController {

    @Resource
    private GoodsCommentService goodsCommentService;

    /**
     * 分页查询文章评论信息
     * @param goodsComment 查询条件
     * @return 文章评论列表信息
     */
    @PostMapping("/list")
    public ResponseResult<PageInfo<GoodsComment>> findList(@RequestBody GoodsComment goodsComment) {
        PageInfo<GoodsComment> pageInfo = goodsCommentService.findByList(goodsComment);
        return new ResponseResult<>(pageInfo);
    }

    /**
     * 逻辑删除评论信息
     * @param goodsComment 评论信息
     * @return 操作状态
     */
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody GoodsComment goodsComment) {
        goodsCommentService.delete(goodsComment);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
