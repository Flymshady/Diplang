package cz.uhk.fim.cellar.diplang.users;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

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
import cz.uhk.fim.cellar.diplang.classes.FriendRequest;
import cz.uhk.fim.cellar.diplang.classes.User;
import cz.uhk.fim.cellar.diplang.navigation.ProfileFragment;


public class ProfileFriendsFragment extends Fragment {

    private SearchView searchUsers;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private String userID;
    private List<User> userList;
    private RecyclerView usersRecycleView;
    private AdapterUsers adapterUsers;
    private LinearLayout layoutRequests;
    private Context mContext;
    private ViewPager2 viewPagerProfile;




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
        viewPagerProfile = (ViewPager2) v.findViewById(R.id.viewPagerProfile);

        usersRecycleView = (RecyclerView) v.findViewById(R.id.usersRecycleView);
        usersRecycleView.setHasFixedSize(true);
        usersRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        userList = new ArrayList<>();

        layoutRequests = (LinearLayout) v.findViewById(R.id.layoutRequests);
        loadRequests();

        searchUsers = (SearchView) v.findViewById(R.id.searchUsers);
        searchUsers.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                userList.clear();
                adapterUsers = new AdapterUsers(mContext, userList);
                //refresh adapter
                adapterUsers.notifyDataSetChanged();
                usersRecycleView.setAdapter(adapterUsers);
                return false;
            }
        });
        searchUsers.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchUsers(s);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchUsers(s);
                }
                return false;
            }

        });


        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    private void loadRequests() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                                        .child(userID).child("Social").child("RequestsReceivedFrom");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    FriendRequest friendRequest = ds.getValue(FriendRequest.class);
                    if(friendRequest!=null){
                        LinearLayout ll = new LinearLayout(mContext);
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        ll.setBackgroundResource(R.color.right_answer_color);
                        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        ll.setWeightSum(2f);

                        ImageButton btnConfirm = new ImageButton(mContext);
                        btnConfirm.setBackgroundResource(R.drawable.ic_baseline_check_circle_24);
                        LinearLayout.LayoutParams lpBtn = new LinearLayout.LayoutParams(100,
                                100);
                        lpBtn.gravity=1;
                        lpBtn.setMargins(10, 0, 10, 0);
                        btnConfirm.setLayoutParams(lpBtn);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //add friend
                                User sender = new User();
                                sender.setEmail(friendRequest.getSenderEmail());
                                sender.setName(friendRequest.getSenderName());
                                sender.setUid(friendRequest.getSenderId());
                                User receiver = new User();
                                receiver.setEmail(friendRequest.getReceiverEmail());
                                receiver.setName(friendRequest.getReceiverName());
                                receiver.setUid(friendRequest.getReceiverId());

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                                        .child("Social")
                                        .child("Friends")
                                        .child(friendRequest.getSenderId())
                                        .setValue(sender);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(sender.getUid())
                                        .child("Social")
                                        .child("Friends")
                                        .child(receiver.getUid())
                                        .setValue(receiver);
                                //remove req
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                                        .child("Social")
                                        .child("RequestsReceivedFrom")
                                        .child(friendRequest.getSenderId())
                                        .removeValue();
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(friendRequest.getSenderId())
                                        .child("Social")
                                        .child("RequestsSentTo")
                                        .child(userID)
                                        .removeValue();

                                layoutRequests.removeView(ll);

                            }
                        });


                        ImageButton btnCancel = new ImageButton(mContext);
                        btnCancel.setBackgroundResource(R.drawable.ic_baseline_cancel_24);
                        btnCancel.setLayoutParams(lpBtn);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //remove req
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                                        .child("Social")
                                        .child("RequestsReceivedFrom")
                                        .child(friendRequest.getSenderId())
                                        .removeValue();
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(friendRequest.getSenderId())
                                        .child("Social")
                                        .child("RequestsSentTo")
                                        .child(userID)
                                        .removeValue();
                                layoutRequests.removeView(ll);
                            }
                        });


                        TextView textView = new TextView(mContext);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                        lp.setMargins(0, 10, 0, 10);

                        textView.setText(friendRequest.getSenderEmail());
                        textView.setTextSize(20);
                        textView.setTextColor(Color.BLACK);
                        textView.setLayoutParams(lp);

                        ll.addView(textView);
                        ll.addView(btnConfirm);
                        ll.addView(btnCancel);
                        layoutRequests.addView(ll);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void searchUsers(String query) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    //overeni zda neni uz pritel
                    if(ds.child("Social").child("Friends").child(userID).getValue(User.class)==null) {
                        //zda od nej nema uz request
                        if (ds.child("Social").child("RequestsSentTo").child(userID).getValue(FriendRequest.class) == null) {
                            if (!user.getUid().equals(userID)) {
                                if (user.getName().toLowerCase().contains(query.toLowerCase()) ||
                                        user.getEmail().toLowerCase().contains(query.toLowerCase())) {
                                    userList.add(user);
                                }
                            }
                        }
                    }
                    adapterUsers = new AdapterUsers(mContext, userList);
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