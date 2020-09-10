package com.s.mygadproject;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.s.mygadproject.save.ApiInterface;
import com.s.mygadproject.save.SkillItemPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQLeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQLeaderFragment extends Fragment {
    ApiInterface apiInterface;
    ArrayList<SkillItemPojo> skillItemPojoArrayList = new ArrayList<>();
    RecyclerView recyclerViewx;
    SkillAdapter skillAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SkillIQLeaderFragment() {
        // Required empty public constructor
    }

    public static SkillIQLeaderFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SkillIQLeaderFragment fragment = new SkillIQLeaderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillLearningLeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillIQLeaderFragment newInstance(String param1, String param2) {
        SkillIQLeaderFragment fragment = new SkillIQLeaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_skill_learning_leader, container, false);
        fetchSkillData();
        recyclerViewx= (RecyclerView) inflate.findViewById(R.id.skil_recy);
        instant();

        return inflate;
    }

    private void instant() {
        recyclerViewx.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void fetchSkillData() {
        apiInterface = APIGad2.getClient().create(ApiInterface.class);
        Call<List<SkillItemPojo>> listCall = apiInterface.getSkillIQ();
        listCall.enqueue(new Callback<List<SkillItemPojo>>() {
            private String Tag="SkillQL";

            @Override
            public void onResponse(Call<List<SkillItemPojo>> call, Response<List<SkillItemPojo>> response) {
                skillItemPojoArrayList = new ArrayList<>(response.body());
                skillAdapter = new SkillAdapter(getActivity(),skillItemPojoArrayList);
                recyclerViewx.setAdapter(skillAdapter);
                Log.e (Tag, "correct"+response.body());
            }

            @Override
            public void onFailure(Call<List<SkillItemPojo>> call, Throwable t) {
                Log.e (Tag, "incorrect"+ t.getLocalizedMessage());
            }
        });
    }


}