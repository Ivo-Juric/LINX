package model;

import java.io.Serializable;

public class Drink implements foodBoost, Serializable{

	private static final long serialVersionUID = 1L;
	private int cantidad;
	
    public Drink(int cant){
    	this.setCantidad(cant);
    }

    public float boost() {
    	this.setCantidad(this.getCantidad() - 1);
        return 50;
    }

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}