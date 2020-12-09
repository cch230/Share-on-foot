package com.example.shareonfoot.home.mySpace;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;



public class Image_processing {

     public Bitmap ImageProcessing(Bitmap bitmap){
         Mat input =new Mat();
         Mat gray=new Mat();
         Mat gradThresh = new Mat();
         Mat matGaussian = new Mat();
         Mat erode = new Mat();
         Mat dilate = new Mat();
         Utils.bitmapToMat(bitmap,input);
         Mat matCny = new Mat();
         Mat result;
         //Mat kernel= Mat.ones(9,5,CvType.CV_8U,new Scalar(1));
         Imgproc.cvtColor(input,gray,Imgproc.COLOR_RGB2GRAY);
         //Imgproc.adaptiveThreshold(gray, gradThresh, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 3, 12);  //block size 3
         Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE,new Size(11,11));
         Imgproc.dilate(gray,dilate,element);
         Imgproc.erode(dilate,erode,new Mat());
         Imgproc.blur(erode,matGaussian,new Size(3,3),new Point(2,2));
         Imgproc.Canny(matGaussian,matCny,130,210,3);
         result=removeVerticalLines(matCny,80).clone();
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
