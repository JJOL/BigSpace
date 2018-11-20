package mdj2.bigspace.engine.graphics;

import mdj2.bigspace.game.resources.ResLoader;

public class Sprites {

	private static CachedSprite enemy1 = 
			create("test.png", 32, 32)
			.addImage(0,0)
			.addImage(0,1)
			.addImage(1,1)
			.addImage(1,0)
			.build();
	
	private static CachedSprite astronaut =
			create("astronaut.png", 283, 285)
			.addImage(0, 0)
			.addImage(1, 0)
			.addImage(2, 0)
			.addImage(3, 0)
			.addImage(0, 1)
			.addImage(1, 1)
			.addImage(2, 1)
			.addImage(3, 1)
			.addImage(0, 2)
			.addImage(1, 2)
			.addImage(2, 2)
			.addImage(3, 2)
			.addImage(0, 3)
			.addImage(1, 3)
			.addImage(2, 3)
			.addImage(3, 3)
			.addImage(0, 4)
			.addImage(1, 4)
			.build();
	
	public static CachedSprite getSprite(String spriteName) {
		if (spriteName.equalsIgnoreCase("player")) {
			return astronaut;
		}
		
		return enemy1;/*
		if (spriteName.equalsIgnoreCase("enemy_1"))
			return enemy1;
		
		return null;*/
	}
	
	public static SpriteBuilder create(String fName, int cellW, int cellH) {
		return new SpriteBuilder(ResLoader.go("images").at("sprites").at(fName).getPath(), cellW, cellH);
	}
}
