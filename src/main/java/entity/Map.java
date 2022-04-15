package entity;

public class Map {
    private String mapID;
    private String mapName;
    private String status;

    public Map(String mapID, String mapName, String status){
        setMapID(mapID);
        setMapName(mapName);
        setStatus(status);
    }

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
