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
	
	public static CachedSprite getSprite(String spriteName) {
		return enemy1;/*
		if (spriteName.equalsIgnoreCase("enemy_1"))
			return enemy1;
		
		return null;*/
	}
	
	public static SpriteBuilder create(String fName, int cellW, int cellH) {
		return new SpriteBuilder(ResLoader.go("images").at("sprites").at(fName).getPath(), cellW, cellH);
	}
}
