package com.hexavara.hexavarademo.component.lovelist;

import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.hexavara.hexavarademo.R;
import com.hexavara.hexavarademo.databinding.ActivityLoveListBinding;

public class LoveListActivity extends AppCompatActivity {

    LoveListPresenter loveItems = new LoveListPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActivityLoveListBinding activityLoveListBinding = DataBindingUtil.setContentView(this, R.layout.activity_love_list);


        loveItems.updateLoveItems();
        activityLoveListBinding.setModel(loveItems);
        activityLoveListBinding.list.addItemDecoration(getSeparator());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    /**
     * love-list separator
     * @return
     */
    private RecyclerView.ItemDecoration getSeparator() {

        Paint mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.lovelist_grey));
        final float thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics());
        mPaint.setStrokeWidth(thickness);

        return new RecyclerView.ItemDecoration() {

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // we set the stroke width before, so as to correctly draw the line we have to offset by width / 2
                final int offset = (int) (mPaint.getStrokeWidth() / 2);

                // this will iterate over every visible view
                for (int i = 0; i < parent.getChildCount(); i++) {
                    // get the view
                    final View view = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

                    // get the position
                    final int position = params.getViewAdapterPosition();

                    // and finally draw the separator
                    if (position < state.getItemCount()) {
                        c.drawLine(view.getLeft(), view.getBottom() + offset, view.getRight(), view.getBottom() + offset, mPaint);
                    }
                }
            }
        };
    }
}
