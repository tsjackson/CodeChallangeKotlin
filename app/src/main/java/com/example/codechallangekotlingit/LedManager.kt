package com.example.codechallangekotlingit

import java.util.HashSet


/*
introduction:
Restaurants have expressed a desire for the tablet to visually notify the staff in certain cases:
1. Customer wants to summon the server (flashing red light, only turns off when the
    user forces it off)
2. Payment successful, not requesting receipt (solid green light, turns off after 3 minutes or
    user turns it off)
3. Payment successful, requesting receipt (flashing green light, turns off after 3 minutes or
    user turns it off)
4. Customer is starting to pay. (solid orange light, turns off when calling code turns it off or
    2 minutes(whichever comes first).)
Your objective is to create a manager that satisfies the above listed use cases. It should be
designed with the idea that new use cases may be requested and should be able to handle them with
little to no refactoring of the class you created.
Your solution should be production quality. You may include a readme.md file in the project to
detail the overall architecture of the app, how it is used and reasons it was done the way it was.
 */

/**
 * Class that changes the LED light for a particular hardware configuration.
 */
class LedManager {

    //Represents the hardware that would shine the light.
    private var deviceHardware: Hardware

    // Test initialiser
    init {
        deviceHardware = Hardware()
    }

    /**
     * Calls the "3rd party API" and sets the passed in [LedColor]s to the specified brightness.
     *
     * @param ledColors  The colors you want the led light to be (colors are combined to form new
     * colors (All colors together form white for example)). Should be a non-null,
     * and non-empty set. Each entry should be non-null.
     * @param brightness The brightness level the led light should be. Should be greater than zero.
     */
    protected fun setLedColors(ledColors: Set<LedColor>, brightness: Int) {
        //Set the current led colors and current brightness to what is passed in.
        //In a real implementation, the method would call the 3rd party API to invoke the change.

        deviceHardware.currentLedColors = HashSet(ledColors)
        deviceHardware.currentBrightness = brightness
    }

    /**
     * This class is used for testing the output of the [LedManager].
     */
    //VisibleForTesting
    inner class Hardware {
        /**
         * Currently visible led lights. It is a set due to it being able to support different
         * lights concurrently (example White=All lights combined, purple=Red+blue)
         */
        internal var currentLedColors: Set<LedColor>? = null
        /**
         * represents current brightness level of current lights. 0-100 (e.g. percent)
         */
        /**
         * Gets the current brightness level. NOTE: This method is visible for testing purposes only.
         *
         * @return An integer indicating the brightness of the LED light. This should be greater
         * than zero.
         */
        var currentBrightness: Int = 0

        /**
         * Gets the current LED colors that are being displayed. NOTE: This method is visible for
         * testing purposes only.
         *
         * @return The colors that are currently being used. Should be non-null and non-empty.
         * Each entry should be non-null.
         */
        fun getCurrentLedColors(): Set<LedColor> {
            return HashSet(currentLedColors)
        }
    }

    companion object {
        private val TAG = LedManager::class.java.simpleName
    }
}
