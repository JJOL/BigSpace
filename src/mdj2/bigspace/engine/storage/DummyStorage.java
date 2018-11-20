package mdj2.bigspace.engine.storage;

import java.util.List;

import mdj2.bigspace.engine.services.IService;

public class DummyStorage implements IService, IStorage{

	@Override
	public boolean putValue(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValue(String key) {
		if (key.equalsIgnoreCase("last_planet")) {
			return "fire";
		}
		if (key.equalsIgnoreCase("last_level")) {
			return "1";
		}
		return null;
	}

	@Override
	public boolean putList(String key, List<String> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pushToList(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean load() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub
		
	}
}
