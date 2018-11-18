package mdj2.bigspace.engine.gameobjects;

import java.awt.Graphics2D;

import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.math.Vec2f;

public abstract class GameObject {

	// GameObject Attributes 
	private static int countId = 0;
	public static int nextId() {
		return countId++;
	}
	
	private final int gId;
	
	protected GameWorld world;
	protected boolean   shouldBeRemoved;
	
	// Physics
	
	// For Gravity Acceleration
	protected float mass;
	
	protected Vec2f acc;
	protected Vec2f vel;
	protected Vec2f pos;
	
	protected Collider collider;
	
	// For Sprite/Graphics Rendering and Animation
	
	// For AI Processing
	
	// For External Reactions
	
	public GameObject(GameWorld world) {
		gId = nextId();
		
		// Creates Default vars
		this.world = world;
		
		this.mass = 1f;
		this.pos = new Vec2f();
		this.vel = new Vec2f();
		this.acc = new Vec2f();
		this.collider = new Collider();
	}
	
	public int getId() {
		return gId;
	}
	
	// Physics
	public Collider getCollider() {
		return collider;
	}
	
	public Vec2f getPos() {
		return pos;
	}
	
	public void setPos(Vec2f v) {
		pos.x = v.x;
		pos.y = v.y;
	}
	
	public Vec2f getVel() {
		return vel;
	}
	
	public void setVel(Vec2f v) {
		vel.x = v.x;
		vel.y = v.y;
	}
	
	public Vec2f getAcc() {
		return acc;
	}
	
	public void setAcc(Vec2f v) {
		acc.x = v.x;
		acc.y = v.y;
	}
	
	public float getMass() {
		return mass;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}
	
	// World 
	public boolean shouldBeRemoved() {
		return shouldBeRemoved;
	}
	// Events
	public void onWorldSpawn() {
		
	}
	
	public void onWorldRemove() {
		
	}
	
	public void render(Graphics2D g) {};
	
	public void update() {}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof GameObject) {
			if (gId == ((GameObject)obj).getId()) {
				return true;
			}
		}
		
		return false;
	}
	
}
