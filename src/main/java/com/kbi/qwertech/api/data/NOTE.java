package com.kbi.qwertech.api.data;

public enum NOTE {
    A2, A3, A4, A5,
    A$2, A$3, A$4, A$5,
    B2, B3, B4,B5,
    C2, C3, C4, C5,
    C$2, C$3, C$4, C$5,
    D2, D3, D4, D5,
    D$2, D$3, D$4, D$5,
    E2, E3, E4, E5,
    F2, F3, F4, F5,
    F$2, F$3, F$4, F$5,
    G2, G3, G4, G5,
    G$2, G$3, G$4, G$5;

    private float note;

    public float get()
    {
        return note;
    }

    public void set(float notum)
    {
        note = notum;
    }

    static {
        A2.set(1F);
        A$2.set(1.05946F);
        B2.set(1.12246F);
        C2.set(1.18921F);
        C$2.set(1.25992F);
        D2.set(1.33484F);
        D$2.set(1.41421F);
        E2.set(1.49831F);
        F2.set(1.58740F);
        F$2.set(1.68179F);
        G2.set(1.78180F);
        G$2.set(1.88775F);
        A3.set(2F);
        A4.set(4F);
        A5.set(8F);
        A$3.set(A$2.get() * 2F);
        A$4.set(A$3.get() * 2F);
        A$5.set(A$4.get() * 2F);
        B3.set(B2.get() * 2F);
        B4.set(B3.get() * 2F);
        B5.set(B4.get() * 2F);
        C3.set(C2.get() * 2F);
        C4.set(C3.get() * 2F);
        C5.set(C4.get() * 2F);
        C$3.set(C$2.get() * 2F);
        C$4.set(C$3.get() * 2F);
        C$5.set(C$4.get() * 2F);
        D3.set(D2.get() * 2F);
        D4.set(D3.get() * 2F);
        D5.set(D4.get() * 2F);
        D$3.set(D$2.get() * 2F);
        D$4.set(D$3.get() * 2F);
        D$5.set(D$4.get() * 2F);
        E3.set(E2.get() * 2F);
        E4.set(E3.get() * 2F);
        E5.set(E4.get() * 2F);
        F3.set(F2.get() * 2F);
        F4.set(F3.get() * 2F);
        F5.set(F4.get() * 2F);
        F$3.set(F$2.get() * 2F);
        F$4.set(F$3.get() * 2F);
        F$5.set(F$4.get() * 2F);
        G3.set(G2.get() * 2F);
        G4.set(G3.get() * 2F);
        G5.set(G4.get() * 2F);
        G$3.set(G$2.get() * 2F);
        G$4.set(G$3.get() * 2F);
        G$5.set(G$4.get() * 2F);
    }
}
