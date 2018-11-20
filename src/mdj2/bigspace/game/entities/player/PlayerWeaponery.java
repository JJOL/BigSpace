package mdj2.bigspace.game.entities.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mdj2.bigspace.engine.services.ServiceProvider;
import mdj2.bigspace.engine.storage.IStorage;

public class PlayerWeaponery {

	private List<String> weaponNames;
	private Map<String, Integer> weaponAmmo;
	private Player holder;
	
	private Weapon currWeapon;
	
	public PlayerWeaponery(Player player) {
		weaponNames = new ArrayList<>();
		weaponAmmo = new HashMap<>();
		
		holder = player;
	}
	
	public void load() {
		IStorage storage = ServiceProvider.getStorage();
		List<String> unlockedWeapons = storage.getList("savedWeapons");
		if (unlockedWeapons != null) {
			for (String weaponName : unlockedWeapons) {
				weaponNames.add(weaponName);
				String ammoQ = storage.getValue("weap_ammo_" + weaponName);
				if (ammoQ != null) {
					weaponAmmo.put(weaponName, Integer.parseInt(ammoQ));
				}
			}
		}
	}
	
	public void shootWeapon() {
		/*int currAmmo = weaponAmmo.get(currWeapon.getName());
		if (currAmmo > 0) {
			int ammoUsed = currWeapon.shoot();
			weaponAmmo.put(currWeapon.getName(), currAmo - ammoUsed);
		}*/
	}

	public void rollForwardWeapon() {
		// TODO Auto-generated method stub
		
	}
	
	public void rollBackWeapon() {
		// TODO Auto-generated method stub
		
	}
	
}
