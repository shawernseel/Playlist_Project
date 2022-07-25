package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    private Song heyYa;
    private Song wouldntItBeNice;
    private Song theSpins;

    private Playlist testPlaylist;

    @BeforeEach
    public void beforeEach() {
        heyYa = new Song("Hey Ya!", "Outkast",
                "Speakerboxxx/The Love Below", 235);
        wouldntItBeNice = new Song("Wouldn't It Be Nice",
                "The Beach Boys", "Pet Sounds", 145);
        theSpins = new Song("The Spins", "Mac Miller", "K.I.D.S.", 203);

        testPlaylist = new Playlist("Test Playlist");
    }

    @Test
    public void testPlayList() {
        assertEquals(0, testPlaylist.getSongList().size());
    }

    @Test
    public void testRename() {
        testPlaylist.rename("Empty Playlist");
        assertEquals("Empty Playlist", testPlaylist.getName());

        testPlaylist.rename("BANGERS");
        assertEquals("BANGERS", testPlaylist.getName());
    }

    @Test
    public void testAddSongToPlaylist() {
        testPlaylist.addSongToPlaylist(heyYa);
        assertEquals(heyYa, testPlaylist.getSongList().get(0));
        assertEquals(1, testPlaylist.getSongList().size());

        testPlaylist.addSongToPlaylist(wouldntItBeNice);
        assertEquals(heyYa, testPlaylist.getSongList().get(0));
        assertEquals(wouldntItBeNice, testPlaylist.getSongList().get(1));
        assertEquals(2, testPlaylist.getSongList().size());
    }

    // REQUIRES: list is not empty, song only appears once in playlist
    // MODIFIES: this
    // EFFECTS: removes a given song from the playlist
    @Test
    public void testRemoveSongFromPlaylist() {
            assertFalse(testPlaylist.removeSongFromPlaylist("Hey Ya!"));

            testPlaylist.addSongToPlaylist(heyYa);
            assertTrue(testPlaylist.removeSongFromPlaylist("Hey Ya!"));
            assertEquals(0, testPlaylist.getSongList().size());

            testPlaylist.addSongToPlaylist(heyYa);
            testPlaylist.addSongToPlaylist(wouldntItBeNice);
            testPlaylist.addSongToPlaylist(theSpins);

            assertTrue(testPlaylist.removeSongFromPlaylist("Wouldn't It Be Nice"));
            assertEquals(heyYa, testPlaylist.getSongList().get(0));
            assertEquals(theSpins, testPlaylist.getSongList().get(1));
            assertEquals(2, testPlaylist.getSongList().size());

            assertTrue(testPlaylist.removeSongFromPlaylist("Hey Ya!"));
            assertEquals(theSpins, testPlaylist.getSongList().get(0));
            assertEquals(1, testPlaylist.getSongList().size());
        }
}