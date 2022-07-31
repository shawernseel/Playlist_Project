package persistence;

import model.Playlist;
import model.Song;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// based on JsonSerializationDemo app; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkSong(String title, String artistName, String albumName, int length, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(artistName, song.getArtistName());
        assertEquals(albumName, song.getAlbumName());
        assertEquals(length, song.getLength());
    }
}
