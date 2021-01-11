package com.music.player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.music.player.adapter.AlbumAdapter;
import com.music.player.adapter.TitreAdapter;
import com.music.player.model.Album;
import com.music.player.model.MusicLab;
import com.music.player.model.Titre;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static int selection;

    private ListView listeView;
    private MusicLab musicLab;
    private MainActivity activite;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(int sectionNumber, MainActivity activity) {
        MainFragment fragment = new MainFragment();
        fragment.setMainActivity(activity);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listeView = (ListView) rootView.findViewById(R.id.content);
        musicLab = new MusicLab(getContext());

        selection = this.getArguments().getInt(ARG_SECTION_NUMBER);

        switch (selection) {
            case 1:
                TitreAdapter adapter = new TitreAdapter(getActivity(), musicLab.getTitres());
                listeView.setAdapter(adapter);
                activite.getServicePlayer().setList(musicLab.getTitres());
                listeView.setOnItemClickListener(onClickMusic);
                break;

            case 2:
                AlbumAdapter adapter2 = new AlbumAdapter(getActivity(), musicLab.getAlbums());
                listeView.setAdapter(adapter2);
                listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Album album = (Album) parent.getItemAtPosition(position);
                        TitreAdapter ad2 = new TitreAdapter(getActivity(), musicLab.getTitresFromAlbum(album.getTitle()));
                        listeView.setAdapter(ad2);
                        activite.getServicePlayer().setList(musicLab.getTitresFromAlbum(album.getTitle()));
                        listeView.setOnItemClickListener(onClickMusic);
                    }
                });
                break;

            case 3:
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, musicLab.getArtists());
                listeView.setAdapter(adapter3);
                listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String artiste = (String) parent.getItemAtPosition(position);
                        TitreAdapter ad3 = new TitreAdapter(getActivity(), musicLab.getTitresFromArtist(artiste));
                        listeView.setAdapter(ad3);
                        activite.getServicePlayer().setList(musicLab.getTitresFromArtist(artiste));
                        listeView.setOnItemClickListener(onClickMusic);
                    }
                });
                break;

            default:
                Log.e("TAG", "Section inconnue: " + getArguments().getInt(ARG_SECTION_NUMBER));
        }
        return rootView;
    }

    private void setMainActivity(MainActivity activite){
        this.activite = activite;
    }

    private AdapterView.OnItemClickListener onClickMusic = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Titre titre = (Titre) parent.getItemAtPosition(position);
            activite.getServicePlayer().playMedia(titre);
        }
    };
}