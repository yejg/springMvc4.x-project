package org.light4j.springMvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String upload(MultipartFile[] file) {// ①
		
		for (MultipartFile multipartFile : file) {
			// 文件的原始名称
			String originalFilename = multipartFile.getOriginalFilename();
			System.out.println(originalFilename);
		}
		return "ok";

//		try {
//			FileUtils.writeByteArrayToFile(new File("f:/upload/" + file.getOriginalFilename()),file.getBytes()); // ②
//			return "ok";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "wrong";
//		}
	}
}
