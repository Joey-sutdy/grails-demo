package demo.limei

import com.convertlab.kafka.KafkaConsumerManager
import com.convertlab.redis.LettuceRedisClient
import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class AsyncCreateDemoRecordService extends KafkaConsumerManager {
    @Value('${kafkaServer.bootstrap.servers}')
    String bootstrapServers

    @Value('${kafkaServer.createDemo.topic}')
    String topic
    @Value('${kafkaServer.createDemo.group}')
    String groupId

    @Value('${kafkaServer.createDemo.numConsumers}')
    Integer numConsumers

    def demoService
    def kafkaProducerService
    LettuceRedisClient idempotentRedis

    void processKafkaMessage(String key, Map demoData) {
        log.info("received ${demoData as JSON}")
        demoService.save(demoData)
    }

    def sendMessage(Map demoData) {
        kafkaProducerService.send(topic, System.currentTimeMillis(), demoData)
    }

}
