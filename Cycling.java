
import java.util.*;

public abstract class Cycling extends Discipline {

	private boolean draft;
	
    public Cycling(){ 
    	super();
    }

    public abstract Penalty penalty();

    public boolean isDraft() {
		return draft;
	} 

}