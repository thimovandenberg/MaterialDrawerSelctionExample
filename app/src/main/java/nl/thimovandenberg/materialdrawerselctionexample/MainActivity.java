package nl.thimovandenberg.materialdrawerselctionexample;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView;

public class MainActivity extends AppCompatActivity {

    MaterialDrawerSliderView mSlider;
    long currentIdentifier = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupDrawer();
        Button mBtnSetSelection = findViewById(R.id.btnSetSelection);
        mBtnSetSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlider.setSelection(currentIdentifier>0? currentIdentifier-1: 4);
            }
        });
    }


    private void setupDrawer(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawerLayoutRoot);

        mSlider = findViewById(R.id.drawer_container);

        // Enabling this causes the drawer items to not be selected when clicked
        //mSlider.setMultiSelect(false);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                com.mikepenz.materialdrawer.R.string.material_drawer_open,
                com.mikepenz.materialdrawer.R.string.material_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        for(int i = 0; i<5; i++){
            PrimaryDrawerItem mDrawerItem = new PrimaryDrawerItem();
            mDrawerItem.setName(new StringHolder("test #"+i));
            mDrawerItem.setSelectable(true);
            mDrawerItem.setIdentifier(i);
            mSlider.getItemAdapter().add(mDrawerItem);
        }

        mSlider.setOnDrawerItemClickListener((view, drawerItem, position) -> {
            Log.d("debug", "position = "+position);
            currentIdentifier = drawerItem.getIdentifier();

            return false;
        });

    }

}
