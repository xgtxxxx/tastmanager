package task.model;

public class ReleaseVersion {
	public static final int NOMAL=3;
	public static final int DEL=1;
	public static final int ADD=2;
	private int id;
	private String name;
	private int active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
}
