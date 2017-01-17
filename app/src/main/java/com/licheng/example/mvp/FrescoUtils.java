package com.licheng.example.mvp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * Created by licheng on 17/1/17.
 */

public class FrescoUtils {



    public static void loadImage(Uri uri, SimpleDraweeView view) {

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(50, 50))
                .setPostprocessor(postprocessor)
                .build();

        PipelineDraweeController controller = (PipelineDraweeController) Fresco
                .newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(view.getController())
                .build();

        view.setController(controller);

    }

    //图片后处理
    static Postprocessor postprocessor = new BasePostprocessor() {
        @Override
        public String getName() {
            return super.getName();
        }

        @Override
        public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {

            CloseableReference<Bitmap> bitmapRef = bitmapFactory.createBitmap(
                    sourceBitmap.getWidth() / 4,
                    sourceBitmap.getHeight() / 4);
            try {
                Bitmap destBitmap = bitmapRef.get();
                for (int x = 0; x < destBitmap.getWidth(); x++) {
                    for (int y = 0; y < destBitmap.getHeight(); y++) {
                        destBitmap.setPixel(x, y, sourceBitmap.getPixel(x, y));
                    }
                }
                return CloseableReference.cloneOrNull(bitmapRef);
            } finally {
                CloseableReference.closeSafely(bitmapRef);
            }
        }
    };


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }

            return inSampleSize;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromFile(String filename,
                                                     int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filename, options);
    }


}
