package com.example.cyril.sensortagti;

import java.util.UUID;

/**
 * Implements the Humidity Sensor.
 */
public class HumiditySensor extends Sensor
{

    private double humidity;

    public HumiditySensor(UUID serviceUuid,BluetoothLeService mBluetoothLeService)
    {
        super(serviceUuid,mBluetoothLeService);
        this.humidity=0.0;
    }

    @Override
    public Point3D convert(byte[] value)
    {
        int a = shortUnsignedAtOffset(value, 2);
        // bits [1..0] are status bits and need to be cleared according
        // to the user guide, but the iOS code doesn't bother. It should
        // have minimal impact.
        a = a - (a % 4);
        this.humidity=(-6f) + 125f * (a / 65535f);
        return new Point3D(this.humidity,0,0);
    }

    @Override
    public String toString()
    {
        return this.humidity+" rH";
    }

}
