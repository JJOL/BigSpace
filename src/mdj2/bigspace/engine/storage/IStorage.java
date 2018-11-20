package mdj2.bigspace.engine.storage;

import java.util.List;

public interface IStorage {

	public boolean putValue(String key, String value);
	public String getValue(String key);
	
	public boolean putList(String key, List<String> list);
	public boolean pushToList(String key, String value);
	public List<String> getList(String key);
	
	public boolean save();
	public boolean load();
	public boolean reset();
	
}
