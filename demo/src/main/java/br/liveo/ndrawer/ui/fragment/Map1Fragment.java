package br.liveo.ndrawer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.liveo.ndrawer.R;
import br.liveo.ndrawer.adapter.Map1Adapter;

public class Map1Fragment extends android.support.v4.app.Fragment {

    private TextView title;
    private int index;

    public Map1Fragment() { }

    public static Map1Fragment newInstance(int i) {
        Map1Fragment f = new Map1Fragment();
        Bundle args = new Bundle();
        args.putInt("INDEX", i);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_1, container, false);
        title = (TextView) view.findViewById(R.id.title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) index = args.getInt("INDEX", 0);

        title.setText(Map1Adapter.TITLES[index]);
    }

}
