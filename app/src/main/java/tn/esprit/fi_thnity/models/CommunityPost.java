package tn.esprit.fi_thnity.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CommunityPost {
    private String postId;
    private String userId;
    private String userName;
    private String userPhotoUrl;
    private String content;
    private PostType postType;
    private Location location;
    private long timestamp;
    private int likesCount;

    public enum PostType {
        ACCIDENT("🚨", "Accident"),
        DELAY("⏱", "Delay"),
        ROAD_CLOSURE("🚧", "Road Closure"),
        ALTERNATIVE_ROUTE("🗺", "Alternative Route"),
        ADVICE("💡", "Advice"),
        GENERAL("📢", "General");

        private final String emoji;
        private final String displayName;

        PostType(String emoji, String displayName) {
            this.emoji = emoji;
            this.displayName = displayName;
        }

        public String getEmoji() {
            return emoji;
        }

        public String getDisplayName() {
            return displayName;
        }

        @Override
        public String toString() {
            return emoji + " " + displayName;
        }
    }

    public CommunityPost() {
        // Default constructor required for Firebase
    }

    public CommunityPost(String userId, String userName, String content, PostType postType, Location location) {
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.postType = postType;
        this.location = location;
        this.timestamp = System.currentTimeMillis();
        this.likesCount = 0;
    }

    // Getters and Setters
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getTimeAgo() {
        long diff = System.currentTimeMillis() - timestamp;
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) return days + "d ago";
        if (hours > 0) return hours + "h ago";
        if (minutes > 0) return minutes + "m ago";
        return "just now";
    }

    @Override
    public String toString() {
        return postType + ": " + content;
    }
}
