package net.aschemann.raspi.advent

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalOutput
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin
import groovy.util.logging.Slf4j

/**
 * Created by ascheman on 26.11.16.
 */
@Slf4j
class AdventMain {


    public static void main (String[] args) {
        log.info ("Starting into Advent")

        AdventController controller = new AdventController()

        while (true) {
            controller.reset()
//            controller.first()
//            controller.second()
//            controller.third()
//            controller.fourth()
            controller.advent(1)
            controller.advent(2)
            controller.advent(3)
            controller.advent(4)
            controller.over()

            controller.reset()
            controller.advent(2)

            Thread.sleep(15000)
            log.info ("Starting the loop again!")
        }
        controller.stop()

        log.info("Advent is OVER - see you next year!")
    }
}
