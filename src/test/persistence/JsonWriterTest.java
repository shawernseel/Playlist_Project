package persistence;

import model.Account;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// based on JsonSerializationDemo app; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Account account = new Account("joeAccount");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccount() {
        try {
            Account account = new Account("joeAccount");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            account = reader.read();
            assertEquals("joeAccount", account.getAccountName());
            assertEquals(0, account.getPlaylists().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try {
            Account account = new Account("a1");
            Playlist p1 = new Playlist("p1");
            p1.addSongToPlaylist(new Song("s1", "n1", "a1", 1));
            p1.addSongToPlaylist(new Song("s2", "n2", "a2", 2));
            Playlist p2 = new Playlist("p2");
            p2.addSongToPlaylist(new Song("s3", "n3", "a3", 3));
            account.addPlaylist(p1);
            account.addPlaylist(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            account = reader.read();
            assertEquals("a1", account.getAccountName());
            ArrayList<Playlist> playlists = account.getPlaylists();
            assertEquals(2, playlists.size());
            assertEquals("p1", account.getPlaylists().get(0).getName());
            assertEquals(2, playlists.get(0).getSongList().size());
            checkSong("s1", "n1", "a1", 1, playlists.get(0).getSongList().get(0));
            checkSong("s2", "n2", "a2", 2, playlists.get(0).getSongList().get(1));

            assertEquals("p2", playlists.get(1).getName());
            assertEquals(1, playlists.get(1).getSongList().size());
            checkSong("s3", "n3", "a3", 3, playlists.get(1).getSongList().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
