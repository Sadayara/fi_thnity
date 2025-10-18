package tn.esprit.fi_thnity.models;

public enum TransportType {
    TAXI("🚖", "Taxi", 4, true),
    TAXI_COLLECTIF("🚐", "Taxi Collectif", 8, true),
    PRIVATE_CAR("🚗", "Private Car", 3, true),
    METRO("🚇", "Metro", 0, false),
    BUS("🚌", "Bus", 0, false);

    private final String emoji;
    private final String displayName;
    private final int maxSeats;
    private final boolean isShareable;

    TransportType(String emoji, String displayName, int maxSeats, boolean isShareable) {
        this.emoji = emoji;
        this.displayName = displayName;
        this.maxSeats = maxSeats;
        this.isShareable = isShareable;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public boolean isShareable() {
        return isShareable;
    }

    public boolean isPublicTransport() {
        return this == METRO || this == BUS;
    }

    @Override
    public String toString() {
        return emoji + " " + displayName;
    }
}
