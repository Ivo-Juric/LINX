package model;
public class Drink implements foodBoost {
	private int cantidad;
	
    public Drink(int cant){
    	this.setCantidad(cant);
    }

    public float boost() {
    	this.setCantidad(this.getCantidad() - 1);
        return 15;
    }

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}