package br.liveo.ndrawer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.content.Intent;

import br.liveo.ndrawer.ui.activity.Map1Activity;

import br.liveo.ndrawer.R;


public class MapsFragment extends Fragment {
	private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

	public static MapsFragment newInstance(String text){
		MapsFragment mFragment = new MapsFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString(TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		
		rootView.findViewById(R.id.card1).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), Map1Activity.class));
				}
			});
			
		rootView.findViewById(R.id.card2).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), Map1Activity.class));
				}
			});

		rootView.findViewById(R.id.card3).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), Map1Activity.class));
				}
			});
			
		rootView.findViewById(R.id.card4).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), Map1Activity.class));
				}
			});
			
		return rootView;		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);

        //Select search item
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setVisible(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(R.id.search_src_text))
			.setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

		menu.findItem(R.id.menu_add).setVisible(true);

		mSearchCheck = false;	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

			case R.id.menu_add:
				Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
				break;

			case R.id.menu_search:
				mSearchCheck = true;
				Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
				break;
		}
		return true;
	}	

	private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
		@Override
		public boolean onQueryTextSubmit(String s) {
			return false;
		}

		@Override
		public boolean onQueryTextChange(String s) {
			if (mSearchCheck){
				// implement your search here
			}
			return false;
		}
	};
}
