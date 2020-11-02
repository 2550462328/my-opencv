package com.acts.my_opencv.demo;

import com.acts.my_opencv.common.web.BaseController;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

/**
 * OpenCV人脸识别
 */
@Controller
@RequestMapping(value = "demo")
public class DetectController extends BaseController {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source, target;
        target = new Mat();
        String sourceAddress = "D:\\develop\\tess4J\\img\\2.jpg";
        String targetAddress = "D:\\develop\\tess4J\\img\\2_get.jpg";
//        source= Highgui.imread(sourceAddress,0);
//        //把图象变为灰度，大小一致输出
//        Highgui.imwrite(sourceAddress,source);
//        Size size=new Size(300,400);
//        Imgproc.resize(source,target,size,0,0,Imgproc.INTER_NEAREST);
//        Highgui.imwrite(sourceAddress,target);

        try {
            detectEye(sourceAddress, targetAddress);
            target = Highgui.imread(targetAddress, 1);
            detectFace(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 从url（image资源）识别出人脸图像并用矩阵标记
//     */
//    @RequestMapping(value = "detectFace")
//    public void detectFace(HttpServletResponse response, HttpServletRequest request, String url) {
////        加载本地的OpenCV库，这样就可以用它来调用Java API。
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.out.println("===========java.library.path:" + System.getProperty("java.library.path"));
//        logger.info("\nRunning DetectFaceDemo");
//        String resourcePath = getClass().getResource("/lbpcascade_frontalface.xml").getPath().substring(1);
//        logger.info("resourcePath============" + resourcePath);
//
////        创建实例CascadeClassifier，将已加载的分类器的文件名传递给它。
//        CascadeClassifier faceDetector = new CascadeClassifier(resourcePath);
//        logger.info("url==============" + Constants.PATH + url);
////        接下来我们将图片转化成Java API能够接受使用Highui类的格式，铺垫在OpenCV C++的n维密集数组类上边。
//        Mat image = Highgui.imread(Constants.PATH + url);
//        MatOfRect faceDetections = new MatOfRect();
////        然后，调用分类器上的detectMultiScale方法传递给它图象和MatOfRect对象。这个过程之后，MatOfRect将有面部检测。
//        faceDetector.detectMultiScale(image, faceDetections);
//
//        logger.info(String.format("Detected %s faces", faceDetections.toArray().length));
////        我们遍历所有的脸部检测并用矩形标记图像。
//        for (Rect rect : faceDetections.toArray()) {
//            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0));
//        }
//
////       最后，将图像写入输出的 .png 文件里。
//        String filename = url.substring(url.lastIndexOf("/"), url.length());
//        System.out.println(String.format("Writing %s", Constants.PATH + Constants.DEST_IMAGE_PATH + filename));
//        Highgui.imwrite(Constants.PATH + Constants.DEST_IMAGE_PATH + filename, image);
//        renderString(response, Constants.SUCCESS);
//    }

    /**
     * opencv实现人眼识别
     *
     * @param imagePath
     * @param outFile
     * @throws Exception
     */
    public static void detectEye(String imagePath, String outFile) throws Exception {

        CascadeClassifier eyeDetector = new CascadeClassifier(DetectController.class.getResource("/haarcascade/haarcascade_eye_tree_eyeglasses.xml").getPath().substring(1));
//         CascadeClassifier faceDetector = new CascadeClassifier(DemoController.class.getResource("/haarcascade/lbpcascade_frontalface.xml").getPath().substring(1));

        //读取图片
        Mat image = Highgui.imread(imagePath);

        // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();
        eyeDetector.detectMultiScale(image, faceDetections);
        Rect[] rects = faceDetections.toArray();
        if (rects != null && rects.length >= 1) {

            Rect eyea = rects[0];
            Rect eyeb = rects[1];
            double dy = (eyeb.y - eyea.y);
            double dx = (eyeb.x - eyea.x);
            double len = Math.sqrt(dx * dx + dy * dy);
            System.out.println("dx is " + dx);
            System.out.println("dy is " + dy);
            System.out.println("len is " + len);
            double angle = Math.atan2(Math.abs(dy), Math.abs(dx)) * 180.0 / Math.PI;
            System.out.println("angle is " + angle);

            for (Rect rec : faceDetections.toArray()) {
                // Core.rectangle(image, new Point(rec.x, rec.y), new Point(rec.x
                //          +rec.width, rec.y +rec.height), new Scalar(0, 255, 0));
                //在瞳孔处做标记
                Point center = new Point();
                center.x = Math.ceil(rec.x + rec.width * 0.5);
                center.y = Math.ceil(rec.y + rec.height * 0.5);
                Core.circle(image, center, 2, new Scalar(0, 255, 0), 3, 8, 0);
            }
        }

        System.out.println(String.format("Detected %s eyes", faceDetections.toArray().length));
        Highgui.imwrite(outFile, image);
        System.out.println(String.format("人眼识别成功，人眼图片文件为： %s", outFile));
    }

    /**
     * opencv实现人脸识别
     *
     * @param img
     * @throws Exception
     */
    public static Mat detectFace(Mat img) {

        System.out.println("Running DetectFace ... ");
        // 从配置文件lbpcascade_frontalface.xml中创建一个人脸识别器，该文件位于opencv安装目录中
        CascadeClassifier eyeDetector = new CascadeClassifier(DetectController.class.getResource("/haarcascade/haarcascade_eye_tree_eyeglasses.xml").getPath().substring(1));
        CascadeClassifier faceDetector = new CascadeClassifier(DetectController.class.getResource("/haarcascade/lbpcascade_frontalface.xml").getPath().substring(1));

        // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(img, faceDetections);

        //System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        Rect[] rects = faceDetections.toArray();
        Random r = new Random();
        if (rects != null && rects.length >= 1) {
            for (Rect rect : rects) {
                //画矩形
                Core.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 255), 2);
                //识别人眼
                Mat faceROI = new Mat(img, rect);
                MatOfRect eyesDetections = new MatOfRect();
                eyeDetector.detectMultiScale(faceROI, eyesDetections);
                System.out.println("Running DetectEye ... " + eyesDetections);

                if (eyesDetections.toArray().length > 1) {
                    save(img, rect, "D:\\develop\\tess4J\\img\\" + r.nextInt(2000) + ".jpg");
                }
            }
        }
        return img;
    }

    /**
     * opencv将人脸进行截图并保存
     *
     * @param img
     */
    private static void save(Mat img, Rect rect, String outFile) {
        Mat sub = img.submat(rect);
        Mat mat = new Mat();
        Size size = new Size(64, 64);
        Imgproc.resize(sub, mat, size);
        Highgui.imwrite(outFile, mat);
    }

}
