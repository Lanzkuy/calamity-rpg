package engine;

import data.DataSaver;
import data.DataStorage;

public class Map {
    private String mapID;
    private String mapName;
    private String status;

    public Map(String mapID, String mapName, String status){
        setMapID(mapID);
        setMapName(mapName);
        setStatus(status);
    }

    //To update map status
    public static void openNewMap(String mapID){
        try{
            for (int i = 0; i< DataStorage.LM.size(); i++){
                if(DataStorage.LM.get(i).getMapID().equals(mapID)){
                    int newMapIndex = i+1;
                    Map m = DataStorage.LM.get(newMapIndex);
                    DataStorage.LM.set(newMapIndex, new Map(m.getMapID(), m.getMapName(), "Unlock"));
                    DataSaver.saveMapData();
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in updateItem: " + ex);
        }
    }

    //Getter Setter
    public String getMapID() {
        return mapID;
    }

    public void setMapID(String mapID) {
        this.mapID = mapID;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
