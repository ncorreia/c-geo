package carnero.cgeo;

import android.text.Spannable;
import java.util.Date;
import java.util.ArrayList;

public class cgCache {
	public Long updated = null;
    public Long detailedUpdate = null;
    public Integer reason = 0;
    public Boolean detailed = false;
	public String geocode = "";
	public String cacheid = "";
	public String guid = "";
	public String type = "";
	public String name = "";
	public Spannable nameSp = null;
	public String owner = "";
	public Date hidden = null;
	public String hint = "";
	public String size = "";
	public Float difficulty = new Float(0);
	public Float terrain = new Float(0);
	public String direction = null;
	public Double distance = null;
	public String latlon = "";
	public String latitudeString = "";
	public String longitudeString = "";
	public String location = "";
	public Double latitude = null;
	public Double longitude = null;
	public String shortdesc = "";
	public String description = "";
	public boolean disabled = false;
	public boolean archived = false;
	public boolean members = false;
	public boolean found = false;
	public boolean favourite = false;
	public int inventoryCoins = 0;
	public int inventoryTags = 0;
	public int inventoryUnknown = 0;
	public ArrayList<String> attributes = new ArrayList<String>();
	public ArrayList<cgWaypoint> waypoints = new ArrayList<cgWaypoint>();
	public ArrayList<cgSpoiler> spoilers = new ArrayList<cgSpoiler>();
	public ArrayList<cgLog> logs = new ArrayList<cgLog>();
	public ArrayList<cgTrackable> inventory = new ArrayList<cgTrackable>();

	public cgCache merge(cgCache oldCache) {
        if (oldCache == null) return this;

		updated = System.currentTimeMillis();
        if (detailed == false && oldCache.detailed == true) {
            detailed = true;
            detailedUpdate = System.currentTimeMillis();
        }
        if (reason == null || reason == 0) reason = oldCache.reason;
		if (geocode == null || geocode.length() == 0) geocode = oldCache.geocode;
		if (cacheid == null || cacheid.length() == 0) cacheid = oldCache.cacheid;
		if (guid == null || guid.length() == 0) guid = oldCache.guid;
		if (type == null || type.length() == 0) type = oldCache.type;
		if (name == null || name.length() == 0) name = oldCache.name;
		if (nameSp == null || nameSp.length() == 0) nameSp = oldCache.nameSp;
		if (owner == null || owner.length() == 0) owner = oldCache.owner;
		if (hidden == null) hidden = oldCache.hidden;
		if (hint == null || hint.length() == 0) hint = oldCache.hint;
		if (size == null || size.length() == 0) size = oldCache.size;
		if (difficulty == null || difficulty == 0) difficulty = oldCache.difficulty;
		if (terrain == null || terrain == 0) terrain = oldCache.terrain;
		if (direction == null || direction.length() == 0) direction = oldCache.direction;
		if (distance == null) distance = oldCache.distance;
		if (latlon == null || latlon.length() == 0) latlon = oldCache.latlon;
		if (latitudeString == null || latitudeString.length() == 0) latitudeString = oldCache.latitudeString;
		if (longitudeString == null || longitudeString.length() == 0) longitudeString = oldCache.longitudeString;
		if (location == null || location.length() == 0) location = oldCache.location;
		if (latitude == null) latitude = oldCache.latitude;
		if (longitude == null) longitude = oldCache.longitude;
		if (shortdesc == null || shortdesc.length() == 0) shortdesc = oldCache.shortdesc;
		if (description == null || description.length() == 0) description = oldCache.description;
		if (inventoryCoins == 0) inventoryCoins = oldCache.inventoryCoins;
		if (inventoryTags == 0) inventoryTags = oldCache.inventoryTags;
		if (inventoryUnknown == 0) inventoryUnknown = oldCache.inventoryUnknown;
		if (attributes == null || attributes.isEmpty()) attributes = oldCache.attributes;
		if (waypoints == null || waypoints.isEmpty()) waypoints = oldCache.waypoints;
		if (spoilers == null || spoilers.isEmpty()) spoilers = oldCache.spoilers;
		if (logs == null || logs.isEmpty()) logs = oldCache.logs;
		if (inventory == null || inventory.isEmpty()) inventory = oldCache.inventory;

		return this;
	}
}