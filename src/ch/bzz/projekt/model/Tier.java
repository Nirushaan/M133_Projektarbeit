package ch.bzz.projekt.model;

import javax.ws.rs.FormParam;

/**
 * short description
 * <p>
 * Projektarbeit
 *
 * @author Nirushaan Sureshkumar
 * @version 1.0
 * @since 27.02.20
 */
public class Tier {
    @FormParam("tier")
    private String tier;


    private String tierID;

    @FormParam("name")
    private String name;

    @FormParam("alter")
    private int alter;

    @FormParam("geschlecht")
    private String geschlecht;

    @FormParam("groesse")
    private double groesse;

    /**
     * Gets the tier
     *
     * @return value of tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * Sets the tier
     *
     * @param tier the value to set
     */

    public void setTier(String tier) {
        this.tier = tier;
    }

    /**
     * Gets the tierID
     *
     * @return value of tierID
     */
    public String  getTierID() {
        return tierID;
    }

    /**
     * Sets the tierID
     *
     * @param tierID the value to set
     */

    public void setTierID(String  tierID) {
        this.tierID = tierID;
    }

    /**
     * Gets the name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name the value to set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the alter
     *
     * @return value of alter
     */
    public int getAlter() {
        return alter;
    }

    /**
     * Sets the alter
     *
     * @param alter the value to set
     */

    public void setAlter(int alter) {
        this.alter = alter;
    }

    /**
     * Gets the geschlecht
     *
     * @return value of geschlecht
     */
    public String getGeschlecht() {
        return geschlecht;
    }

    /**
     * Sets the geschlecht
     *
     * @param geschlecht the value to set
     */

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * Gets the groesse
     *
     * @return value of groesse
     */
    public double getGroesse() {
        return groesse;
    }

    /**
     * Sets the groesse
     *
     * @param groesse the value to set
     */

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }



}
