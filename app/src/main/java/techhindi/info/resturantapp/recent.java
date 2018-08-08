package techhindi.info.resturantapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class recent extends AppCompatActivity {


    RecyclerView mBlogList;

    DatabaseReference mDatabase;

    RecyclerView.LayoutManager recyclerlayManager;


    ProgressBar progressBar;

   /* String GETURL = "http://www.examantra.com/android/result.php";
    String JID = "id";
    String JTITLE = "title";
    String JDESCRIPTION = "description";
    String JDATE = "date";
    String JVIEW = "view";
    String JCATEGORY = "category";
    String JIMAGE = "image";
    String JIMAGESTATUS = "imagestatus";
    String JLINKS = "links";
    String JLABELS = "labels";
    String JSTATUS = "status";
    JsonArrayRequest jArrReq ;
    RequestQueue reqQue ;*/
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);




        mBlogList = (RecyclerView)findViewById(R.id.recyclerView1);
        mBlogList.setHasFixedSize(true);

        recyclerlayManager = new LinearLayoutManager(this);
        mBlogList.setLayoutManager(recyclerlayManager);

        progressBar = (ProgressBar)findViewById(R.id.progressBar1);

        progressBar.setVisibility(View.VISIBLE);



    }

    @Override
    public void onStart() {
        super.onStart();


       FirebaseRecyclerAdapter<Blog,BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
               (Blog.class,R.layout.row_itemresult,BlogViewHolder.class,mDatabase) {


           @Override
           protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

               viewHolder.setTitle(model.getTitle());
               viewHolder.setDesc(model.getDesc());
               viewHolder.setImage(getApplicationContext(),model.getImage());


           }
       };

       mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
        }

        public void setTitle(String title)
        {
            TextView post_title=(TextView)mview.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc)
        {
            TextView post_desc=(TextView)mview.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx,String image)
        {
            ImageView post_image=(ImageView)mview.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);
        }
    }
}
