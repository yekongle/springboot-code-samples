package top.yekongle.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.extern.slf4j.Slf4j;
import top.yekongle.fileupload.entity.FileInfo;

/**
 * @Description: 文件上传
 * @Author: Yekongle
 */

@Controller
@Slf4j
public class UploadController {

	@Value("${spring.servlet.multipart.location}")
	private String path;

	private static final int DEFAULT_MAX_FILE_SIZE = 5 * 1000 * 1000;
	private static final int DEFAULT_REQUEST_MAX_SIZE = 50 * 1000 * 1000;

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();

	@GetMapping("/singleFileUpload")
	public String getSingleFileUploadPage(Model model) {
		model.addAttribute("files", fileInfoList);
		return "single_file_upload";
	}

	@GetMapping("/multiFileUpload")
	public String getMultiFileUploadPage(Model model) {
		model.addAttribute("files", fileInfoList);
		return "multi_file_upload";
	}

	@PostMapping("/singleFileUpload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {
		File filePath = new File(path);
		log.info("文件保存的路径为： {}", filePath.getAbsolutePath());
		if (!filePath.exists() && !filePath.isDirectory()) {
			// 该文件目录不存在则创建
			filePath.mkdir();
		}
		// 判断文件是否为空
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "文件为空!");
			return "redirect:/singleFileUpload";
		}
		// 判断文件是否为空文件
		if (file.getSize() <= 0) {
			redirectAttributes.addFlashAttribute("message", "文件大小为空，上传失败!");
			return "redirect:/singleFileUpload";
		}
		// 判断文件大小不能大于5MB
		if (file.getSize() > DEFAULT_MAX_FILE_SIZE) {
			redirectAttributes.addFlashAttribute("message", "上传的文件不能大于5M!");
			return "redirect:/singleFileUpload";
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();

		log.info("上传的文件名为：{}", fileName);
		log.info("上传的文件大小为：{}", file.getSize());
		log.info("上传的文件类型为：{}", file.getContentType());
		// 在指定目录下创建该文件
		File newfile = new File(path, fileName);
		if (newfile.exists()) {
			redirectAttributes.addFlashAttribute("message", "该文件已经存在!");
			return "redirect:/singleFileUpload";
		} else {
			FileInfo fileInfo = new FileInfo(fileName, file.getContentType(), file.getSize(), sf.format(new Date()));
			fileInfoList.add(fileInfo);
		}

		// 将文件保存到该目录
		file.transferTo(newfile);
		return "redirect:/singleFileUpload";
	}

	@PostMapping("/multiFileUpload")
	public String multiFileUpload(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes)
			throws IOException {
		if (null == files) {
			redirectAttributes.addFlashAttribute("message", "参数为空!");
			return "redirect:/multiFileUpload";
		}
		File filePath = new File(path);
		log.info("文件保存的路径为： {}", filePath.getAbsolutePath());
		if (!filePath.exists() && !filePath.isDirectory()) {
			// 该文件目录不存在则创建
			filePath.mkdir();
		}
		for (MultipartFile uploadFile : files) {
			if (uploadFile.isEmpty()) {
				redirectAttributes.addFlashAttribute("message", "文件为空!");
				return "redirect:/multiFileUpload";
			}
			// 判断文件是否为空文件
			if (uploadFile.getSize() <= 0) {
				redirectAttributes.addFlashAttribute("message", "文件大小为空，上传失败!");
				return "redirect:/multiFileUpload";
			}
			// 判断文件大小不能大于50MB
			if (uploadFile.getSize() > DEFAULT_REQUEST_MAX_SIZE) {
				redirectAttributes.addFlashAttribute("message", "上传的文件不能大于5M!");
				return "redirect:/multiFileUpload";
			}
			// 获取文件名
			String fileName = uploadFile.getOriginalFilename();

			log.info("上传的文件名为：{}", fileName);
			log.info("上传的文件大小为：{}", uploadFile.getSize());
			log.info("上传的文件类型为：{}", uploadFile.getContentType());
			// 在指定目录下创建该文件
			File newfile = new File(path, fileName);
			if (!newfile.exists()) {
				FileInfo fileInfo = new FileInfo(fileName, uploadFile.getContentType(), uploadFile.getSize(),
						sf.format(new Date()));
				fileInfoList.add(fileInfo);
			}

			// 将文件保存到该目录
			uploadFile.transferTo(newfile);
		}

		return "redirect:/multiFileUpload";
	}

}
