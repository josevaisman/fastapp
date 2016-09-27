package br.liveo.ndrawer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import br.liveo.ndrawer.ui.fragment.Map1Fragment;

public class Map1Adapter extends MapViewPager.Adapter {

    public static final String[] TITLES = { "Las Condes", "La Reina", "Ñuñoa", "Providencia", "Macul" };

    public static final CameraPosition[] POSITIONS = {
		CameraPosition.fromLatLngZoom(new LatLng(-33.414597,-70.602216), 15f),
		CameraPosition.fromLatLngZoom(new LatLng(-33.438728,-70.557616), 15f),
        CameraPosition.fromLatLngZoom(new LatLng(-33.455506,-70.605355), 15f),
        CameraPosition.fromLatLngZoom(new LatLng(-33.426840,-70.616446), 15f),
        CameraPosition.fromLatLngZoom(new LatLng(-33.481132,-70.606008), 15f)
    };

    public Map1Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return Map1Fragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public CameraPosition getCameraPosition(int position) {
        return POSITIONS[position];
    }

}
