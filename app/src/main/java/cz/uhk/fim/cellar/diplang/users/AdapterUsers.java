package cz.uhk.fim.cellar.diplang.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
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

/**
 * Adapter pro zobrazení vyhledávání uživatelů
 */
public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder>{

    private Context context;
    private List<User> userList;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseUser sender;
    private String senderID;
    private String senderEmail;
    private String senderName;

    public AdapterUsers(Context context, List<User> userList) {
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyHolder holder, int position) {
        String userName = userList.get(position).getName();
        String userEmail = userList.get(position).getEmail();
        String receiverId = userList.get(position).getUid();

        /**
         * Nastavení parametrů pro odesílatele žádosti o přátelství
         */
        sender = FirebaseAuth.getInstance().getCurrentUser();
        senderID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        senderEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        DatabaseReference myRef = database
                .getReference("Users").child(sender.getUid()).child("UserParams");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User senderProfile = snapshot.getValue(User.class);
                if(senderProfile != null){
                    senderName = senderProfile.name;
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });

        holder.searchUserName.setText(userName);
        holder.searchUserEmail.setText(userEmail);
        holder.btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senderId = senderID;
                String createdTime = LocalDateTime.now().toString();
                sender = FirebaseAuth.getInstance().getCurrentUser();
                /**
                 * Vytvoření požadavku na žádost o přátelství na straně odesílatele
                 */
                FriendRequest friendRequest = new FriendRequest(createdTime, senderEmail, userEmail, senderId, receiverId, senderName, userName);
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                        .child("Social")
                        .child("RequestsSentTo")
                        .child(receiverId)
                        .setValue(friendRequest);
                /**
                 * Vytvoření požadavku na žádost o přátelství na straně příjemce
                 */
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

    /**
     * Holder pro zobrazení komponent pro popis vyhledaného uživatele
     */
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
