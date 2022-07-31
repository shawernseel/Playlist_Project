package model;

// Represents a song with a title, name of the artist, name of the album it's from
// and the length of the song in seconds
public class Song {
    private final String title;
    private final String artistName;
    private final String albumName;
    private final int length;

    // REQUIRES: length > 0
    // EFFECTS: creates a song with a title, name of the artist, name of the album it
    // is from and length of song in seconds
    public Song(String title, String artistName, String albumName, int length) {
        this.title = title;
        this.artistName = artistName;
        this.albumName = albumName;
        this.length = length;
    }

    // EFFECTS: returns title of song
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns name of artist
    public String getArtistName() {
        return artistName;
    }

    // EFFECTS: returns name of album
    public String getAlbumName() {
        return albumName;
    }

    // EFFECTS: returns length of song in seconds
    public int getLength() {
        return length;
    }

}
