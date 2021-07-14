package cz.uhk.fim.cellar.diplang.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.User;


public class ProfileFriendsFragment extends Fragment {

    private SearchView searchUsers;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private String userID;
    private List<User> userList;
    private RecyclerView usersRecycleView;
    private AdapterUsers adapterUsers;




    public ProfileFriendsFragment() {
        // Required empty public constructor
    }

    public static ProfileFriendsFragment newInstance(String param1, String param2) {
        ProfileFriendsFragment fragment = new ProfileFriendsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_friends, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        usersRecycleView = (RecyclerView) v.findViewById(R.id.usersRecycleView);
        usersRecycleView.setHasFixedSize(true);
        usersRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        userList = new ArrayList<>();


        searchUsers = (SearchView) v.findViewById(R.id.searchUsers);
        searchUsers.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchUsers(s);

                }else{
                    //search text empy - get all users? 
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchUsers(s);

                }else{
                    //search text empy - get all users?
                }
                return false;
            }
        });


        return v;
    }

    private void searchUsers(String query) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    if(!user.getUid().equals(userID)){

                        if(user.getName().toLowerCase().contains(query.toLowerCase()) ||
                            user.getEmail().toLowerCase().contains(query.toLowerCase())){
                            userList.add(user);
                        }

                    }
                    adapterUsers = new AdapterUsers(getActivity(), userList);
                    //refresh adapter
                    adapterUsers.notifyDataSetChanged();
                    usersRecycleView.setAdapter(adapterUsers);
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}