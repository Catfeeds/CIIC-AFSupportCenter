package com.ciicsh.gto.test.testKafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "139.224.14.105:9092");
        System.out.println("---------------------------------------------this is the group part test 1");
        //消费者的组id
        props.put("group.id", "GroupA");//这里是GroupA或者GroupB

        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        //从poll(拉)的回话处理时长
        props.put("session.timeout.ms", "30000");
        //poll的数量限制
        // props.put("max.poll.records", "100");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("shuaige"));
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(()->{
            while (true){
                System.out.println("--------------接收消息-----------------------------");
                ConsumerRecords records =consumer.poll(1000);
                System.out.println("size:"+records.isEmpty());
                records.forEach((p)->{
                    ConsumerRecord record = (ConsumerRecord)p;
                    System.out.println("---------------get topic-----------------------------");
                    Object object= toObject((byte[]) record.value());
                    System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), object+"\n");
                });
            }
        });

    }

    static Object  toObject(byte[] bytes){

        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        Object obj = null;
        try {
            bi =new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(bi!=null)bi.close();
                    if(oi!=null) oi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return obj;
    }
}
