package com.howbuy.cc.basic.test.consumer.mq.listener;

import com.howbuy.cc.basic.mq.annotation.ActivemqListener;
import com.howbuy.cc.basic.mq.listener.common.QueueAbstractListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by xinwei.cheng on 2015/10/27.
 */
@Service
@ActivemqListener("queue.test")
public class QueueListener extends QueueAbstractListener {
    @Override
    public void onMessage(String id, Serializable message) {

    }
}
