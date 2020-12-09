package com.example.shareonfoot.home.mySpace;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;


public class Image_processing {

     public Bitmap ImageProcessing(Bitmap bitmap){
         Mat input =new Mat();
         Utils.bitmapToMat(bitmap,input);
         Mat img_gray =new Mat(), img_sobel=new Mat(), img_threshold=new Mat(), element=new Mat(), gradThresh=new Mat();
         Imgproc.cvtColor(input, img_gray, Imgproc.COLOR_RGB2GRAY);
         Imgproc.Sobel(img_gray, img_sobel, CvType.CV_8U, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
         //at src, Mat dst, double thresh, double maxval, int type
         Imgproc.threshold(img_sobel, img_threshold, 0, 255, 8);
         //Imgproc.adaptiveThreshold(img_sobel, img_threshold, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 3, 12);  //block size 3

         element=Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15,5));
         Imgproc.morphologyEx(img_threshold, img_threshold, Imgproc.MORPH_CLOSE, element);
         /*List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
         Mat hierarchy = new Mat();
         Imgproc.findContours(img_threshold, contours,hierarchy, 0, 1);

         List<MatOfPoint> contours_poly = new ArrayList<MatOfPoint>(contours.size());

         for( int i = 0; i < contours.size(); i++ ){

             MatOfPoint2f  mMOP2f1=new MatOfPoint2f();
             MatOfPoint2f  mMOP2f2=new MatOfPoint2f();

             contours.get(i).convertTo(mMOP2f1, CvType.CV_32FC2);
             Imgproc.approxPolyDP(mMOP2f1, mMOP2f2, 2, true);
             mMOP2f2.convertTo(contours.get(i), CvType.CV_32S);


             Rect appRect = Imgproc.boundingRect(contours.get(i));
             if (appRect.width>appRect.height) {
                 boundRect.add(appRect);
             }
         }*/
         Mat result;
         result=removeVerticalLines(img_threshold,80).clone();
         Utils.matToBitmap(result,bitmap);
         return bitmap;
     }

    public Mat removeVerticalLines(Mat img, int limit) {
        Mat lines=new Mat();
        int threshold = 100; //선 추출 정확도
        int minLength = 80; //추출할 선의 길이
        int lineGap = 5; //5픽셀 이내로 겹치는 선은 제외
        int rho = 1;
        Imgproc.HoughLinesP(img, lines, rho, Math.PI/180, threshold, minLength, lineGap);
        for (int i = 0; i < lines.total(); i++) {
            double[] vec=lines.get(i,0);
            Point pt1, pt2;
            pt1=new Point(vec[0],vec[1]);
            pt2=new Point(vec[2],vec[3]);
            double gapY = Math.abs(vec[3]-vec[1]);
            double gapX = Math.abs(vec[2]-vec[0]);
            if(gapY>limit && limit>0) {
                //remove line with black color
                Imgproc.line(img, pt1, pt2, new Scalar(0, 0, 0), 10);
            }
        }
        return img;
    }
}
