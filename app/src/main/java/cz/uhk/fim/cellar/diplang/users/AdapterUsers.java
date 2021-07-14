package cz.uhk.fim.cellar.diplang.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.FriendRequest;
import cz.uhk.fim.cellar.diplang.classes.User;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder>{

    Context context;
    List<User> userList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    String userID;

    public AdapterUsers(Context context, List<User> userList) {
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent,false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyHolder holder, int position) {
        String userName = userList.get(position).getName();
        String userEmail = userList.get(position).getEmail();
        String receiverId = userList.get(position).getUid();

        holder.searchUserName.setText(userName);
        holder.searchUserEmail.setText(userEmail);

        holder.btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senderId = userID;
                String createdTime = LocalDateTime.now().toString();
                FriendRequest friendRequest = new FriendRequest(createdTime);
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                        .child("Social")
                        .child("RequestsSentTo")
                        .child(receiverId)
                        .setValue(friendRequest);

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(receiverId)
                        .child("Social")
                        .child("RequestsReceivedFrom")
                        .child(senderId)
                        .setValue(friendRequest);

                Toast.makeText(context, "Žádost odeslána", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView searchUserName, searchUserEmail;
        ImageButton btnAddUser;

        public MyHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            searchUserName = (TextView) itemView.findViewById(R.id.searchUserName);
            searchUserEmail = (TextView) itemView.findViewById(R.id.searchUserEmail);
            btnAddUser = (ImageButton) itemView.findViewById(R.id.btnAddUser);

        }
    }
}
