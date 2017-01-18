package com.example.pt.sqlitewithrecyclerview.activity;

/**
 * Created by pt on 5/2/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pt.sqlitewithrecyclerview.R;
import com.example.pt.sqlitewithrecyclerview.adapter.InstructorAdapter;
import com.example.pt.sqlitewithrecyclerview.helper.DatabaseHelper;
import com.example.pt.sqlitewithrecyclerview.model.Instructor;

import java.util.ArrayList;
import java.util.List;


public class InstructorListFragment extends Fragment implements SearchView.OnQueryTextListener {

   private List<Instructor> instructorList =new ArrayList();
    private InstructorAdapter adapter ;
    private RecyclerView recyclerView;


    public InstructorListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setHasOptionsMenu(true);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instructor_list, container, false);
//        Getting List From database
        DatabaseHelper db = new DatabaseHelper(getActivity());
        instructorList = db.getAllContacts();
        adapter = new InstructorAdapter(instructorList);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.instructor_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);






        // Inflate the layout for this fragment
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addFragment = new InstructorAddFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_body,addFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });




        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu , menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(instructorList);
                        adapter.notifyDataSetChanged();

                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }
    private List<Instructor> filter(List<Instructor> models, String query) {
        query = query.toLowerCase();

        final List<Instructor> filteredModelList = new ArrayList<>();
        for (Instructor model : models) {
            final String text = model.getName().toLowerCase();
            final String dept = model.getDept_name().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
            if(dept.contains(query)){
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Instructor> filteredModelList = filter(instructorList, newText);
        adapter.setFilter(filteredModelList);
        return false;
    }

}