package demo.limei

import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class UserController {

    def asyncCreateDemoRecordService

    @Value('${kafka.createDemo.topic}')
    String createDemoTopic

    def index() { }

    def createByKafka() {
        def data = request.JSON
        asyncCreateDemoRecordService.sendMessage(data)
        render([taskAccepted: true] as JSON)
    }
}
