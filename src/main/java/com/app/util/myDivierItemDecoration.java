package com.app.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.app.recyclerview.R;

/**
 * Created by Administrator on 2017/2/6.
 */
public class myDivierItemDecoration extends RecyclerView.ItemDecoration {


    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public myDivierItemDecoration(Context context) {
        //初始化使用 指定的mDivider
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
       // mDivider = context.getResources().getDrawable(R.drawable.divider_bg);
        a.recycle();
    }
    public myDivierItemDecoration(Context context,int drawable) {
        //初始化使用 指定的mDivider
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
       // mDivider = a.getDrawable(0);
        mDivider = context.getResources().getDrawable(drawable);
        a.recycle();
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);


    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int ChildrenViewCount = parent.getChildCount();//子元素的数量

       // System.out.println("drawVerticalChildrenViewCount:" + ChildrenViewCount);

        //遍历子元素
        for (int i = 0; i < ChildrenViewCount; i++) {

            View ChildrenView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParam = (RecyclerView.LayoutParams) ChildrenView.getLayoutParams();

            //获取子元素的位置
            int layout_left = ChildrenView.getRight() + layoutParam.rightMargin;
            int layout_right = ChildrenView.getRight() + mDivider.getIntrinsicWidth() + layoutParam.rightMargin;
            int layout_top = ChildrenView.getTop() - layoutParam.topMargin;
            int layout_bottom = ChildrenView.getBottom()+layoutParam.bottomMargin;
            //在Canvas画上响应的分隔条
            // mDivider.setBounds(layout_left,layout_top,layout_right,layout_bottom);
            mDivider.setBounds(layout_left, layout_top, layout_right, layout_bottom);
           // System.out.println("mDivider-->" + mDivider.getBounds());
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {

        int ChildrenViewCount = parent.getChildCount();//子元素的数量
       // System.out.println("drawHorizontalChildrenViewCount:" + ChildrenViewCount);
        //遍历子元素
        for (int i = 0; i < ChildrenViewCount; i++) {

            View ChildrenView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParam = (RecyclerView.LayoutParams) ChildrenView.getLayoutParams();

            //获取子元素的位置
            int layout_left = ChildrenView.getLeft()-layoutParam.leftMargin;
            int layout_right = ChildrenView.getRight() + mDivider.getIntrinsicWidth()+layoutParam.rightMargin;
           // int layout_right = ChildrenView.getRight() ;
            int layout_top = ChildrenView.getBottom()+layoutParam.bottomMargin;
            int layout_bottom = layout_top + mDivider.getIntrinsicHeight();
            //在Canvas画上响应的分隔条
            mDivider.setBounds(layout_left, layout_top, layout_right, layout_bottom);
           // System.out.println("mDivider-->" + mDivider.getBounds());
            mDivider.draw(c);
        }


    }

   /* @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

       // outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());



        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                    mDivider.getIntrinsicHeight());
        }

    }*/


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

      //  System.out.println("getItemOffsets:"+"spanCount-->"+spanCount+"childCount-->"+childCount+"itemPosition-->"+itemPosition);
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                    mDivider.getIntrinsicHeight());
        }
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
