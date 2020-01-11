package com.kbi.qwertech.api.data;

import java.util.ArrayList;

import static com.kbi.qwertech.api.data.NOTE.*;

public class MUSE {
    private static ArrayList<NOTE[]> songList = new ArrayList<NOTE[]>();
    public static NOTE[] getSong(int option)
    {
        if (option > songList.size()) option = option % songList.size();
        System.out.println("Chose song " + option);
        return songList.get(option);
    }

    public static int addSong(NOTE[] songy)
    {
        songList.add(songy);
        return songList.size();
    }

    public static void addSome()
    {
        //Axel
        addSong(new NOTE[]{A3, C3, A3, A3, D3, A3, G2, A3, E3, A3, A3, F3, E3, C3, A3, E3, A4, A3, G2, G2, E2, B3, A3, A3, A3});
        //Bites
        addSong(new NOTE[]{A3, A3, A3, /*beat*/ A3, A3, A3, A3, C3, A3, D3, /*beat*/ A3, A3, A3, /*beat*/ A3, A3, A3, A3, C3, A3, D3, E3, E3, D3, D3, C3, A3, E3, E3, D3, D3, C3, A3, E3, E3, E3, E3, E3, E3, E3, D3, /*beat*/ B3, B3, C3, D3, D3, D3, D3, B3, B3, B3, B3, B3, F3, G2, C3});
        //Canon
        addSong(new NOTE[]{C3, A3, A$3, C3, A3, A$3, C3, D3, C3, A$3, A3, G2, F2, G2, A3, F2, G2, A3, A2, A$2, C2, D2, C2, A$2, C2, F2, E2, D2, F2, E2, D2, C2, A$2, C2, A$2, A2, A$2, C2, D2, E2, F2, D2, F2, E2, F2, E2, D2, E2, C2, D2, E2, F2, G2});
        //Who
        addSong(new NOTE[]{D3, A$4, A4, C4, D3, A4, A4, F3, D3, A3, C3, A$3, A3, G2, A3, A$3, C3, A3, /*switch verse*/ A$4, A4, F3, A4, G3, F3, E3, F3, C4, D4, D4, C4, A$4, C4, F3, D4, D4, C4, A$4, C4, F3, A4, G3, E3, F3, G3, A$4, A4, A$4, A4});
        //Elmo
        addSong(new NOTE[]{A3, C3, A3, C3, /*beat*/ A3, C3, A3, C3, /*beat*/ C3, G2, G2, A3, C3, A3, C3, /*beat*/ A3, C3, A3, C3, /*beat*/ D3, G2, G2, F3, F3, E3, F3, E3, D3, G2, G2, G2, D3, C3, E3, C3, F3});
        //Never
        addSong(new NOTE[]{C3, D3, F3, C3, F3, F3, G3, C3, C3, D3, C3, G3, G3, A4, C4, A$4, A4, F3, F3, G3, E3, D3, C3, C3, C3, D3, F3, C3, F3, F3, G3, C3, C3, D3, C3, G3, G3, A4, C4, A$4, A4, F3, F3, G3, C3, G3, F3});
        //Some
        addSong(new NOTE[]{C3, G3, E3, E3, D3, C3, C3, F3, E3, E3, D3, D3, C3, C3, G3, E3, E3, D3, D3, C3, C3, A3, G2, C3, C3, G3, E3, E3, D3, D3, C3, C3, F3, E3, E3, D3, D3, C3, C3, G3, E3, E3, D3, C3, C3, D3, A3});
        //Doobeedoobeedoobah
        addSong(new NOTE[]{A3, A3, C3, A3, D3, E3, A3, A3, C3, A3, D3, E3, A3, A3, C3, A3, D3, E3, F4, F4, F4});
        //Army
        addSong(new NOTE[]{A3, A3, C3, A3, G$2, G2, F2, A3, A3, C3, A3, G$2, G2, G$2, G2, F2, A3, A3, C3, A3, G$2, G2, F2, A3, A3, C3, A3, G2, C3, D3, C3, B3});
        //Weasels
        addSong(new NOTE[]{G2, C3, C3, D3, D3, E3, G3, E3, C3, G2, C3, C3, D3, F3, E3, C3, G2, C3, C3, D3, D3, E3, G3, E3, C3, A4, D3, F3, E3, C3});
        //Sweet
        addSong(new NOTE[]{A3, A4, G2, A4, C3, C4, A3, A4, F2, F3, G2, A4, E2, E3, G2, A4});
        //Imperial
        addSong(new NOTE[]{D3, D3, D3, A$3, F3, D3, A$3, F3, D3, A4, A4, A4, A$4, E3, C$3, A3, D3, A3, D4, D3, D3, D4, C$4, C4, B4, A$4, B4, D3, G3, F3, E3, D3, C$3, D3, A3, C3, A$3, F3, D3, A3, F3, D3});
        //Wonder
        addSong(new NOTE[]{E4, D4, C4, D4, C4, D4, C4, D4, D4, C4, D4, C4, D4, C4, D4, E4, C4, E4, D4, C4, D4, C4, E4, D4, D4, C4, D4, D4, C4, D4, E4, C4, E4, G4, E4, G4, E4, A4, G4, D4, C4, D4, D4, D4, E4, C4, A4, A4, A4, C4, A4});
        //Brick
        addSong(new NOTE[]{A4, B4, C4, B4, A4, B4, C4, B4, A4, B4, C4, B4, C4, B4, C4, A4, B4, C4, B4, A4, B4, C4, B4, A4, B4, C4, B4, C4, B4, C4, C4, C4, C4, C4, C4, C4, B4, B4, B4, A4, G4, A4, C4, C4, C4, C4, C4, C4, C4, B4, B4, B4, A4, G4, A4});
        //Batman
        addSong(new NOTE[]{B4, B4, A$4, A$4, A4, A4, A$4, A$4, B4, B4, A$4, A$4, A4, A4, A$4, A$4, B3, B3, A$3, A$3});
        //Frozen
        addSong(new NOTE[]{B4, C4, A4, B4, C4, C4, B4, G3, C4, B4, A4, B4, G3, A4, B4, G3, E3, D3, /*end intro*/ D3, E3, E3, E3, E3, E3, E3, D3, C3, C3, C3, C3, D3, D3, C3, B3, A3, D3, E3, E3, E3, E3, A4, G3, E3, D3, C3, D3, E3, D3, C3, D3, E3, E3, G3, A4, G3, E3, G3, G3, G3, F3, E3, F3, E3, E3, E3, D3, C3, D3, D3, E3, D3, C3, A3, G2, G2, C3, C3, G2, G2, D3, D3, D3, C3, D3, D3, C3, D3, E3, F3, E3, E3, G2, G2, C3, C3, G2, G2, D3, D3, C3, D3, E3, F3, A3, B3, C3, G2, G2, D3, C3, A3, G2, A3, A3, B3, C3, A3, B3, C3, G2, E3, D3, C3, D3, E3, E3, F3, E3, D3, C3, G3, E3, D3, C3, C3, G3, E3, C3, C3, C3, B3, C3, G3, C3, F3, F3, E3, F3, E3, F3, F3, E3, C3});
        //Phantom
        addSong(new NOTE[]{A5, A5, G$4, G4, F$4, F4, F4, F$4, G4, G$4, A5, E4, A5, E4, G4, F4, F4, D4, G4, D4, E4, E4, A5, E4, G4, F4, F4, D4, G4, D4, E4, A4, A4, C4, E4, D4, D4, D4, G4, D4, E4, E4, A5, G4, F4, E4, D4, C4, B4, A4, G$3, F3, F3, E3, E3});
    }
}
