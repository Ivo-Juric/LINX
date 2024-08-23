
public class Penalty {

	private String description;
    private boolean disqualifications;

    public Penalty(String description, Boolean disqualifications) {
    	this.description = description;
    	this.disqualifications = disqualifications;
    }

    public String getDescription() {
		return description;
	}

	public Boolean getDisqualifications() {
		return disqualifications;
	}
	
	public void verificarNatacion(double temperatura, double tiempoEnAgua, double distancia, boolean usoNeoprene) {
	        
		if (distancia <= 750) {
			if (temperatura > 20 && usoNeoprene == true) {
	            penalizarNatacion ("Prohibido el uso de neoprene por encima de los 20 grados para la distancia de 750m o menos", true);
	        } 	else if (temperatura < 14 && !usoNeoprene == false) {
	            	penalizarNatacion ("Obligatorio el uso de neoprene por debajo de los 14 grados para la distancia de 750m o menos", true);
	            }
	        if (timepoAtleta > 20) {
	            penalizarNatacion ("El atleta supero el tiempo maximo para terminar la fase de natacion en la distancia de 750m o menos", true);
	        }
	    }
	        
		if (distancia > 750 && distancia <= 1500) {
			if (temperatura > 20 && usoNeoprene == true) {
				penalizarNatacion ("Prohibido el uso de neoprene por encima de los 20 grados para la distancia de 750m y 1500m", true);
			} 	else if (temperatura < 14 && !usoNeoprene == false) {
					penalizarNatacion ("Obligatorio el uso de neoprene por debajo de los 14 grados para la distancia de 750m y 1500m", true);
				}
			if (timepoAtleta > 40) {
				penalizarNatacion ("El atleta supero el tiempo maximo para terminar la fase de natacion en la distancia de 750m y 1500m", true);
			}
		}
	        
	    if (distancia > 1500 && distancia <= 3000) {
	    	if (temperatura > 22 && usoNeoprene == true) {
	    		penalizarNatacion ("Prohibido el uso de neoprene por encima de los 20 grados para la distancia de 1500m y 3000m", true);
	    	} 	else if (temperatura < 15 && !usoNeoprene == false) {
	            	penalizarNatacion ("Obligatorio el uso de neoprene por debajo de los 14 grados para la distancia de 1500m y 3000m", true);
	            }
	        if (timepoAtleta > 90) {
	        	penalizarNatacion ("El atleta supero el tiempo maximo para terminar la fase de natacion en la distancia de 1500m y 3000m", true);
	        }
	    }
	        
	    if (distancia > 3000 && distancia <= 4000) {
	    	if (temperatura > 23 && usoNeoprene == true) {
	    		penalizarNatacion ("Prohibido el uso de neoprene por encima de los 20 grados para la distancia de 3000m y 4000m", true);
	    	} 	else if (temperatura < 16 && !usoNeoprene == false) {
	            	penalizarNatacion ("Obligatorio el uso de neoprene por debajo de los 14 grados para la distancia de 3000m y 4000m", true);
	            }
	    	if (timepoAtleta > 140) {
	    		penalizarNatacion ("El atleta supero el tiempo maximo para terminar la fase de natacion en la distancia de 3000m y 4000m", true);
	    	}
		}
	}
	            
	private void penalizarNatacion (String description, boolean disqualifications) {
	}

	public void verificarTiemposMaximos(double tiempoMaxT2, double tiempoMaxMeta) {
		
		if (tiempoMaxT2 > timepoAtleta) {
			penalizarTiempoMaximo ("El atleta supero el tiempo maximo para llegar al T2", true);
		} else if (tiempoMaxMeta > timepoAtleta) {
			penalizarTiempoMaximo ("El atleta supero el tiempo maximo para llegar a la Meta", true);
	    }
	} 
	 
	private void penalizarTiempoMaximo(String descripcion, boolean descalificacion) {
	}
	
	 public void verificarDrafting(boolean draftingPermitido, boolean cometioDrafting) {
		 
		 if (!draftingPermitido && cometioDrafting) {
			 penalizarDrafting ("Drafting ilegal durante la etapa de ciclismo", false, 1);
		 }
	 }
	 
	 private void penalizarDrafting (String descripcion, boolean descalificacion, double tiempo) {
	 }
	 
}