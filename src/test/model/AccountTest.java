package model;

import exceptions.PlaylistDoesNotExistError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    private Account joe;

    @BeforeEach
    public void beforeEach() {
        joe = new Account("Joe");
    }

    @Test
    public void testAccount() {
        assertEquals(0, joe.getPlaylists().size());
    }

    @Test
    public void testCreatePlaylist() {
        joe.createPlaylist("New Playlist");
        assertEquals("New Playlist", joe.getPlaylists().get(0).getName());
        assertEquals(1, joe.getPlaylists().size());

        joe.createPlaylist("2nd Playlist");
        assertEquals("New Playlist", joe.getPlaylists().get(0).getName());
        assertEquals("2nd Playlist", joe.getPlaylists().get(1).getName());
        assertEquals(2, joe.getPlaylists().size());
    }

    @Test
    public void testReturnPlaylistDoesNotExist() {
        try {
            joe.returnPlaylist("Doesn't Exist");
        } catch (PlaylistDoesNotExistError e){
            // do nothing
        }
    }

    @Test
    public void testReturnPlaylistDoesExist() {
        joe.createPlaylist("Test");
        joe.createPlaylist("Test2");

        try {
            assertEquals( "Test", joe.returnPlaylist("Test").getName());
        } catch (PlaylistDoesNotExistError e) {
            fail("Got PlaylistDoesNotExistError");
        }
        assertEquals("Test", joe.getPlaylists().get(0).getName());
        assertEquals("Test2", joe.getPlaylists().get(1).getName());
        assertEquals(2, joe.getPlaylists().size());

        try {
            assertEquals( "Test2", joe.returnPlaylist("Test2").getName());
        } catch (PlaylistDoesNotExistError e) {
            fail("Got PlaylistDoesNotExistError");
        }
        assertEquals("Test", joe.getPlaylists().get(0).getName());
        assertEquals("Test2", joe.getPlaylists().get(1).getName());
        assertEquals(2, joe.getPlaylists().size());
    }

    @Test
    public void testRemovePlaylist() {
        assertFalse(joe.removePlaylist("FAIL"));

        joe.createPlaylist("New Playlist");
        joe.createPlaylist("2nd Playlist");

        assertTrue(joe.removePlaylist("New Playlist"));
        assertEquals("2nd Playlist", joe.getPlaylists().get(0).getName());
        assertEquals(1, joe.getPlaylists().size());

        assertFalse(joe.removePlaylist("FAIL"));

        assertTrue(joe.removePlaylist("2nd Playlist"));
        assertEquals(0, joe.getPlaylists().size());
    }
}
