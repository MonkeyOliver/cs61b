package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];

        for (int i = 0; i < KEYBOARD.length(); i++) {
            char c = KEYBOARD.charAt(i);
            strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (double) (i - 24) / 12));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            int index = 0;
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = KEYBOARD.indexOf(key);
                strings[index].pluck();
            }
            double sample = 0;
            /* compute the superposition of samples */
            for (int i = 0; i < KEYBOARD.length(); i++) {
                sample += strings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < KEYBOARD.length(); i++) {
                strings[i].tic();
            }
        }
    }
}

