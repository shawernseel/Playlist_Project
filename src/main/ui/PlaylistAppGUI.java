package ui;

import model.Account;
import model.Event;
import model.EventLog;
import model.Playlist;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// GUI for Playlist Application
public class PlaylistAppGUI extends JFrame {

//    PlaylistAppGUI jFrame = new PlaylistAppGUI();

    private static final int WIDTH = 700;
    private static final int HEIGHT = 750;
    private static final String JSON_STORE = "./data/account.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Account account;
    private Playlist playlist;

    private ImageIcon musicBanner;
    private JPanel panel;
    private JPanel songsPanel;
    private JPanel changingPanel;
    private JPanel removeSongPanel;
    private JPanel addSongPanel;
    private JPanel loadPanel;

    private JLabel label;
    private JTextField textField;
    private JButton button;

    private JLabel songNameLabel;
    private JTextField songNameField;
    private JLabel artistNameLabel;
    private JTextField artistNameField;
    private JLabel albumNameLabel;
    private JTextField albumNameField;
    private JLabel lengthLabel;
    private JTextField lengthField;

    // EFFECTS: creates a new playlist application
    public PlaylistAppGUI() {
        super("Playlist Application");
        account = new Account("BlueDudeDaniel");
        playlist = new Playlist("SUMMER VIBES");
        account.addPlaylist(playlist);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        panel = new JPanel();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initializes GUI
    public void initializeGraphics() {
        setWindow();
        createMainMenu();
        createChangeableMenu();
        songsPanel = new JPanel();
        updateSongListPanel();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates main menu panels
    public void createMainMenu() {
        createMainDisplayImage();
        createMainDisplayText();
        createAddSongButton();
        createRemoveSongButton();
        createRenamePlaylistButton();
        createLoadButton();
        createSaveButton();
    }

    // inspired by C3-LectureLabSolution
    // MODIFIES: this
    // EFFECTS: creates image for main menu
    public void createMainDisplayImage() {
        String sep = System.getProperty("file.separator");
        musicBanner = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "musicBanner.png");
        JLabel musicBannerLabel = new JLabel(musicBanner);
        panel.add(musicBannerLabel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates the main display text for the main panel
    public void createMainDisplayText() {
        JLabel mainText = new JLabel("Welcome to the Playlist App! Please select one of the menu options below: ");
        mainText.setFont(new Font("New York Times", Font.PLAIN, 18));
        panel.add(mainText);
    }

    // MODIFIES: this
    // EFFECTS: creates the add song button and implements it
    public void createAddSongButton() {
        button = new JButton("Add Song");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAddSongMenu();
            }
        });
        panel.add(button);
        add(panel, BorderLayout.CENTER);
    }

    // REQUIRES: lengthField.getText() is an int
    // MODIFIES: this
    // EFFECTS: sets up the menu for adding a song to playlist
    private void loadAddSongMenu() {
        addSongPanel = new JPanel(new GridLayout(0, 1));
        createAddSongTextInputFields();
        button = new JButton("Add Song");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String songName = songNameField.getText();
                String artistName = artistNameField.getText();
                String albumName = albumNameField.getText();
                int length = Integer.parseInt(lengthField.getText());
                addSong(new Song(songName, artistName, albumName, length));
                updateSongListPanel();
            }
        });

        addTextInputFieldsToPanel();
        addSongPanel.add(button);
        setChangingPanel(addSongPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates text input fields for add menu
    public void createAddSongTextInputFields() {
        songNameLabel = new JLabel("Enter song name: ");
        songNameField = new JTextField(20);
        artistNameLabel = new JLabel("Enter artist name: ");
        artistNameField = new JTextField(20);
        albumNameLabel = new JLabel("Enter album name: ");
        albumNameField = new JTextField(20);
        lengthLabel = new JLabel("Enter song length: ");
        lengthField = new JTextField(20);
    }

    // MODIFIES: this
    // EFFECTS: adds text input fields to panel
    public void addTextInputFieldsToPanel() {
        addSongPanel.add(songNameLabel);
        addSongPanel.add(songNameField);
        addSongPanel.add(albumNameLabel);
        addSongPanel.add(albumNameField);
        addSongPanel.add(artistNameLabel);
        addSongPanel.add(artistNameField);
        addSongPanel.add(lengthLabel);
        addSongPanel.add(lengthField);
    }

    // MODIFIES: playlist
    // EFFECTS: adds song to playlist
    public void addSong(Song song) {
        account.getPlaylists().get(0).addSongToPlaylist(song);
    }

    // MODIFIES: this
    // EFFECTS: creates the remove song button and implements it
    public void createRemoveSongButton() {
        button = new JButton("Remove Song");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRemoveSongMenu();
            }
        });
        panel.add(button);
        add(panel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel for removing a song from playlist
    public void loadRemoveSongMenu() {
        removeSongPanel = new JPanel();
        label = new JLabel("Enter song name:");
        textField = new JTextField(20);
        button = new JButton("Remove Song");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String song = textField.getText();
                removeSong(song);
                updateSongListPanel();
            }
        });

        removeSongPanel.add(label);
        removeSongPanel.add(textField);
        removeSongPanel.add(button);
        setChangingPanel(removeSongPanel);
    }

    // MODIFIES: playlist
    // EFFECTS: removes song from playlist
    public void removeSong(String song) {
        account.getPlaylists().get(0).removeSongFromPlaylist(song);
    }

    // MODIFIES: this
    // EFFECTS: creates rename playlist button and implements it
    public void createRenamePlaylistButton() {
        button = new JButton("Rename Playlist");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRenamePlaylistMenu();
            }
        });
        panel.add(button);
        add(panel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel for renaming a playlist
    public void loadRenamePlaylistMenu() {
        removeSongPanel = new JPanel();
        label = new JLabel("Enter new playlist name:");
        textField = new JTextField(20);
        button = new JButton("Rename Playlist");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                renamePlaylist(name);
                updateSongListPanel();
            }
        });

        removeSongPanel.add(label);
        removeSongPanel.add(textField);
        removeSongPanel.add(button);
        setChangingPanel(removeSongPanel);
    }

    // MODIFIES: playlist
    // EFFECTS: renames playlist
    public void renamePlaylist(String name) {
        account.getPlaylists().get(0).rename(name);
    }

    // MODIFIES: this
    // EFFECTS: sets up the window for playlist application
    public void setWindow() {
        printLogOnQuit();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: removes all components from the song list panel and displays all songs
    //          if there are no songs it will display a suitable message
    public void updateSongListPanel() {
        if (songsPanel != null) {
            songsPanel.removeAll();
        }
        JLabel temp = new JLabel("Songs in playlist " + account.getPlaylists().get(0).getName() + ": ");
        songsPanel.add(temp);
        if (account.getPlaylists().get(0).getSongList().size() == 0) {
            JLabel noSongs = new JLabel("There are no songs in playlist yet!");
            songsPanel.add(noSongs);
        } else {
            for (Song song : account.getPlaylists().get(0).getSongList()) {
                JLabel item = new JLabel(song.getTitle());
                songsPanel.add(item);
            }
        }
        songsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));
        songsPanel.setBackground(Color.PINK);
        add(songsPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: creates the panel where the GUI changes when buttons are clicked
    public void createChangeableMenu() {
        changingPanel = new JPanel();
        changingPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));
        JTextField startingPanelText = new JTextField("Please select an option above");
        changingPanel.add(startingPanelText);
        changingPanel.setBackground(Color.BLUE);
        panel.add(changingPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets the changingPanel to a given panel and updates it
    public void setChangingPanel(JPanel panel) {
        changingPanel.removeAll();
        changingPanel.add(panel);
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: sets up the button for loading playlist
    public void createLoadButton() {
        button = new JButton("Load Playlist");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPlaylist();
            }
        });
        panel.add(button);
        add(panel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: loads account from file
    public void loadPlaylist() {
        try {
            account = jsonReader.read();
            loadedMessage();
            updateSongListPanel();
        } catch (IOException e) {
            notLoadedMessage();
            updateSongListPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates message that account is loaded and displays in changingPanel
    public void loadedMessage() {
        loadPanel = new JPanel();
        JLabel text = new JLabel("Loaded " + account.getAccountName() + " from " + JSON_STORE);
        text.setFont(new Font("New York Times", Font.PLAIN, 18));
        loadPanel.add(text);
        loadPanel.setBackground(Color.GREEN);
        setChangingPanel(loadPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates message that account is not loaded and displays in changingPanel
    public void notLoadedMessage() {
        loadPanel = new JPanel();
        JLabel text = new JLabel("Unable to read from file: " + JSON_STORE);
        text.setFont(new Font("New York Times", Font.PLAIN, 18));
        loadPanel.add(text);
        loadPanel.setBackground(Color.RED);
        setChangingPanel(loadPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up the button for saving playlist
    public void createSaveButton() {
        button = new JButton("Save Playlist");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlaylist();
            }
        });
        panel.add(button);
        add(panel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: saves account to file
    public void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(account);
            jsonWriter.close();
            savedMessage();
        } catch (FileNotFoundException e) {
            notSavedMessage();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates message that account is loaded and displays in changingPanel
    public void savedMessage() {
        loadPanel = new JPanel();
        JLabel text = new JLabel("Saved " + account.getAccountName() + " to " + JSON_STORE);
        text.setFont(new Font("New York Times", Font.PLAIN, 18));
        loadPanel.add(text);
        loadPanel.setBackground(Color.GREEN);
        setChangingPanel(loadPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates message that account is not loaded and displays in changingPanel
    public void notSavedMessage() {
        loadPanel = new JPanel();
        JLabel text = new JLabel("Unable to write to file: " + JSON_STORE);
        text.setFont(new Font("New York Times", Font.PLAIN, 18));
        loadPanel.add(text);
        loadPanel.setBackground(Color.RED);
        setChangingPanel(loadPanel);
    }

    // EFFECTS: prints EventLog on exit
    public void printLogOnQuit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event : eventLog) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }
        });
    }

}
