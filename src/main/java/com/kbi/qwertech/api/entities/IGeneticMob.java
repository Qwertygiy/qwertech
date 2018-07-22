package com.kbi.qwertech.api.entities;

public interface IGeneticMob {
    short getSpeciesID();
    short getSubtypeID();
    short getSize();
    short getStrength();
    short getStamina();
    short getSmart();
    short getSnarl();
    short getMutable();
    int getPrimaryColor();
    int getSecondaryColor();
    short getFertility();
    short getMaturity();

    void setSpeciesID(short species);
    void setSubtypeID(short subtype);
    void setSize(short size);
    void setStrength(short strength);
    void setStamina(short stamina);
    void setSmart(short smart);
    void setSnarl(short snarl);
    void setMutable(short mutable);
    void setPrimaryColor(int color);
    void setSecondaryColor(int color);
    void setFertility(short fertility);
    void setMaturity(short maturity);
}
