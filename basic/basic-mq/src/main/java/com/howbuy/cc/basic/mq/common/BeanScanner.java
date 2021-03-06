//package com.howbuy.cc.basic.mq.common;
//
//import com.howbuy.cc.basic.mq.annotation.ActivemqListener;
//import com.howbuy.cc.basic.mq.annotation.ActivemqSender;
//import com.howbuy.cc.basic.mq.constant.MQConstant;
//import com.howbuy.cc.basic.mq.listener.common.QueueAbstractListener;
//import com.howbuy.cc.basic.mq.listener.common.TopicAbstractListener;
//import com.howbuy.cc.basic.mq.listener.common.VirtualAbstractListener;
//import com.howbuy.cc.basic.mq.listener.common.container.QueueMessageListenerContainer;
//import com.howbuy.cc.basic.mq.listener.common.container.TopicMessageListenerContainer;
//import com.howbuy.cc.basic.mq.listener.common.container.VirtualMessageListenerContainer;
//import com.howbuy.cc.basic.mq.namespace.MqOperationSource;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.config.RuntimeBeanReference;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.beans.factory.support.RootBeanDefinition;
//import org.springframework.context.annotation.ScannedGenericBeanDefinition;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by xinwei.cheng on 2015/8/18.
// */
//public class BeanScanner implements BeanDefinitionRegistryPostProcessor {
//
//    private MqOperationSource mqOperationSource;
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        MqBeanDefinitionScanner classPathMqBeanDefinitionScanner = new MqBeanDefinitionScanner(registry);
//        Set<BeanDefinitionHolder> beanDefinitionHolderSet = classPathMqBeanDefinitionScanner.doScan(mqOperationSource.getBasePackage());
//        for(BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolderSet) {
//            ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition)beanDefinitionHolder.getBeanDefinition();
//            Class<?> clazz;
//            try {
//                clazz = Class.forName(scannedGenericBeanDefinition.getBeanClassName());
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            if(clazz.isAnnotationPresent(ActivemqSender.class)){
//                registSender(scannedGenericBeanDefinition, clazz);
//            }else if(scannedGenericBeanDefinition.getMetadata().hasAnnotation(ActivemqListener.class.getName())){
////                registListener(registry, beanDefinitionHolder.getBeanName(), clazz);
//            }
//        }
//    }
//
//    private static void registSender(ScannedGenericBeanDefinition beanDefinition , Class<?> clazz) {
//        beanDefinition.getPropertyValues().add("destinationName" , clazz.getAnnotation(ActivemqSender.class).value());
//        beanDefinition.getPropertyValues().add("mqOperationSource" , new RuntimeBeanReference(MQConstant.MQ_OPERATION_SOURCE_BEANNAME));
//        beanDefinition.getPropertyValues().add("jmsTemplate" , new RuntimeBeanReference(MQConstant.MQ_JMSTEMPLATE_BEANNAME));
//    }
//
//
////    private static void registListener(BeanDefinitionRegistry registry , String listenerBeanName , Class<?> clazz){
////        ActivemqListener activemqListenerAnno = clazz.getAnnotation(ActivemqListener.class);
////        if(activemqListenerAnno == null) {
////            return;
////        }
////        Class<?> registClazz;
////
////        Map<String , Object> params = new HashMap<>();
////
////        //依据不同的消息类型做不同的处理
////        if(QueueAbstractListener.class.isAssignableFrom(clazz)){
////            registClazz = QueueMessageListenerContainer.class;
////        }else if(TopicAbstractListener.class.isAssignableFrom(clazz)){
////            //TOPIC 需要是否持久化
////            if(activemqListenerAnno.isSubscriptionDurable()){
////                params.put("subscriptionDurable", true);
////                params.put("clientId", activemqListenerAnno.clientId());
////            }
////            registClazz =  TopicMessageListenerContainer.class;
////        }else if(VirtualAbstractListener.class.isAssignableFrom(clazz)){
////            //VIRTUAL 需要判断是否指定了systemName
////            registClazz =  VirtualMessageListenerContainer.class;
////            if(activemqListenerAnno.systemName() == null || activemqListenerAnno.systemName().equals(MQConstant.DETAULT_NONE)){
////                throw new IllegalArgumentException("VirtualAbstractListener 必须设置 systemName");
////            }
////            params.put("sysName", activemqListenerAnno.systemName());
////        }else{
////            throw new RuntimeException("abstractListener 没有继承 QueueAbstractListener | TopicAbstractListener | VirtualAbstractListener ， 无法识别是哪类监听端" );
////        }
////
////        RootBeanDefinition registBean = new RootBeanDefinition();
////        //初始化参数
////        params.put("destinationName" , activemqListenerAnno.value());
////        params.put("concurrentConsumers" , activemqListenerAnno.threadCount());
////
////        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues(params);
////        mutablePropertyValues.add("connectionFactory" , new RuntimeBeanReference(MQConstant.MQ_CONNECTION_POOL_FACTORY_BEANNAME));
////        mutablePropertyValues.add("messageListener" , new RuntimeBeanReference(listenerBeanName));
////
////        registBean.setBeanClass(registClazz);
////        registBean.setPropertyValues(mutablePropertyValues);
////        //注册
////        String beanName = QueueMessageListenerContainer.class.getSimpleName() + "." +  clazz.getSimpleName();
////
////        registry.registerBeanDefinition(beanName , registBean);
////    }
//
//    public MqOperationSource getMqOperationSource() {
//        return mqOperationSource;
//    }
//
//    public void setMqOperationSource(MqOperationSource mqOperationSource) {
//        this.mqOperationSource = mqOperationSource;
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//
//    }
//}
