package com.dnfm.common.start;

import com.dnfm.mina.codec.MessageCodecFactory;
import com.dnfm.mina.codec.SerializerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SerializerHelperInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SerializerHelperInitializer.class);

    @Autowired
    private MessageCodecFactory messageCodecFactory;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== SerializerHelperInitializer.run() 开始执行 =====");
        logger.info("开始初始化SerializerHelper的MessageCodecFactory...");
        SerializerHelper helper = SerializerHelper.getInstance();
        helper.setCodecFactory(messageCodecFactory);
        logger.info("SerializerHelper的MessageCodecFactory初始化完成");
        System.out.println("===== SerializerHelperInitializer.run() 执行完成 =====");
    }
}
