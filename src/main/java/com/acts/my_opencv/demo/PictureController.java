package com.acts.my_opencv.demo;

import com.acts.my_opencv.common.web.BaseController;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * OpenCV人脸识别demo
 * 创建者 Songer
 * 创建时间	2018年3月9日
 */
@Controller
@RequestMapping(value = "picture")
public class PictureController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(PictureController.class);

	@GetMapping(value = "detectFace")
	public void detectFace() {}

	public static void main(String[] args) throws TesseractException {
		//加载待读取图片
		File imageFile = new File("D:\\develop\\tess4J\\img\\font1.png");
		//创建tess对象
		ITesseract instance = new Tesseract();
		//设置训练文件目录
		instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\resources\\tessdata");
		//设置训练语言
		instance.setLanguage("chi_sim");
		//执行转换
		String result = instance.doOCR(imageFile);

		System.out.println(result);
	}
}
