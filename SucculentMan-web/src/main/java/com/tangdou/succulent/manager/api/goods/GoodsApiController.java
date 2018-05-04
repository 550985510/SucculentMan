package com.tangdou.succulent.manager.api.goods;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.Goods;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.goods.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:21
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsApiController {

    private StaffUser currentUser = new StaffUser();

    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询商品信息
     * @param goods 查询条件
     * @return 商品列表信息
     */
    @PostMapping("/list")
    public ResponseResult<PageInfo<Goods>> findList(@RequestBody Goods goods) {
        PageInfo<Goods> pageInfo = goodsService.findByList(goods);
        return new ResponseResult<>(pageInfo);
    }

    /**
     * 新增商品
     * @param goods 商品信息
     * @param session 当前用户信息
     * @return 操作状态
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Goods goods, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        goods.setModifiedBy(currentUser.getRealName());
        goods.setCreatedBy(currentUser.getRealName());
        goodsService.add(goods);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 商品发布状态
     * @param goods 发布状态
     * @param session 当前用户
     * @return 操作状态
     */
    @PostMapping("/status")
    public ResponseResult examine(@RequestBody Goods goods, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        goods.setModifiedBy(currentUser.getRealName());
        goodsService.status(goods);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 商品详情
     * @param id 商品编号
     * @return 商品详情信息
     */
    @GetMapping("/detail")
    public ResponseResult<Goods> detail(Integer id) {
        Goods goods = goodsService.detail(id, null);
        return new ResponseResult<>(goods);
    }

    /**
     * 编辑商品
     * @param goods 商品信息
     * @param session 当前用户
     * @return 操作状态
     */
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody Goods goods, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        goods.setModifiedBy(currentUser.getRealName());
        goodsService.edit(goods);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
