package ru.tatarchuk.darkweather.rest.dark_sky.request;

import java.util.HashMap;
import java.util.Map;

public abstract class DarkSkyRequest {

    public Map<String, String> toQueryMap(){
        Map<String, String> map = new HashMap<>();
        onQueryMapCreate(map);
        return map;
    }

    public abstract void onQueryMapCreate(Map<String, String> map);

    public Map<String, String> toHeaderMap(){
        Map<String, String> map = new HashMap<>();
        onHeaderMapCreate(map);
        return map;
    }

    public abstract void onHeaderMapCreate(Map<String, String> map);
}
