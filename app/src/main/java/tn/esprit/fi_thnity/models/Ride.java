package tn.esprit.fi_thnity.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ride {
    private String rideId;
    private String userId;
    private String userName;
    private String userPhotoUrl;
    private RideType rideType; // REQUEST or OFFER
    private TransportType transportType;
    private Location origin;
    private Location destination;
    private int availableSeats;
    private RideStatus status;
    private long createdAt;
    private long expiresAt;

    public enum RideType {
        REQUEST, // User needs a ride
        OFFER    // User is offering a ride
    }

    public enum RideStatus {
        ACTIVE,
        MATCHED,
        COMPLETED,
        CANCELLED,
        EXPIRED
    }

    public Ride() {
        // Default constructor required for Firebase
    }

    public Ride(String userId, String userName, RideType rideType, TransportType transportType,
                Location origin, Location destination, int availableSeats) {
        this.userId = userId;
        this.userName = userName;
        this.rideType = rideType;
        this.transportType = transportType;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.status = RideStatus.ACTIVE;
        this.createdAt = System.currentTimeMillis();
        this.expiresAt = createdAt + (2 * 60 * 60 * 1000); // Expires in 2 hours
    }

    // Getters and Setters
    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAt;
    }

    public boolean isActive() {
        return status == RideStatus.ACTIVE && !isExpired();
    }

    @Override
    public String toString() {
        return rideType + " - " + transportType + " from " + origin + " to " + destination;
    }
}
