package tn.esprit.forummicroservice.Entity;

public enum EmojiType {
    LIKE("👍", "Like"),
    HAHA("😂", "Haha"),
    LOVE("❤️", "Love"),
    SAD("😢", "Sad"),
    ANGRY("😡", "Angry");

    private final String emoji;
    private final String description;

    EmojiType(String emoji, String description) {
        this.emoji = emoji;
        this.description = description;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDescription() {
        return description;
    }

    // You can also create a method to retrieve an enum from the description
    public static EmojiType fromDescription(String description) {
        for (EmojiType emojiType : EmojiType.values()) {
            if (emojiType.getDescription().equalsIgnoreCase(description)) {
                return emojiType;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }
}
