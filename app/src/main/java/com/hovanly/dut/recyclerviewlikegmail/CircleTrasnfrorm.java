package com.hovanly.dut.recyclerviewlikegmail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Copyright@ AsianTech.Inc
 * Created by Ly Ho V. on 19/07/2017
 */
public class CircleTrasnfrorm extends BitmapTransformation {
    public CircleTrasnfrorm(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

    private Bitmap circleCrop(BitmapPool bitmapPool, Bitmap bitmapResource) {
        if (bitmapResource == null) {
            return null;
        }
        int minSize = Math.min(bitmapResource.getHeight(), bitmapResource.getWidth());
        int x = (bitmapResource.getWidth() - minSize) / 2;
        int y = (bitmapResource.getHeight() - minSize) / 2;
        // TODO this could be acquired from the pool too
        Bitmap bitmapResult = bitmapPool.get(minSize, minSize, Bitmap.Config.ARGB_8888);
        if (bitmapResult == null) {
            bitmapResult = Bitmap.createBitmap(minSize, minSize, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapResult);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap squared = Bitmap.createBitmap(bitmapResource, x, y, minSize, minSize);
        paint.setShader(new BitmapShader(squared,BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        float r = minSize/2f;
        canvas.drawCircle(r, r, r, paint);
        return  bitmapResult;
    }
}
