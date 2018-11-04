package com.kbi.qwertech.api.entities;

import java.util.List;

public interface Taggable {
    Object getTag(String tag);
    Taggable addTag(String tag, Object obby);
    boolean hasTag(String tag);
    List<String> getTags();
}
