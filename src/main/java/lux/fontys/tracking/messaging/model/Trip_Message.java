package lux.fontys.tracking.messaging.model;


public class Trip_Message {
	String trackerID;
	String tripID;
	String longitude;
	String latitude;
	String trackedAt;

	public String getTrackerID() {
		return trackerID;
	}

	public void setTrackerID(String trackerID) {
		this.trackerID = trackerID;
	}

	public String getTripID() {
		return tripID;
	}

	public void setTripID(String tripID) {
		this.tripID = tripID;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getTrackedAt() {
		return trackedAt;
	}

	public void setTrackedAt(String trackedAt) {
		this.trackedAt = trackedAt;
	}
}
