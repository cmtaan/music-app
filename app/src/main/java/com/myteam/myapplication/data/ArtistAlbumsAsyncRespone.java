package com.myteam.myapplication.data;

import com.myteam.myapplication.model.Album;

import java.util.ArrayList;

public interface ArtistAlbumsAsyncRespone {
    void processFinished(ArrayList<Album> albums);
}
