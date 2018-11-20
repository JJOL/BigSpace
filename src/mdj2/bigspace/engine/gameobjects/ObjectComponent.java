package mdj2.bigspace.engine.gameobjects;

import java.util.Observable;

public abstract class ObjectComponent extends Observable {

	protected GameObject baseObject;
	
	public ObjectComponent(GameObject _obj) {
		baseObject = _obj;
	}
} 
