package persistence;

import model.Account;
import model.Playlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// based on JsonSerializationDemo app; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account account = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account account = reader.read();
            assertEquals("joeAccount", account.getAccountName());
            assertEquals(0, account.getPlaylists().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyPlaylistAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylistAccount.json");
        try {
            Account account = reader.read();
            assertEquals("joeAccount", account.getAccountName());
            ArrayList<Playlist> playlists = account.getPlaylists();
            assertEquals(2, playlists.size());
            assertEquals("p1", playlists.get(0).getName());
            assertEquals(0, playlists.get(0).getSongList().size());
            assertEquals("p2", playlists.get(1).getName());
            assertEquals(0, playlists.get(1).getSongList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccount.json");
        try {
            Account account = reader.read();
            assertEquals("joeAccount", account.getAccountName());
            ArrayList<Playlist> playlists = account.getPlaylists();
            assertEquals(2, playlists.size());
            assertEquals("p1", playlists.get(0).getName());
            assertEquals(1, playlists.get(0).getSongList().size());
            checkSong("Hey Ya!", "Outkast",
                    "Speakerboxxx/The Love Below", 235,
                    playlists.get(0).getSongList().get(0));

            assertEquals("p2", playlists.get(1).getName());
            assertEquals(2, playlists.get(1).getSongList().size());
            checkSong("Wouldn't It Be Nice",
                    "The Beach Boys", "Pet Sounds", 145,
                    playlists.get(1).getSongList().get(0));
            checkSong("The Spins", "Mac Miller", "K.I.D.S.", 203,
                    playlists.get(1).getSongList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
