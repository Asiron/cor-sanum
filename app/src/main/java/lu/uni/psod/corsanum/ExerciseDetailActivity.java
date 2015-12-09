package lu.uni.psod.corsanum;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import lu.uni.psod.corsanum.fragments.ExerciseDetailHeaderFragment;
import lu.uni.psod.corsanum.models.Exercise;

public class ExerciseDetailActivity extends BaseActivity implements OnMapReadyCallback{

    private int mCurrentExerciseIndex = 0;
    private Exercise mCurrentExercise = null;

    private MapFragment mMapFragment                     = null;
    private ExerciseDetailHeaderFragment mHeaderFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        mCurrentExerciseIndex = getIntent().getIntExtra(getString(R.string.current_exercise_idx),0);
        mCurrentExercise = mExerciseList.get(mCurrentExerciseIndex);

        mHeaderFragment = (ExerciseDetailHeaderFragment) getFragmentManager()
                        .findFragmentById(R.id.exercise_detail_header);

        mMapFragment = (MapFragment) getFragmentManager()
                        .findFragmentById(R.id.map);

        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions()
                .position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHeaderFragment.updateAdapter(mExerciseList.get(mCurrentExerciseIndex).getActions());
    }

    public void initMap() {

    }

    public int getCurrentExerciseIndex() {
        return mCurrentExerciseIndex;
    }

    public Exercise getCurrentExercise() {
        return mCurrentExercise;
    }
}
