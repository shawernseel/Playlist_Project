package model;

import java.util.ArrayList;

// Represents a playlist with a name, and a list of songs
public class Playlist {
    private String name;
    private ArrayList<Song> songList;

    // EFFECTS: create a playlist with a name and list of songs
    public Playlist(String name) {
        this.name = name;
        songList = new ArrayList<>();
    }

    // EFFECTS: returns name of playlist
    public String getName() {
        return name;
    }

    // EFFECTS: returns list of songs in playlist
    public ArrayList<Song> getSongList() {
        return songList;
    }

    // MODIFIES: this
    // EFFECTS: renames the playlist
    public void rename(String name) {
        this.name = name;
    }


    // MODIFIES: this
    // EFFECTS: adds a song to playlist (at the end)
    public void addSongToPlaylist(Song song) {
        songList.add(song);
    }

    // REQUIRES: song name only appears once in playlist
    // MODIFIES: this
    // EFFECTS: if song name is in playlist returns true and removes song from playlist
    // else return false
    public boolean removeSongFromPlaylist(String songName) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getTitle().equals(songName)) {
                songList.remove(i);
                return true;
            }
        }
        return false;
    }




}
