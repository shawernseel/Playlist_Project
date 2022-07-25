package ui;

import exceptions.PlaylistDoesNotExistError;
import model.Account;
import model.Playlist;
import model.Song;

import java.util.Scanner;

// Song playlist Application
// based on Teller app; link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class PlaylistManagerApp {
    private Account account;
    private Scanner input;
    private Playlist selectedPlaylist;

    // EFFECTS: runs the playlist manager application
    public PlaylistManagerApp() {
        runPlaylistManager();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runPlaylistManager() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            doViewPlaylists();
        } else if (command.equals("s")) {
            doSelectPlaylist();
        } else if (command.equals("r")) {
            doRemovePlaylist();
        } else if (command.equals("a")) {
            doAddPlaylist();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes account
    private void init() {
        account = new Account("Joe");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view playlists");
        System.out.println("\ts -> select a playlist");
        System.out.println("\tr -> remove a playlist");
        System.out.println("\ta -> add a playlist");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays list of playlists made
    private void doViewPlaylists() {
        System.out.println("\nPlaylists:");
        if (account.getPlaylists().size() == 0) {
            System.out.println("\tthere are no playlists!");
        } else {
            for (Playlist playlist : account.getPlaylists()) {
                System.out.println("\t" + playlist.getName());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for a selected playlist
    // returns "playlist doesn't exist" if no such playlist exists
    private void doSelectPlaylist() {
        try {
            selectedPlaylist = selectPlaylist();
            runSelectedPlaylistManager();
        } catch (PlaylistDoesNotExistError e) {
            System.out.println("Playlist doesn't exist\n");
        }
    }

    // EFFECTS: prompts user to select playlist and returns it
    private Playlist selectPlaylist() throws PlaylistDoesNotExistError {
        System.out.print("Enter playlist name: ");
        String playlistName = input.next();
        return account.returnPlaylist(playlistName);
    }


    // MODIFIES: this
    // EFFECTS: processes user input for selected playlist menu
    public void runSelectedPlaylistManager() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displaySelectedPlaylistMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("x")) {
                keepGoing = false;
            } else {
                processSelectedPlaylistCommand(command);
            }
        }
    }

    // EFFECTS: display menu for selected playlist
    private void displaySelectedPlaylistMenu() {
        System.out.println("\nPlaylist Menu for: " + selectedPlaylist.getName());
        System.out.println("Select from:");
        System.out.println("\t* -> rename playlist");
        System.out.println("\tv -> view songs in playlist");
        System.out.println("\tr -> remove a song");
        System.out.println("\ta -> add a song");
        System.out.println("\tx -> exit playlist menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processSelectedPlaylistCommand(String command) {
        if (command.equals("*")) {
            doRenamePlaylist();
        } else if (command.equals("v")) {
            doViewSongs();
        } else if (command.equals("r")) {
            doRemoveSong();
        } else if (command.equals("a")) {
            doAddSong();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: renames a playlist
    private void doRenamePlaylist() {
        System.out.print("Enter new name for playlist: ");
        String name = input.next();
        selectedPlaylist.rename(name);
    }

    // EFFECTS: displays songs in a playlist
    private void doViewSongs() {
        System.out.println("\nPlaylists:");
        if (selectedPlaylist.getSongList().size() == 0) {
            System.out.println("\tthere are no songs in this playlist!");
        } else {
            for (Song song : selectedPlaylist.getSongList()) {
                System.out.println("\t" + song.getTitle());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes song from selected playlist
    private void doRemoveSong() {
        System.out.print("Enter the song you wish to remove: ");
        String songName = input.next();
        if (selectedPlaylist.removeSongFromPlaylist(songName)) {
            System.out.println("Removed Song: " + songName + "\n");
        } else {
            System.out.println("Song is not in this playlist\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds song to selected playlist
    private void doAddSong() {
        System.out.print("Enter the song name: ");
        String songName = input.next();

        System.out.print("Enter the artist's name: ");
        String artistName = input.next();

        System.out.print("Enter the album name: ");
        String albumName = input.next();

        System.out.print("Enter the song length in seconds: ");
        int length = input.nextInt();

        Song song = new Song(songName, artistName, albumName, length);
        selectedPlaylist.addSongToPlaylist(song);
        System.out.println("Add a new song to playlist!\n");
    }

    // MODIFIES: this
    // EFFECTS: removes playlist from account
    private void doRemovePlaylist() {
        System.out.print("Enter the playlist you wish to remove: ");
        String playlistName = input.next();
        if (account.removePlaylist(playlistName)) {
            System.out.println("Removed Playlist " + playlistName + "\n");
        } else {
            System.out.println("Playlist doesn't exist\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new empty playlist to account
    private void doAddPlaylist() {
        System.out.print("Enter a name for your playlist: ");
        String playlistName = input.next();
        account.createPlaylist(playlistName);
        System.out.println("Created a new Playlist!\n");
    }


}
