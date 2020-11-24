package shareonfoot;

public class Location {
	
	private String name;
	private String lng;
	private String lat;
	private String dst;
	
	public Location() {
		super();
	}
		
	public Location(String name,String lng, String lat, String dst) {
		super();
		this.name = name;
		this.lng = lng;
		this.lat = lat;
		this.dst = dst;
		
	}
	
	public String getname() {
		return name;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public String getlng() {
		return lng;
	}
	
	public void setlng(String lng) {
		this.lng = lng;
	}
	
	public String getlat() {
		return lat;
	}
	
	public void setlat(String lat) {
		this.lat = lat;
	}
	
	public String getdst() {
		return dst;
	}
	
	public void setdst(String dst) {
		this.dst = dst;
	}
	
}
