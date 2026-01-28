package model;

/*
*Represents the lifecycle of a trip.
*State transition rules:
*- REQUESTED -> DISPATCHED or CANCELLED
*- DISPATCHED -> ACTIVE or CANCELLED
*- ACTIVE -> COMPLETED or CANCELLED
*- CANCELLED and COMPLETED are terminal states
 */

public enum TripStatus {
    REQUESTED,   // Trip created, awaiting dispatch
    DISPATCHED,  // Driver assigned, not yet started
    ACTIVE,      // Driver has picked up passenger, currently driving
    COMPLETED,   // Trip successfully finished 
    CANCELLED;   // Trip cancelled before completion

    /** 
     * Checks if the status is terminal (no further transition is allowed).
     * Terminal states: COMPLETED, CANCELLED
     * @return true if status is COMPLETED or CANCELLED, false otherwise
     */
    public boolean isTerminal(){
        return this == COMPLETED || this == CANCELLED;
    }

}