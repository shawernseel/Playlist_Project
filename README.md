# Playlist Manager Project

## A way to organise songs

This project will be a playlist manager where users will be able to **create and delete playlists**.
Users will be able to **add and remove songs from playlists** that they create.
This project is made for anyone who enjoys listening to music and wants to organize songs they like into playlists.
This project is of interest to me because I love music and it’ll be fun to create a way to manage and organize the songs I enjoy listening to.


## User Stories
- As a user, I want to be able to name and create a playlist
- As a user, I want to be able to add a song to a playlist
- As a user, I want to be able to remove a song from a playlist
- As a user, I want to be able to remove a playlist
- As a user, I want to be able to update a playlist name

- As a user, I want to be able to save all my playlists to file
- As a user, I want to be able to load all my playlists from file

## Phase 4: Task 2
- As a user, I want to be able to add a song to a playlist
- As a user, I want to be able to remove a song from a playlist

## Phase 4: Task 3
NOTE: My UML diagram does not include the PlaylistManagerApp class that was the console application
(I just wanted to leave it in for reference, it is not actually used)
Changes I would make:
- I would reduce the coupling so that PlaylistAppGUI is only dependent on Account and not a playlist and an account
- I would also increase cohesion by abstracting ActionListener() using lambda
