package com.example.app3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import java.util.Arrays;
import java.util.List;

public class AlbumViewModel extends ViewModel {
    private final SavedStateHandle state;
    public final MutableLiveData<String> title, artist, composer;
    public final MutableLiveData<Boolean> classical;
    private final MutableLiveData<Integer> selected;
    private final MutableLiveData<List<String>> titles = new MutableLiveData<>();
    public AlbumViewModel(SavedStateHandle state) {
        this.state=state; restoreAlbums();
        title=state.getLiveData("title", ""); artist=state.getLiveData("artist", ""); composer=state.getLiveData("composer", "");
        classical=state.getLiveData("classical", false); selected=state.getLiveData("selected",0);
        if (!state.contains("initialized")) { loadDraft(); state.set("initialized",true); }
        refreshTitles();
    }
    public LiveData<List<String>> getTitles() { return titles; }
    public LiveData<Integer> getSelected() { return selected; }
    public void select(int position) {
        if (position<0 || position>=Album.size()) return;
        selected.setValue(position); loadDraft();
    }
    public void apply() {
        Album a=Album.gib(selected.getValue());
        a.setTitle(value(title)); a.setArtist(value(artist)); a.setComposer(value(composer)); a.setClassical(Boolean.TRUE.equals(classical.getValue()));
        saveAlbums(); refreshTitles();
    }
    public void cancel() { loadDraft(); }
    private static String value(MutableLiveData<String> field) { return field.getValue()==null ? "" : field.getValue(); }
    private void loadDraft() {
        Album a=Album.gib(selected.getValue()); title.setValue(a.getTitle()); artist.setValue(a.getArtist());
        composer.setValue(a.getComposer()); classical.setValue(a.isClassical());
    }
    private void refreshTitles() { titles.setValue(Arrays.asList(Album.gibAlleNamen())); }
    private void saveAlbums() {
        String[] data=new String[Album.size()*4];
        for (int i=0; i<Album.size(); i++) {
            Album a=Album.gib(i); data[i*4]=a.getTitle(); data[i*4+1]=a.getArtist(); data[i*4+2]=a.getComposer(); data[i*4+3]=a.isClassical().toString();
        }
        state.set("albums",data);
    }
    private void restoreAlbums() {
        String[] data=state.get("albums"); if (data==null || data.length!=Album.size()*4) return;
        for (int i=0; i<Album.size(); i++) Album.addAlbumsRow(i,data[i*4],data[i*4+1],Boolean.parseBoolean(data[i*4+3]),data[i*4+2]);
    }
}
