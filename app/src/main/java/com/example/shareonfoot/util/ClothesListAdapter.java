package com.example.shareonfoot.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shareonfoot.R;
import com.example.shareonfoot.VO.ClothesVO;

import java.util.ArrayList;


//어댑터 : 리사이클러뷰의 아이템 뷰를 생성하는 역할을 함
//뷰 홀더 : 아이템 뷰를 저장하는 객체
//아이템 뷰 : 각각의 카드뷰 한 개
public class ClothesListAdapter extends RecyclerView.Adapter<ClothesListAdapter.ItemViewHolder> {

    Context mContext;
    ArrayList<ClothesVO> listData=new ArrayList<>();

    int item_layout; //리사이클러뷰 레이아웃. fragment_recyclerview임.
   // private AdapterView.OnItemClickListener mListener = null ;


    public interface OnItemClickListener {
        void onItemClick(View v, int position, ImageView iv_Clothes, ClothesVO cloInfo);
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener nmListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.nmListener = listener ;
    }

    //생성자에서 데이터 리스트 객체를 전달받음.
    public ClothesListAdapter(Context context, ArrayList<ClothesVO> items, int item_layout ) {
        this.mContext=context;
        this.item_layout=item_layout;
        this.listData=items;

    }
    //뷰홀더 객체 생성하며 리턴 (아이템뷰를 위한)
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);

        return new ItemViewHolder(v);
    }

    //position 에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));

    }

    //아이템 개수 반환
    @Override
    public int getItemCount() {
        return this.listData.size();
    }

    public void addItem(ClothesVO data){ listData.add(data);}


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        private TextView textView6;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = (TextView)itemView.findViewById(R.id.idx);
            textView2 = (TextView)itemView.findViewById(R.id.title);
            textView3 = (TextView)itemView.findViewById(R.id.category);
            textView4 = (TextView)itemView.findViewById(R.id.star);
            textView5 = (TextView)itemView.findViewById(R.id.adress);
            textView6 = (TextView)itemView.findViewById(R.id.review);

            imageView = (ImageView)itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        System.out.println("클릭");
                        if (nmListener != null) {
                            nmListener.onItemClick(v, pos, imageView, listData.get(pos)) ;
                        }
                        // 데이터 리스트로부터 아이템 데이터 참조.
                        //RecyclerItem item = mData.get(pos) ;
                    }
                }
            });
        }

        void onBind(ClothesVO data) {
            textView1.setText(data.getidx());
            textView2.setText(data.getname());
            textView3.setText(data.getcategory());
            textView4.setText(data.getstar());
            textView5.setText(data.getadress());
            textView6.setText(data.getreview());
            imageView.setImageResource(data.getimage());
        }


    }
}