package ch.bzz.projekt.model;

import java.util.Map;

/**
 * short description
 * <p>
 * Projektarbeit
 *
 * @author Nirushaan Sureshkumar
 * @version 1.0
 * @since 13.03.20
 */
public class Zoowaerter {

    private Map<String, Tier> tierMap;

    public Zoowaerter(){

    }

    /**
     * Gets the tierMap
     *
     * @return value of tierMap
     */
    public Map<String, Tier> getTierMap() {
        return tierMap;
    }

    /**
     * Sets the tierMap
     *
     * @param tierMap the value to set
     */

    public void setTierMap(Map<String, Tier> tierMap) {
        this.tierMap = tierMap;
    }

    /**
     * gets a tier by its tierID
     * @param tierID the unique key of tier
     * @return Tier-Object
     */
    public Tier getTierByTierID(String tierID){
        if (getTierMap().containsKey(tierID)) {
            return getTierMap().get(tierID);
        }
        return null;
    }
}
