package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.RecyclercountryAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.showjobadapter;

import java.util.ArrayList;

public class Jobs extends android.support.v4.app.Fragment {

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<Integer> mimageurls=new ArrayList<>();
    public  RecyclerView recyclerView;
    Button btn;
    public Jobs() {
        // Required empty public constructor
    }

    /*    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }
    */
    String[] data={"Junior integration engineer","IT manager","Electrician","content writer"};
    String[] datalv2={"Information Technology (62)","Marketing and PR (51)","Engineering (36)","Management (18)","Sales (16)"};
    String[] dat={"Browse Jobs by City"};
    ArrayList<String> a1=new ArrayList<>();
    ArrayList<String> a2=new ArrayList<>();
    ArrayList<String> a3=new ArrayList<>();

    public void data2(){
        a1.add("iOS and Android app developer");
        a1.add("Programmer(Job location:Lahore)");
        a1.add("Engineer");
        a1.add("Software Engineer");
        a1.add("Junior Integration Engineer");
        a2.add("MFM Events");
        a2.add("Resettlement solution");
        a2.add("Executive solutions");
        a2.add("Quest search and selection");
        a2.add("I-talent");
        a3.add("Dubai");
        a3.add("Pakistan");
        a3.add("Qatar");
        a3.add("Kuwait");
        a3.add("Dubai");
        initRecyclerView3();
    }
    RecyclerView re;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_jobs, container, false);
        recyclerView=view.findViewById(R.id.recylerView2);
        re=view.findViewById(R.id.recylerView3);
        ListView lv= view.findViewById(R.id.list1);
        ListView lv1=view.findViewById(R.id.list2);
        ListView lv2=view.findViewById(R.id.list3);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.recentseach_items,R.id.textsearched,data);
        lv.setAdapter(adapter);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getContext(),R.layout.recentseach_items,R.id.textsearched,datalv2);
        lv1.setAdapter(adapter2);

        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(getContext(),R.layout.recentseach_items,R.id.textsearched,dat);
        lv2.setAdapter(adapter3);

        data2();
        data();
        btn=(Button)view.findViewById(R.id.searchgo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),FilterJobs.class);
                startActivity(i);
            }
        });
        return view;
    }


    private void data(){
        mimageurls.add(R.drawable.pakistanicon);
        mNames.add("Pakistan");
        mimageurls.add(R.drawable.dubaiii);
        mNames.add("Dubai");
        mimageurls.add(R.drawable.funnyland);
        mNames.add("Funland");
        mimageurls.add(R.drawable.qatar);
        mNames.add("Qatar");
        mimageurls.add(R.drawable.saudi);
        mNames.add("Saudia");
        mimageurls.add(R.drawable.bahrain);
        mNames.add("Bahrain");

        initRecyclerView();
    }

    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclercountryAdapter adapter= new RecyclercountryAdapter(mNames,mimageurls,getContext());
        recyclerView.setAdapter(adapter);


    }

    public void initRecyclerView3() {
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        re.setLayoutManager(linearLayoutManager2);
        //  RecyclercountryAdapter adapter= new RecyclercountryAdapter(mNames,mimageurls,getContext());
        showjobadapter ad=new showjobadapter(a1,a2,a3,getContext());
        re.setAdapter(ad);


    }

  /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
  /*  public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
