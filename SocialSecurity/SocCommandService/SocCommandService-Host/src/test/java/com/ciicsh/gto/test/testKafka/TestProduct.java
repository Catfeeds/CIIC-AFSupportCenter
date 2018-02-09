package com.ciicsh.gto.test.testKafka;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
 * Test kafka
 */
public class TestProduct {

    public static void main(String[] args) {
//        Properties props = new Properties();
//        props.put("metadata.broker.list", "139.224.14.105:9092");
//        /**
//         * 0表示不等待结果返回<br/>
//         * 1表示等待至少有一个服务器返回数据接收标识<br/>
//         * -1表示必须接收到所有的服务器返回标识，及同步写入<br/>
//         * */
//        props.put("request.required.acks", "0");
//        /**
//         * 内部发送数据是异步还是同步
//         * sync：同步, 默认
//         * async：异步
//         */
//        props.put("producer.type", "async");
//        /**
//         * 设置序列化的类
//         * 可选：kafka.serializer.StringEncoder
//         * 默认：kafka.serializer.DefaultEncoder
//         */
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        /**
//         * 设置分区类
//         * 根据key进行数据分区
//         * 默认是：kafka.producer.DefaultPartitioner ==> 安装key的hash进行分区
//         * 可选:kafka.serializer.ByteArrayPartitioner ==> 转换为字节数组后进行hash分区
//         */
//        props.put("partitioner.class", "JavaKafkaProducerPartitioner");
//
//        // 重试次数
//        props.put("message.send.max.retries", "3");
//
//        // 异步提交的时候(async)，并发提交的记录数
//        props.put("batch.num.messages", "200");
//
//        // 设置缓冲区大小，默认10KB
//        props.put("send.buffer.bytes", "102400");
//        // 2. 构建Kafka Producer Configuration上下文
//        props.put("send.buffer.bytes", "102400");
//
//        // 2. 构建Kafka Producer Configuration上下文
        Properties props = new Properties();
        props.put("bootstrap.servers", "139.224.14.105:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");


        SsComTask ssComTask = new SsComTask();
        ssComTask.setComTaskId(new Long(5000));
        ssComTask.setSubmitRemark("我就是要批退你 你能咋办？");
        byte[] result = toByteArray(ssComTask);
        Producer<String, String> producer = new KafkaProducer<>(props);
//        for(int i = 0; i < 10; i++) Integer.toString(i) Integer.toString(i))
        System.out.println("-----------------------------------------是否为空："+result);
        producer.send(new ProducerRecord("shuaige","任务单对象" ,result));
        System.out.println("------------------------------发送完毕");
        producer.close();
    }
    static byte[] toByteArray(Object object){
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        byte[] bytes = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(object);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bos!=null)
                    bos.close();
                if(os!=null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}