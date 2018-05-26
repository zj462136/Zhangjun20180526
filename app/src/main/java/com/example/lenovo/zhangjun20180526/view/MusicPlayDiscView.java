//package com.example.lenovo.zhangjun20180526.view;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.os.Handler;
//import android.view.View;
//
//import com.example.lenovo.zhangjun20180526.R;
//import com.example.lenovo.zhangjun20180526.util.Constant;
//
//public class MusicPlayDiscView extends View {
//
//  Paint paint;
//
//  private Handler handler;
//  // 光盘图片
//  Bitmap bitmapDisc = BitmapFactory.decodeResource(getResources(),
//      R.mipmap.ic_launcher);
//  // 专辑图片
//  Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(),
//          R.mipmap.ic_launcher);
//  Bitmap bitmapCircularAblum, bitmapDiscCircular;
//  // 光盘指针图片
//  Bitmap bitmapNeedle = BitmapFactory.decodeResource(getResources(),
//          R.mipmap.ic_launcher);
//
//  public MusicPlayDiscView(Context context) {
//    super(context);
//    //分别获得光盘和专辑的圆形图片
//    bitmapCircularAblum = getCircularBitmap(bitmapImage, 400);
//    bitmapDiscCircular = getCircularBitmap(bitmapDisc,
//        bitmapDisc.getWidth());
//
//    paint = new Paint();
//    handler = new Handler();
//    handler.post(runnable);
//  }
//
//  /**
//   * 利用线程不断更新界面
//   */
//  private Runnable runnable = new Runnable() {
//    public void run() {
//      postInvalidate();
//      handler.postDelayed(runnable, 50);
//    }
//  };
//
//  //状态标志：
//  int before = 0;
//  //角度标志
//  private int degreeFlag = 0;
//
//  @Override
//  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
//
//    /**
//     * 先画光盘与专辑图片
//     */
//
//    if (Constant.CurrentState == Constant.Play) {
//      Constant.Degree++;
//      if (Constant.Degree > 360)
//        Constant.Degree = 0;
//
//      degreeFlag = Constant.Degree;
//
//      canvas.save();
//      //360为屏幕的中间位置，手机是720的宽度
//      canvas.rotate(Constant.Degree, 360,
//          170 + bitmapDiscCircular.getHeight() / 2);
//      canvas.drawBitmap(bitmapCircularAblum,
//          360 - bitmapCircularAblum.getWidth() / 2, 200, paint);
//
//      canvas.drawBitmap(bitmapDisc,
//          360 - bitmapDiscCircular.getWidth() / 2, 170, paint);
//
//      canvas.restore();
//
//    } else {
//      //before = 0;
//
//      canvas.save();
//      canvas.rotate(degreeFlag, 360,
//          170 + bitmapDiscCircular.getHeight() / 2);
//      canvas.drawBitmap(bitmapCircularAblum,
//          360 - bitmapCircularAblum.getWidth() / 2, 200, paint);
//
//      canvas.drawBitmap(bitmapDisc,
//          360 - bitmapDiscCircular.getWidth() / 2, 170, paint);
//      canvas.restore();
//
//    }
//
//    /**
//     * 再画光盘指针图片,三张图不能同时画
//     */
//    if (Constant.CurrentState == Constant.Play ) {
//      canvas.drawBitmap(bitmapNeedle, 360 - bitmapNeedle.getWidth() / 2,
//          0, paint);
//
//    } else {
//      canvas.save();
//      Matrix matrix = new Matrix();
//      matrix.postRotate(-45);
//      paint.setAntiAlias(true);
//      //获得指针旋转后的图片
//      Bitmap bm = Bitmap.createBitmap(bitmapNeedle, 0, 0,
//          bitmapNeedle.getWidth(), bitmapNeedle.getHeight(), matrix,
//          true);
//      canvas.drawBitmap(bm, 360 - bitmapNeedle.getWidth() / 2 + 5, -60,
//          paint);
//
//
//      canvas.restore();
//    }
//
//  }
//
//  /**
//   * 获得圆形图片的方法
//   *
//   */
//  private Bitmap getCircularBitmap(Bitmap bitmap, int radius) {
//    Bitmap sbmp = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
//
//    Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
//        Bitmap.Config.ARGB_8888);
//    Canvas canvas = new Canvas(output);
//
//    Paint paint = new Paint();
//    Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
//
//    paint.setAntiAlias(true);
//    paint.setFilterBitmap(true);
//    paint.setDither(true);
//    canvas.drawARGB(0, 0, 0, 0);
//    paint.setColor(Color.BLACK);
//    canvas.drawCircle(sbmp.getWidth() / 2, sbmp.getHeight() / 2,
//        sbmp.getWidth() / 2, paint);
//    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//    canvas.drawBitmap(sbmp, rect, rect, paint);
//    return output;
//  }
//
//}