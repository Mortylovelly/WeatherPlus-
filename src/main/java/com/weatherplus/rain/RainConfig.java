package com.weatherplus.rain;

/**
 * All tunable constants for the WeatherPlus rain system.
 */
public final class RainConfig {

    private RainConfig() {}

    // ── Ramp speed ───────────────────────────────────────────────────────────

    /** ~2 minutes to ramp from 0 → 1.0 (0.00042 * 20tps * 120s ≈ 1.0) */
    public static final float RAMP_UP_PER_TICK   = 0.00042f;

    /** Slightly faster fade-out feels natural */
    public static final float RAMP_DOWN_PER_TICK = 0.00083f;

    // ── Escalation ───────────────────────────────────────────────────────────

    /**
     * Minimum ticks before the escalation roll happens.
     * Between MIN and MAX a random offset is added so not all rains
     * escalate at exactly the same time.
     * MIN = 3 minutes, MAX = 5 minutes
     */
    public static final int ESCALATION_DELAY_MIN = 20 * 60 * 3; // 3 min
    public static final int ESCALATION_DELAY_MAX = 20 * 60 * 5; // 5 min

    /**
     * ONE roll per stage transition. No repeated checks.
     * 40% chance LIGHT → MEDIUM, 25% chance MEDIUM → HEAVY.
     * If the roll fails the rain stays at its current stage until it stops.
     */
    public static final float CHANCE_LIGHT_TO_MEDIUM = 0.40f;
    public static final float CHANCE_MEDIUM_TO_HEAVY = 0.25f;

    // ── Particles ────────────────────────────────────────────────────────────

    /** Extra drip particles per spawn tick at full strength. Keep low. */
    public static final int BASE_PARTICLES_PER_TICK = 4;

    // ── Sound intervals (ticks) ──────────────────────────────────────────────

    public static final int SOUND_INTERVAL_LIGHT  = 120;
    public static final int SOUND_INTERVAL_MEDIUM =  80;
    public static final int SOUND_INTERVAL_HEAVY  =  40;

    // ── Vanilla command parity ───────────────────────────────────────────────

    /** Vanilla /weather rain → treated as MEDIUM by our system */
    public static final RainStage VANILLA_RAIN_COMMAND_STAGE = RainStage.MEDIUM;
}
