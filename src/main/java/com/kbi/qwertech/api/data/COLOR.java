package com.kbi.qwertech.api.data;

import gregapi.data.CS;

import java.util.HashMap;

public class COLOR {
    public static HashMap<String, Integer> colorDictionary = new HashMap<String, Integer>();

    public static void put(String name, int color)
    {
        colorDictionary.put(name, color);
    }

    public static int get(String name)
    {
        if (colorDictionary.containsKey(name))
        {
            return colorDictionary.get(name);
        } else {
            System.out.println("Could not find color for " + name);
            return 0;
        }
    }

    public static int getMin(int num1, int num2)
    {
        System.out.println("Getting minimum for " + num1 + " and " + num2);
        int returnable = 0;
        float f1 = (num1 >> 16 & 255);
        float f2 = (num1 >> 8 & 255);
        float f3 = (num1 & 255);

        float g1 = (num2 >> 16 & 255);
        float g2 = (num2 >> 8 & 255);
        float g3 = (num2 & 255);

        if (f1 < g1)
        {
            returnable = (int)(f1 * Math.pow(2, 16));
        } else {
            returnable = (int)(g1 * Math.pow(2, 16));
        }
        if (f2 < g2)
        {
            returnable = returnable + (int)(f2 * Math.pow(2, 8));
        } else {
            returnable = returnable + (int)(g2 * Math.pow(2, 8));
        }
        if (f3 < g3)
        {
            returnable = returnable + (int)(f3);
        } else {
            returnable = returnable + (int)(g3);
        }
        System.out.println("It's " + returnable);
        return returnable;
    }

    public static int getMax(int num1, int num2)
    {
        System.out.println("Getting maximum for " + num1 + " and " + num2);
        int returnable = 0;
        float f1 = (num1 >> 16 & 255);
        float f2 = (num1 >> 8 & 255);
        float f3 = (num1 & 255);

        float g1 = (num2 >> 16 & 255);
        float g2 = (num2 >> 8 & 255);
        float g3 = (num2 & 255);

        if (f1 > g1)
        {
            returnable = (int)(f1 * Math.pow(2, 16));
        } else {
            returnable = (int)(g1 * Math.pow(2, 16));
        }
        if (f2 > g2)
        {
            returnable = returnable + (int)(f2 * Math.pow(2, 8));
        } else {
            returnable = returnable + (int)(g2 * Math.pow(2, 8));
        }
        if (f3 > g3)
        {
            returnable = returnable + (int)(f3);
        } else {
            returnable = returnable + (int)(g3);
        }
        System.out.println("It's " + returnable);
        return returnable;
    }

    public static int getPercentageShift(int low, int high, float percentage)
    {
        if (percentage > 1.0F) percentage = percentage * 0.01F;
        int returnable = 0;
        short f1 = (short)(low >> 16 & 255);
        short f2 = (short)((low >> 8 & 255));
        short f3 = (short)((low & 255));

        short g1 = (short)((high >> 16 & 255));
        short g2 = (short)((high >> 8 & 255));
        short g3 = (short)((high & 255));

        int h1 = g1 - f1;
        int h2 = g2 - f2;
        int h3 = g3 - f3;
        returnable = (f1 + (int)(h1 * percentage)) * (int)Math.pow(2, 16);
        returnable = returnable + (f2 + (int)(h2 * percentage)) * (int)Math.pow(2, 8);
        returnable = returnable + (f3 + (int)(h3 * percentage));
        return returnable;
    }

    public static int getRandom(int low, int high)
    {
        System.out.println("Randomly choosing between " + low + " and " + high);
        int returnable = getPercentageShift(low, high, CS.RNGSUS.nextFloat());
        System.out.println("It's " + returnable);
        return returnable;
    }

    public static int make(int red, int green, int blue)
    {
        int returnable = red * (int)Math.pow(2, 16);
        returnable = returnable + (green * (int)Math.pow(2, 8));
        returnable = returnable + blue;
        return returnable;
    }

    static {
        put("Alice Blue", 15792383);
        put("Antique White", 16444375);
        put("Aqua", 65535);
        put("Aquamarine", 8388564);
        put("Azure", 15794175);
        put("Beige", 16119260);
        put("Bisque", 16770244);
        put("Black", 0);
        put("Blanched Almond", 16772045);
        put("Blue", 255);
        put("Blue Violet", 9055202);
        put("Brown", 10824234);
        put("Burly Wood", 14596231);
        put("Cadet Blue", 6266528);
        put("Chartreuse", 8388352);
        put("Chocolate", 13789470);
        put("Coral", 16744272);
        put("Cornflower Blue", 6591981);
        put("Cornsilk", 16775388);
        put("Crimson", 14423100);
        put("Cyan", 65535);
        put("Dark Blue", 139);
        put("Dark Cyan", 35723);
        put("Dark Goldenrod", 12092939);
        put("Dark Grey", 11119017);
        put("Dark Gray", 11119017);
        put("Dark Green", 25600);
        put("Dark Khaki", 12433259);
        put("Dark Magenta", 9109643);
        put("Dark Olive Green", 5597999);
        put("Dark Orange", 16747520);
        put("Dark Orchid", 10040012);
        put("Dark Red", 9109504);
        put("Dark Salmon", 15308410);
        put("Dark Sea Green", 9419919);
        put("Dark Slate Blue", 4734347);
        put("Dark Slate Grey", 3100495);
        put("Dark Slate Gray", 3100495);
        put("Dark Turquoise", 52945);
        put("Dark Violet", 9699539);
        put("Deep Pink", 16716947);
        put("Deep Sky Blue", 49151);
        put("Dim Grey", 6908265);
        put("Dim Gray", 6908265);
        put("Dodger Blue", 2003199);
        put("Fire Brick", 11674146);
        put("Floral White", 16775920);
        put("Forest Green", 2263842);
        put("Fuchsia", 16711935);
        put("Fuschia", 16711935);
        put("Gainsboro", 14474460);
        put("Ghost White", 16316671);
        put("Gold", 16766720);
        put("Goldenrod", 14329120);
        put("Grey", 8421504);
        put("Gray", 8421504);
        put("Green", 32768);
        put("Green Yellow", 11403055);
        put("Honeydew", 15794160);
        put("Hot Pink", 16738740);
        put("Indian Red", 13458524);
        put("Indigo", 4915330);
        put("Ivory", 16777200);
        put("Khaki", 15787660);
        put("Lavender", 15132410);
        put("Lavender Blush", 16773365);
        put("Lawn Green", 8190976);
        put("Lemon Chiffon", 16775885);
        put("Light Blue", 11393254);
        put("Light Coral", 15761536);
        put("Light Cyan", 14745599);
        put("Light Goldenrod", 16448210);
        put("Light Goldenrod Yellow", 16448210);
        put("Light Grey", 13882323);
        put("Light Gray", 13882323);
        put("Light Green", 9498256);
        put("Light Pink", 16758465);
        put("Light Salmon", 16752762);
        put("Light Sea Green", 2142890);
        put("Light Sky Blue", 8900346);
        put("Light Slate Grey", 7833753);
        put("Light Slate Gray", 7833753);
        put("Light Steel Blue", 11584734);
        put("Light Yellow", 16777184);
        put("Lime", 65280);
        put("Lime Green", 3329330);
        put("Linen", 16445670);
        put("Magenta", 16711935);
        put("Maroon", 8388608);
        put("Medium Aquamarine", 6737322);
        put("Medium Blue", 205);
        put("Medium Orchid", 12211667);
        put("Medium Purple", 9662683);
        put("Medium Sea Green", 3978097);
        put("Medium Slate Blue", 8087790);
        put("Medium Spring Green", 64154);
        put("Medium Turqoiuse", 4772300);
        put("Medium Violet Red", 13047173);
        put("Midnight Blue", 1644912);
        put("Mint Cream", 16121850);
        put("Misty Rose", 16770273);
        put("Moccasin", 16770229);
        put("Navajo White", 16768685);
        put("Navy Blue", 128);
        put("Old Lace", 16643558);
        put("Olive", 8421376);
        put("Olive Drab", 7048739);
        put("Orange", 16753920);
        put("Orange Red", 16729344);
        put("Orchid", 14315734);
        put("Pale Goldenrod", 15657130);
        put("Pale Green", 10025880);
        put("Pale Turquoise", 11529966);
        put("Pale Violet Red", 14381203);
        put("Papaya Whip", 16773077);
        put("Peach Puff", 16767673);
        put("Peru", 13468991);
        put("Pink", 16761035);
        put("Plum", 14524637);
        put("Powder Blue", 11591910);
        put("Purple", 8388736);
        put("Rebecca Purple", 6697881);
        put("Red", 16711680);
        put("Rosy Brown", 12357519);
        put("Royal Blue", 4286945);
        put("Saddle Brown", 9127187);
        put("Salmon", 16416882);
        put("Sea Green", 3050327);
        put("Seashell", 16774638);
        put("Sea Shell", 16774638);
        put("Sienna", 10506797);
        put("Silver", 12632256);
        put("Sky Blue", 8900331);
        put("Slate Blue", 6970061);
        put("Slate Gray", 7372944);
        put("Slate Grey", 7372944);
        put("Snow", 16775930);
        put("Spring Green", 65407);
        put("Steel Blue", 4620980);
        put("Tan", 13808780);
        put("Teal", 32896);
        put("Thistle", 14204888);
        put("Tomato", 16737095);
        put("Turquoise", 4251856);
        put("Violet", 15631086);
        put("Wheat", 16113331);
        put("White", 16777215);
        put("White Smoke", 16119285);
        put("Yellow", 16776960);
        put("Yellow Green", 10145074);
    }
}
