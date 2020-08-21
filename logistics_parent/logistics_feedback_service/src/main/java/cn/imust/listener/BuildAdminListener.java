package cn.imust.listener;

import cn.imust.common.utils.MailUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class BuildAdminListener implements MessageListener {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public void onMessage(Message message) {
        try {
            String content=new String(message.getBody());
            MailUtil.sendMsg("ares1573@163.com","恭喜成为平头物流管理系统管理员",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
