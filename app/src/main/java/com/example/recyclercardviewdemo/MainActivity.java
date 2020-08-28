package com.example.recyclercardviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler_member = (RecyclerView) findViewById(R.id.recycler_member);

        //呼叫setLayoutManager()設定layout樣式
        recycler_member.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        List<Member> memberList = getMemberList();

        recycler_member.setAdapter(new MemberAdapter(this, memberList));


    }


    //MainActivity的內部類別
    //先寫好泛型<MemberAdapter.MyViewHolder>替換原來的ViewHolder
    //再Override method
    private class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {
        private Context context;
        private List<Member> memberList;

        MemberAdapter(Context context, List<Member> memberList) {
            this.context = context;
            this.memberList = memberList;
        }

        //MemberAdapter的內部類別
        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_image;
            TextView tv_id;
            TextView tv_name;

            MyViewHolder(View view) {
                //父類別有個itemView的屬性，所以子類別也有這個屬性
                //呼叫super()將view傳給父類別的itemView屬性
                super(view);
                iv_image = (ImageView) view.findViewById(R.id.iv_image);
                tv_id = (TextView) view.findViewById(R.id.tv_id);
                tv_name = (TextView) view.findViewById(R.id.tv_name);

            }
        }


        @Override
        public int getItemCount() {
            return memberList.size();
        }


        @NonNull
        @Override
        public MemberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_view, parent, false);
            return new MemberAdapter.MyViewHolder(view);  //回傳MemberAdapter.MyViewHolder的建構式
        }

        @Override
        public void onBindViewHolder(@NonNull MemberAdapter.MyViewHolder holder, int position) {
            final Member member = memberList.get(position);

            holder.iv_image.setImageResource(member.getImage());
            holder.tv_id.setText(String.valueOf(member.getId()));
            holder.tv_name.setText(member.getName());

            //holder.iv_image.setOnClickListener();

            //父類別的itemView屬性
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView = new ImageView(MainActivity.this);
                    //member設為final才能用
                    imageView.setImageResource(member.getImage());
                    Toast toast = new Toast(MainActivity.this);
                    toast.setView(imageView);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
            });


        }


    }

    public List<Member> getMemberList() {
        List<Member> members = new ArrayList<>();
        members.add(new Member(R.drawable.ic_accessibility_black_48dp, 30, "Curry"));
        members.add(new Member(R.drawable.ic_accessible_black_48dp, 27, "Mike"));
        members.add(new Member(R.drawable.ic_account_balance_black_48dp, 83, "John"));
        members.add(new Member(R.drawable.ic_account_circle_black_48dp, 48, "Joan"));
        members.add(new Member(R.drawable.ic_add_shopping_cart_black_48dp, 22, "Brown"));
        members.add(new Member(R.drawable.ic_alarm_add_black_48dp, 01, "TMac"));
        members.add(new Member(R.drawable.ic_alarm_black_48dp, 03, "Lee"));
        members.add(new Member(R.drawable.ic_android_black_48dp, 11, "Thompson"));
        members.add(new Member(R.drawable.ic_donut_large_black_48dp, 19, "Jerry"));
        members.add(new Member(R.drawable.ic_bug_report_black_48dp, 23, "James"));
        members.add(new Member(R.drawable.ic_assignment_ind_black_48dp, 33, "Dean"));
        members.add(new Member(R.drawable.ic_all_out_black_48dp, 86, "Bob"));
        return members;
    }
}






