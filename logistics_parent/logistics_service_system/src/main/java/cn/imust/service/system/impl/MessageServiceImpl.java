package cn.imust.service.system.impl;

import cn.imust.dao.feedback.MessageDao;
import cn.imust.domain.feedback.Message;
import cn.imust.domain.feedback.MessageExample;
import cn.imust.service.system.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return messageDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Message record) {
        return messageDao.insertSelective(record);
    }

    @Override
    public List<Message> selectByExampleWithBLOBs(MessageExample example) {
        return messageDao.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Message> selectByExample(MessageExample example) {
        return messageDao.selectByExample(example);
    }

    @Override
    public Message selectByPrimaryKey(Integer id) {
        return messageDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Message record) {
        return messageDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Message record) {
        return messageDao.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public PageInfo findAll(MessageExample messageExample, int page, int size) {
        PageHelper.startPage(page,size);
        List<Message> lis = messageDao.selectByExampleWithBLOBs(messageExample);
        PageInfo pageInfo=new PageInfo(lis);
        return pageInfo;
    }
}
