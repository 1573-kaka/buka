package cn.imust.web.controller.cargo;

import cn.imust.domain.cargo.Factory;
import cn.imust.domain.cargo.FactoryExample;
import cn.imust.domain.cargo.Goods;
import cn.imust.domain.cargo.GoodsExample;
import cn.imust.service.cargo.FactoryService;
import cn.imust.service.cargo.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cargo/goods")
public class GoodController {
    @Reference
    private GoodsService goodService;
    @Reference
    private FactoryService factoryService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/list")
    public String  findAll(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
        GoodsExample goodExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodExample.createCriteria();
        PageInfo pageInfo = goodService.findAll(goodExample, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/goods/goods-list";
    }
    @RequestMapping("/delete")
    public String delete(String id){
        goodService.delete(id);
        return "redirect:/cargo/goods/list.do";
    }
    @RequestMapping("/edit")
    public  String  edit(Goods goods){

        if(StringUtils.isEmpty(goods.getId())){
            goodService.save(goods);
        }else {
            goodService.update(goods);
        }
        return "redirect:/cargo/goods/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Goods goods = goodService.findById(id);
        request.setAttribute("goods", goods);
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Factory> all = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",all);
        return "cargo/goods/goods-update";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",factoryList);
        return "cargo/goods/goods-add";
    }
    @RequestMapping("/toView")
    public String toView(String id){
        Goods goods = goodService.findById(id);
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Factory> all = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList", all);
        request.setAttribute("goods", goods);
        return "cargo/goods/goods-view";
    }
    @RequestMapping("/findByfid")
    public  @ResponseBody List<Goods> findByFid(String fid){
        GoodsExample goodExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodExample.createCriteria();
        criteria.andFactoryIdEqualTo(fid);
        List<Goods> goods = goodService.findByFid(goodExample);
        return goods;
    }
}
