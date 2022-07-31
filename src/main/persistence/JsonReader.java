package persistence;

import model.Account;
import model.Playlist;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// based on JsonSerializationDemo app; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String accountName = jsonObject.getString("accountName");
        Account account = new Account(accountName);
        addPlaylists(account, jsonObject);
        return account;
    }

    // MODIFIES: account
    // EFFECTS: parses Playlists from JSON object and adds them to account
    private void addPlaylists(Account account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("playlists");
        for (Object json : jsonArray) {
            JSONObject nextPlaylist = (JSONObject) json;
            addPlaylist(account, nextPlaylist);
        }
    }

    // EFFECTS: parses Playlist from JSON object and returns it
    private void addPlaylist(Account account, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Playlist playlist = new Playlist(name);
        addSongList(playlist, jsonObject);
        account.addPlaylist(playlist);
    }

    // MODIFIES: playlist
    // EFFECTS: parses songList from JSON object and adds them to Playlist
    private void addSongList(Playlist playlist, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songList");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(playlist, nextSong);
        }
    }

    // MODIFIES: playlist
    // EFFECTS: parses Song from JSON object and adds it to songList
    private void addSong(Playlist playlist, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artistName = jsonObject.getString("artistName");
        String albumName = jsonObject.getString("albumName");
        int length = jsonObject.getInt("length");
        Song song = new Song(title, artistName, albumName, length);
        playlist.addSongToPlaylist(song);
    }
}
