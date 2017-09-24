//package com.rishabh.githubquery;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
///**
// * Created by rishabh on 24-09-2017.
// */
//
//abstract class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.NumberViewHolder> {
//
//    private static final String TAG = RecycleAdapter.class.getSimpleName();
//
//    private String mListItems;
//
//    public RecycleAdapter(String ListOfItems) {
//        mListItems = ListOfItems;
//    }
//
//    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, String viewType) {
//        Context context = viewGroup.getContext();
//        String layoutForListItem = Integer.toString( R.layout.search_results);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        boolean shouldAttachToParentImmediately = false;
//
////Error causing line
////        View view = inflater.inflate(layoutForListItem,viewGroup,shouldAttachToParentImmediately);
//        NumberViewHolder viewHolder = new NumberViewHolder(view);
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(NumberViewHolder holder, int position) {
//        Log.d(TAG, "#" + position);
//        holder.bind(position);
//    }
//
//    class NumberViewHolder extends RecyclerView.ViewHolder {
//
//        // Will display the position in the list, ie 0 through getItemCount() - 1
//        TextView listItemNumberView;
//
//        public NumberViewHolder(View itemView) {
//            super(itemView);
//
//            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_search_number);
//        }
//
//        void bind(int listIndex) {
//            listItemNumberView.setText(String.valueOf(listIndex));
//        }
//    }
//}