package com.weatherplus.rain;

/**
 * Holds the runtime state of the WeatherPlus rain system for a single ServerLevel.
 * This is NOT saved to disk — vanilla handles rain persistence.
 * WeatherPlus only controls the visual/audio intensity layer on top.
 */
public class RainState {

    /** Current stage of rain */
    private RainStage stage = RainStage.NONE;

    /**
     * Current interpolated strength [0.0 – 1.0].
     * This is what drives particle/sound scaling.
     * It ramps gradually toward stage.targetStrength each tick.
     */
    private float currentStrength = 0f;

    /**
     * Ticks remaining before we check whether rain escalates to the next stage.
     * Resets when a new escalation window begins.
     */
    private int escalationCooldown = 0;

    /**
     * Ticks remaining in the current rain session.
     * When this hits 0 we begin the fade-out.
     */
    private int rainDurationTicks = 0;

    /** True when vanilla rain is active (synced from ServerLevel each tick) */
    private boolean vanillaRaining = false;

    // ── Getters / Setters ────────────────────────────────────────────────────

    public RainStage getStage() { return stage; }
    public void setStage(RainStage stage) { this.stage = stage; }

    public float getCurrentStrength() { return currentStrength; }
    public void setCurrentStrength(float v) { this.currentStrength = Math.max(0f, Math.min(1f, v)); }

    public int getEscalationCooldown() { return escalationCooldown; }
    public void setEscalationCooldown(int v) { this.escalationCooldown = Math.max(0, v); }

    public int getRainDurationTicks() { return rainDurationTicks; }
    public void setRainDurationTicks(int v) { this.rainDurationTicks = Math.max(0, v); }

    public boolean isVanillaRaining() { return vanillaRaining; }
    public void setVanillaRaining(boolean v) { this.vanillaRaining = v; }

    /** Returns true if our system considers rain active */
    public boolean isActive() {
        return stage != RainStage.NONE;
    }

    /** Resets to a clean stopped state */
    public void reset() {
        stage = RainStage.NONE;
        currentStrength = 0f;
        escalationCooldown = 0;
        rainDurationTicks = 0;
    }
}
