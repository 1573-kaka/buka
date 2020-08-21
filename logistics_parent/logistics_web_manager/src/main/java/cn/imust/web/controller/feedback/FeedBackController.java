package cn.imust.web.controller.feedback;

import cn.imust.common.utils.MailUtil;
import cn.imust.domain.feedback.Message;
import cn.imust.domain.feedback.MessageExample;
import cn.imust.service.system.MessageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private HttpSession session;
    @RequestMapping("/write")
    public String write()
    {
        return "feedback/message";
    }
    @RequestMapping("/edit")
    public String edit(Message message) throws Exception {
        message.setReceivePer("ares1573@163.com");
        MailUtil.sendMsg("ares1573@163.com","您收到一条反馈",message.getContent()+"    "+"发件人："+message.getSendPer());
        message.setState(0);
        int i = messageService.insertSelective(message);
        session.setAttribute("tips","反馈成功");
        return "redirect:/feedback/write.do";
    }
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size, Model model)
    {
        MessageExample messageExample=new MessageExample();
        messageExample.setOrderByClause("state asc");
        PageInfo pageInfo = messageService.findAll(messageExample, page, size);
        model.addAttribute("page",pageInfo);
        return "feedback/message-list";
    }
    @RequestMapping("/toView")
    public String toView(String id,Model model)
    {
        Message message = messageService.selectByPrimaryKey(Integer.parseInt(id));
        message.setState(1);
        messageService.updateByPrimaryKeyWithBLOBs(message);
        model.addAttribute("feedback",message);
        return "feedback/message-view";
    }
    @RequestMapping("/delete")
    public String delete(String id)
    {
        messageService.deleteByPrimaryKey(Integer.valueOf(id));
        return "redirect:/feedback/list.do";
    }
    @RequestMapping("/shuaxin")
    public @ResponseBody List<Message>shuaxin(String id)
    {
        Message message = messageService.selectByPrimaryKey(Integer.parseInt(id));
        message.setState(1);
        messageService.updateByPrimaryKeyWithBLOBs(message);
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andStateEqualTo(0);
        List<Message> list = messageService.selectByExampleWithBLOBs(messageExample);
        return list;
    }
}
