package com.myteam.myapplication.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myteam.myapplication.R;
import com.myteam.myapplication.adapter.UserPlaylistAdapter;
import com.myteam.myapplication.data.UserPlaylistAsyncResponse;
import com.myteam.myapplication.data.UserPlaylistData;
import com.myteam.myapplication.model.Playlist;
import com.myteam.myapplication.model.User;

import java.util.ArrayList;

public class UserPlaylistFragment extends Fragment {
    View view;
    ListView listViewPlaylist;
    ArrayList<Playlist> playlists;
    UserPlaylistAdapter userPlaylistAdapter;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_playlist, container, false);
        listViewPlaylist = view.findViewById(R.id.listview_user_playlist);
        getUser();
        getUserPlaylist(user);
        return view;
    }

    public void getUserPlaylist(User user) {
        playlists = new UserPlaylistData().getPlaylistsbyId(user, new UserPlaylistAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Playlist> playlists) {

                userPlaylistAdapter = new UserPlaylistAdapter(getActivity(), R.layout.user_playlist_item, playlists);
                listViewPlaylist.setAdapter(userPlaylistAdapter);
            }
        });
    }

    private void getUser() {
        user = new User();
        SharedPreferences sharedPref = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        user.setId(sharedPref.getInt("user_id", -1));
        user.setName(sharedPref.getString("user_name", ""));
        user.setEmail(sharedPref.getString("user_email", ""));
    }

}
