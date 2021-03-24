package demo.limei

import com.convertlab.kafka.gen.KafkaTopicGenerator
import com.convertlab.kafka.gen.KafkaTopicGeneratorBuilder
import grails.gorm.transactions.Transactional
import grails.util.Environment
import org.springframework.beans.factory.annotation.Value

@Transactional
class TopicDrclarerService {

    @Value('${kafkaServer.bootstrap.servers}')
    String kafkaServers

    @Value('${kafkaServer.defaultReplications:1}')
    Short defaultReplications

    def run() {
        KafkaTopicGeneratorBuilder generatorBuilder = new KafkaTopicGeneratorBuilder(kafkaServers)
                .setDefaultReplicas(defaultReplications)
                .setDevMode(Environment.isDevelopmentMode())

        KafkaTopicGenerator generator = generatorBuilder.build()
        generator.doRun()
    }
}
