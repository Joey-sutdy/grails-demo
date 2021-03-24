package demo.limei

class BootStrap {

    def topicDeclarerService
    def asyncCreateDemoRecordService

    def init = { servletContext ->
        topicDeclarerService.run()
        asyncCreateDemoRecordService.start()

    }
    def destroy = {
        asyncCreateDemoRecordService.shutdown()
    }
}
