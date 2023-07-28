package gui;

import java.io.Serializable;

/**
 * Statistics.java
 * Implements statistics POJO for game stats.
 * @author DrkWithT
 */
public class Statistics implements Serializable {
    private double roundsLost;
    private double roundsWon;

    public Statistics(double roundsLost, double roundsWon) {
        this.roundsLost = roundsLost;
        this.roundsWon = roundsWon;
    }

    public double getRoundsLost() {
        return roundsLost;
    }

    public double getRoundsWon() {
        return roundsWon;
    }

    public double getTotalRounds() {
        return roundsLost + roundsWon;
    }

    public void setRoundsLost(double roundsLost) {
        this.roundsLost = roundsLost;
    }

    public void setRoundsWon(double roundsWon) {
        this.roundsWon = roundsWon;
    }
}
