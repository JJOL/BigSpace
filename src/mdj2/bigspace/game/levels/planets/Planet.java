package mdj2.bigspace.game.levels.planets;

public class Planet {
	
	public static String DEMO_PLANET = "tutorial";
	
	private static final PlanetInfo FIRE_PLANET  = new FirePlanet();
	private static final PlanetInfo WATER_PLANET = new WaterPlanet();
	private static final PlanetInfo EARTH_PLANET = new EarthPlanet();
	private static final PlanetInfo AIR_PLANET   = new AirPlanet();

	public static PlanetInfo loadPlanet(String planetName) {
		PlanetInfo planet = null;
		if (planetName.equalsIgnoreCase("fire")) {
			planet = FIRE_PLANET;
		} else if (planetName.equalsIgnoreCase("water")){
			planet = WATER_PLANET;
		} else if (planetName.equalsIgnoreCase("earth")){
			planet = EARTH_PLANET;
		} else if (planetName.equalsIgnoreCase("air")){
			planet = AIR_PLANET;
		} else {
			planet = new FirePlanet();
		}
		
		return planet;
	}
}
