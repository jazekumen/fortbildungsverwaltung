package com.example.app3;

import com.example.app3.databinding.FragmentAlbumBinding;
public class AlbumFragment extends androidx.fragment.app.Fragment {
    private FragmentAlbumBinding binding;
    public AlbumFragment() { super(R.layout.fragment_album); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        binding=FragmentAlbumBinding.bind(view);
        AlbumViewModel vm=new androidx.lifecycle.ViewModelProvider(this).get(AlbumViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner()); binding.setVm(vm);
        android.widget.ArrayAdapter<String> adapter=new android.widget.ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_activated_1,new java.util.ArrayList<>());
        binding.albums.setAdapter(adapter);
        binding.albums.setOnItemClickListener((parent,row,position,id)->vm.select(position));
        vm.getTitles().observe(getViewLifecycleOwner(),titles->{
            adapter.clear(); adapter.addAll(titles); adapter.notifyDataSetChanged();
            binding.albums.setItemChecked(vm.getSelected().getValue(),true);
        });
        vm.getSelected().observe(getViewLifecycleOwner(),position->binding.albums.setItemChecked(position,true));
    }
    @Override public void onDestroyView() { binding=null; super.onDestroyView(); }
}
