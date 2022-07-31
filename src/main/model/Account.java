package model;

import exceptions.PlaylistDoesNotExistError;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents an account with a name that holds a list of playlists
public class Account implements Writable {
    private final String accountName;
    private ArrayList<Playlist> playlists;

    public Account(String accountName) {
        this.accountName = accountName;
        playlists = new ArrayList<>();
    }

    // EFFECTS: returns list of currently made playlist
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    // EFFECTS: returns list of currently made playlist
    public String getAccountName() {
        return accountName;
    }

    // MODIFIES: this
    // EFFECTS: creates a new playlist to the list of playlists
    public void createPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
    }

    // MODIFIES: this
    // EFFECTS: adds a playlist to the list of playlists
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    // REQUIRES: playlist name only appears once in account
    // EFFECTS: if playlist name is in account returns playlist
    // else throw PlaylistDoesNotExistError
    public Playlist returnPlaylist(String playlistName) throws PlaylistDoesNotExistError {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(playlistName)) {
                return playlist;
            }
        }
        throw new PlaylistDoesNotExistError();
    }


    // REQUIRES: playlist name only appears once in account
    // MODIFIES: this
    // EFFECTS: if playlist name is in account returns true and removes playlist
    // else return false
    public boolean removePlaylist(String playlistName) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getName().equals(playlistName)) {
                playlists.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accountName", accountName);
        json.put("playlists", playlistsToJson());
        return json;
    }

    // EFFECTS: returns playlists in this account as a JSON array
    private JSONArray playlistsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Playlist p : playlists) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
