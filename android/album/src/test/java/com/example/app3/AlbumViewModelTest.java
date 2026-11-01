package com.example.app3;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.SavedStateHandle;
import org.junit.*;
import static org.junit.Assert.*;
public class AlbumViewModelTest {
    @Rule public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Before public void reset() { Album.albumDataSet(); }
    @Test public void selectionLoadsAllFields() {
        AlbumViewModel vm=new AlbumViewModel(new SavedStateHandle()); vm.select(3);
        assertEquals("Symphony No.5",vm.title.getValue()); assertEquals("CBSO",vm.artist.getValue());
        assertEquals("Sibelius",vm.composer.getValue()); assertTrue(vm.classical.getValue());
    }
    @Test public void applyUpdatesModelAndObservedList() {
        AlbumViewModel vm=new AlbumViewModel(new SavedStateHandle()); vm.title.setValue("Neu");
        assertEquals("HQ",Album.gib(0).getTitle()); vm.apply();
        assertEquals("Neu",Album.gib(0).getTitle()); assertEquals("Neu",vm.getTitles().getValue().get(0));
    }
    @Test public void cancelRestoresEveryField() {
        AlbumViewModel vm=new AlbumViewModel(new SavedStateHandle());
        vm.title.setValue("Entwurf"); vm.artist.setValue("Entwurf"); vm.composer.setValue("Entwurf"); vm.classical.setValue(true); vm.cancel();
        assertEquals("HQ",vm.title.getValue()); assertEquals("Roy Harper",vm.artist.getValue());
        assertEquals("",vm.composer.getValue()); assertFalse(vm.classical.getValue());
    }
    @Test public void switchingDiscardsUnappliedDraft() {
        AlbumViewModel vm=new AlbumViewModel(new SavedStateHandle()); vm.title.setValue("Entwurf"); vm.select(1); vm.select(0);
        assertEquals("HQ",vm.title.getValue());
    }
    @Test public void savedStateRestoresModelAndDraft() {
        SavedStateHandle state=new SavedStateHandle(); AlbumViewModel vm=new AlbumViewModel(state);
        vm.title.setValue("Gespeichert"); vm.apply(); vm.title.setValue("Entwurf");
        Album.albumDataSet(); AlbumViewModel restored=new AlbumViewModel(state);
        assertEquals("Gespeichert",Album.gib(0).getTitle()); assertEquals("Entwurf",restored.title.getValue());
    }
}
