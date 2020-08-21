package cn.imust.web.controller.cargo;

import cn.imust.domain.cargo.Contract;
import cn.imust.domain.cargo.ContractExample;
import cn.imust.domain.cargo.Packing;
import cn.imust.service.cargo.ContractService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {
    @Reference
    private ContractService contractService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        ContractExample example = new ContractExample();
        //合同企业进行签定的
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);

        if (loginUser.getDegree()==3){
            //如果你是主管，增加创建部门条件
            criteria.andCreateDeptEqualTo(loginUser.getDeptId());
        }else if (loginUser.getDegree()==4) {
            //如果你是普通职员，增加创建人条件
            criteria.andCreateByEqualTo(loginUser.getId());
        }else if (loginUser.getDegree()==2){
            //管理部门以及下属的人员
            criteria.andCreateDeptLike(loginUser.getDeptId()+"%");
        }

        PageInfo pageInfo = contractService.findAll(example, page, size);
        request.setAttribute("page", pageInfo);
        return "cargo/contract/contract-list";
    }

    @RequestMapping(value = "/toAdd")
    public String toAdd(){
        return "cargo/contract/contract-add";
    }

    @RequestMapping(value = "/edit",name = "新建或修改的保存")
    public String edit(Contract contract){
        contract.setCompanyId(companyId);
        contract.setCompanyName(companyName);
        if(StringUtils.isEmpty(contract.getId())){
            //新增的时候写入创建人和创建部门
            contract.setCreateBy(loginUser.getId());
            contract.setCreateDept(loginUser.getDeptId());

            contractService.save(contract);
//            contract.setCreateBy();
        } else {
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = " 修改合同信息")
    public String toUpdate(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract", contract);
        return "cargo/contract/contract-update";
    }

    @RequestMapping("/toView")
    public String toView(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract", contract);
        return "cargo/contract/contract-view";
    }

    @RequestMapping(value = "/delete", name = " 通过id删删除合同")
    public String delete(String id){
        contractService.delete(id);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping(value = "/submit", name = " 提交上报")
    public String submit(String id){
        Contract contract = contractService.findById(id);
        contract.setState(1);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping(value = "/cancel", name = " 取消上报")
    public String cancel(String id){
        Contract contract = contractService.findById(id);
        contract.setState(0);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }
}
