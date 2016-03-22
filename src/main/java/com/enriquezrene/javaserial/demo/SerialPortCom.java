package com.enriquezrene.javaserial.demo;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * Created by rene on 22/03/16.
 */
public class SerialPortCom {

    private SerialPort serialPort;

    public SerialPortCom() {

    }

    public void initialize(String serialPortName) throws Exception {

        serialPort = new SerialPort(serialPortName);
        // Open serial port
        serialPort.openPort();
        // Set connection params
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                SerialPort.FLOWCONTROL_RTSCTS_OUT);
        // Set a listener to read the ARDUINO output
        serialPort.addEventListener(new PortReader(serialPort), SerialPort.MASK_RXCHAR);
    }

    public void closeConnection() throws Exception {
        serialPort.closePort();
    }

    public void sendData(String data) {
        try {
            serialPort.writeString(data);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public String[] getAvailableSerialPorts() {
        String[] ports = SerialPortList.getPortNames();
        return ports;
    }

}
