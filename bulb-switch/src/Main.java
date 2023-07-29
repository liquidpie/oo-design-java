/*
* Dimmer switch and Light bulb
* MIN_LEVEL = 5
* MAX_LEVEL = 15
Dimmer Level | 5 Watt | 10 Watt | 20 Watt
5            | 0      | 0       | 0
15           | 5      | 10      | 20
10           | 2.5    | 5       | 10

*/

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // Illuminance - 5 watts
        Illuminance illuminance = new Illuminance(5);
        LightBulb bulb = new LightBulbImpl(illuminance);
        DimmerSwitch dimmerSwitch = new DimmerSwitchImpl(bulb);


        dimmerSwitch.updateBrightness(10);

        /*
         * The 5 watt bulb is 5 bright.
         */
    }

    interface LightBulb {

        void updateBrightness(double level);

    }

    interface DimmerSwitch {

        void updateBrightness(int level);

    }

    static class DimmerSwitchImpl implements DimmerSwitch {

        private static final int MIN_LEVEL = 5;
        private static final int MAX_LEVEL = 15;
        private final LightBulb lightBulb;

        DimmerSwitchImpl(LightBulb lightBulb) {
            this.lightBulb = lightBulb;
        }

        public void updateBrightness(int currentLevel) {
            if (currentLevel < MIN_LEVEL || currentLevel > MAX_LEVEL)
                throw new InputMismatchException();
            double brightness = (double) (currentLevel - MIN_LEVEL) / (MAX_LEVEL - MIN_LEVEL);
            lightBulb.updateBrightness(brightness);
        }

    }

    static class LightBulbImpl implements LightBulb {

        private final Illuminance illuminance;
        private double state;

        LightBulbImpl(Illuminance illuminance) {
            this.illuminance = illuminance;
        }

        @Override
        public void updateBrightness(double level) {
            this.state = illuminance.getWatt() * level;
            System.out.printf("The %s watt bulb is %s bright%n", illuminance.getWatt(), state);
        }

        public double getBrightnessState() {
            return state;
        }

    }

    static class Illuminance {

        private final int watt;

        Illuminance(int watt) {
            this.watt = watt;
        }

        public int getWatt() {
            return watt;
        }

    }

}