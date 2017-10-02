package com.example.erick.aondeir;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Erick on 16/09/2017.
 */

public class DocList {

    private List<Doc> docs;
    private Connection connection = new Connection();
    

    public void getDocs(){
        try {
            docs = new ArrayList(new Connection().listDoc());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> returnAllPost_Title(){
        List<String> post_titles = new LinkedList<String>();
        // ok!
        getDocs();
        for(Doc doc:docs){
            post_titles.add(doc.getPost_title());
        }
        return post_titles;
    }

    public String returnPlaceByTitle(String title){
        String found = new String();
        for(Doc doc: docs){
            if(doc.getPost_title().equals(title)) found = doc.getPlace_name();
        }
        return found;
    }

    public String returnNeighborhoodByTitle(String title){
        String found = new String();
        for(Doc doc: docs){
            if(doc.getPost_title().equals(title)) found = doc.getPlace_neighborhood();
        }
        return found;
    }

    /*
    public String returnChords(String band, String musicName){
        for(Music music: musics){
            if(music.getBand().equals(band) && music.getMusicName().equals(musicName)) return music.getChords();
        }
        return "Sorry...";
    }*/
}
