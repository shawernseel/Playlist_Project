package ui;

import model.Account;
import model.Playlist;

import java.util.Scanner;

// Song playlist Application
// based on Teller app; link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class PlaylistManagerApp {
    private Account account;
    private Playlist selectedPlaylist;
    private Scanner input;

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
            doView();
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
    // EFFECTS: initializes accounts
    private void init() {

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

    private void doView() {

    }

    private void doSelectPlaylist() {

    }

    private void doRemovePlaylist() {

    }

    private void doAddPlaylist() {

    }


}
