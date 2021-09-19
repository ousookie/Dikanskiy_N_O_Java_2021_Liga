package com.bad.code2.implementations;

import com.bad.code2.interfaces.Shape3D;

public class Qube implements Shape3D {

    private Double x;
    private Double y;
    private Double z;
    private Double edgeSize;

    public Qube(Double centerX, Double centerY, Double centerZ, Double s) {
        this.x = centerX;
        this.y = centerY;
        this.z = centerZ;
        this.edgeSize = s;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getZ() {
        return z;
    }

    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }

}
