package com.example.iatiimd_eindoplevering;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiCreateIdeeService;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IdeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdeeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText etTitel, etDescription;
    Spinner sCategorie;
    Button btnCreate;
    String selected;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IdeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IdeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IdeeFragment newInstance(String param1, String param2) {
        IdeeFragment fragment = new IdeeFragment();
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
        return inflater.inflate(R.layout.fragment_idee, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        etTitel = (EditText) getView().findViewById(R.id.titel);
        etDescription = (EditText) getView().findViewById(R.id.description);
        btnCreate = (Button) getView().findViewById(R.id.create);

        sCategorie = (Spinner) getView().findViewById(R.id.categorie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategorie.setAdapter(adapter);
        sCategorie.setOnItemSelectedListener(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();
            }
        });
    }

    //create idee
    private void Create(){
        //information that is saved in the database
        final String titel = etTitel.getText().toString();
        final String description = etDescription.getText().toString();
        final String categorie = selected;

        //token to verify the user
        tokenResponse tokenResponse = (tokenResponse) getActivity().getApplicationContext();
        final String token = "Bearer " + tokenResponse.getAccess_token();

        ApiCreateIdeeService service = ApiClient.getClient().create(ApiCreateIdeeService.class);
        Call<ResponseBody> srvCreate = service.postIdee(titel, description, categorie, token);
        srvCreate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Idee is succesvol toegevoegd", Toast.LENGTH_LONG).show();

                    etTitel.setText(null);
                    etDescription.setText(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }

    //Spinner item select
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();
    }
    //Spinner no item selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}