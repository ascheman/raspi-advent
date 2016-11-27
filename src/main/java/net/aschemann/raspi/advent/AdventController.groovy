package net.aschemann.raspi.advent

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalOutput
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin
import groovy.util.logging.Slf4j

/**
 * Created by ascheman on 26.11.16.
 */
@Slf4j
class AdventController {

    public static final int DELAY_FOR_SINGLE_LIGHTS = 2000
    public static final int BLINK_TIME = 5000

    GpioController gpio
    GpioPinDigitalOutput[] adventLight

    AdventController () {
        log.info ("Initializing Advent")
        gpio = GpioFactory.getInstance()
        adventLight = new GpioPinDigitalOutput[5]
        initAdventLight(0, RaspiPin.GPIO_01)
        initAdventLight(1, RaspiPin.GPIO_02)
        initAdventLight(2, RaspiPin.GPIO_03)
        initAdventLight(3, RaspiPin.GPIO_04)
        initAdventLight(4, RaspiPin.GPIO_05)
    }

    private void initAdventLight(int no, Pin raspiPin) {
        log.info ("Initializing AdventLight #{}", no)
        adventLight[no] = gpio.provisionDigitalOutputPin(raspiPin, "Advent #${no}", PinState.LOW)
        adventLight[no].setShutdownOptions(true, PinState.LOW);
    }

    void reset () {
        log.info ("Resetting Advent")
        for (GpioPinDigitalOutput currentAdventLight : adventLight) {
            currentAdventLight.low()
        }
    }

    void advent (int no) {
        log.info ("It is Advent #{}", no)
        for (int i = 0; i < no; i++) {
            adventLight[i].high()
        }
        adventLight[0].high()
        Thread.sleep(DELAY_FOR_SINGLE_LIGHTS)
    }

    void current() {
        AdventComputer ac = new AdventComputer()
        int currentAdvent = ac.current()
        if (0 == currentAdvent) {
            over()
        } else if (1 <= currentAdvent && currentAdvent <= 4) {
            advent(currentAdvent)
        } else {
            throw new RuntimeException ("Advent #${currentAdvent} must never happen")
        }
    }

    void over () {
        log.info ("Advent is OVER")
        adventLight[0].high()
        adventLight[1].high()
        adventLight[2].high()
        adventLight[3].high()
        adventLight[4].blink((int) BLINK_TIME / 10, BLINK_TIME)
        Thread.sleep(BLINK_TIME)
        adventLight[4].low()
    }

    void stop () {
        log.info ("Shutting down Advent")
        gpio.shutdown()
    }
}
