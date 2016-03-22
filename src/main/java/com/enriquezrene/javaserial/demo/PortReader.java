package com.enriquezrene.javaserial.demo;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

/**
 * Created by rene on 22/03/16.
 */
public class PortReader implements SerialPortEventListener {

    private SerialPort serialPort;

    public PortReader(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public synchronized void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                byte[] buffer = serialPort.readBytes(1);
                System.out.print(new String(buffer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}