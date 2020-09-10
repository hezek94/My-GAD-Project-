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
import com.s.mygadproject.save.LeadersItemPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningLeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningLeaderFragment extends Fragment {
    ApiInterface apiInterface;
    ArrayList<LeadersItemPojo> itemPojoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LearnersAdapter learnersAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearningLeaderFragment() {
        // Required empty public constructor
    }

    public static LearningLeaderFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LearningLeaderFragment fragment = new LearningLeaderFragment();
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningLeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningLeaderFragment newInstance(String param1, String param2) {
        LearningLeaderFragment fragment = new LearningLeaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private static final String Tag="Learningleader";

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
        final View inflate = inflater.inflate(R.layout.fragment_learning_leader, container, false);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.learn_recy);
        fetchLearningLeaders();
        initialize();
        return inflate;
    }

    private void initialize() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }


    private void fetchLearningLeaders() {
        apiInterface = APIGad2.getClient().create(ApiInterface.class);
        Call<List<LeadersItemPojo>> listCall = apiInterface.getLearning();
        listCall.enqueue(new Callback<List<LeadersItemPojo>>() {


            @Override
            public void onResponse(Call<List<LeadersItemPojo>> call, Response<List<LeadersItemPojo>> response) {
                itemPojoArrayList=new ArrayList<>(response.body());
                learnersAdapter = new LearnersAdapter(getActivity(), itemPojoArrayList);
                recyclerView.setAdapter(learnersAdapter);
                Toast.makeText(getActivity(), "success",Toast.LENGTH_SHORT).show();
                Log.e(Tag,"success"+response.body());
            }

            @Override
            public void onFailure(Call<List<LeadersItemPojo>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed",Toast.LENGTH_SHORT).show();

            }
        });
    }
}