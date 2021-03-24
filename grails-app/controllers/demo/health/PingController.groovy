package demo.health

import grails.converters.JSON

class PingController {

    def pong() {
        render status : 200, text : "pong"
    }

    def try1() {
        Thread.sleep(300000)
        render status : 200, text : "tried"
    }

    def review() {
        def res = [code: 1, msg: "success"]
        render res as JSON
    }
}
