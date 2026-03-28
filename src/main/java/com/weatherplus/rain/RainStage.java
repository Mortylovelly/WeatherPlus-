package com.weatherplus.rain;

/**
 * Represents the three stages of rain intensity.
 * Rain always starts at LIGHT and can progress upward over time.
 */
public enum RainStage {
    NONE(0f),
    LIGHT(0.2f),
    MEDIUM(0.5f),    // This matches vanilla rain strength
    HEAVY(1.0f);

    /** Target rain strength for this stage (used for gradual transitions) */
    public final float targetStrength;

    RainStage(float targetStrength) {
        this.targetStrength = targetStrength;
    }

    public RainStage next() {
        return switch (this) {
            case NONE   -> LIGHT;
            case LIGHT  -> MEDIUM;
            case MEDIUM -> HEAVY;
            case HEAVY  -> HEAVY;
        };
    }

    public boolean canEscalate() {
        return this == LIGHT || this == MEDIUM;
    }
}
