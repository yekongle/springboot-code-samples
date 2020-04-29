package top.yekongle.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 文件下载
 * @Author: Yekongle
 */

@Controller
@Slf4j
public class DownloadController {

	private static final String FILE_STORE_PATH = "F:/upload";

	@GetMapping("/downloadFile")
	public String getUploadPage() {
		return "file_download";
	}

	/**
	 * 文件下载
	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@PostMapping("/downloadFile")
	@ResponseBody
	public String downLoadFile(HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		log.info("要下载的文件：{}", request.getParameter("fileName"));
		String fileName = request.getParameter("fileName");
		log.info("File name:{}", fileName);

		File file = new File(FILE_STORE_PATH, fileName);
		log.info("File path:{}", file.getAbsolutePath());

		if (file.exists()) {
			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes(), "utf-8"));
			// 文件内容长度
			response.setHeader("Content-Length", "" + file.length());
			// 读取文件
			BufferedInputStream bi = null;
			try {
				byte[] buffer = new byte[1024];
				bi = new BufferedInputStream(new FileInputStream(file));
				ServletOutputStream outputStream = response.getOutputStream();
				int i = -1;
				while (-1 != (i = bi.read(buffer))) {
					outputStream.write(buffer, 0, i);
				}
				return "下载成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "下载失败";
			} finally {
				if (bi != null) {
					try {
						bi.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return "文件不存在";
	}

}
