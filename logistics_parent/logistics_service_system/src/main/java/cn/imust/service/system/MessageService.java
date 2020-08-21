package cn.imust.service.system;

import cn.imust.domain.feedback.Message;
import cn.imust.domain.feedback.MessageExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MessageService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBs(MessageExample example);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    PageInfo findAll(MessageExample messageExample, int page, int size);
}
