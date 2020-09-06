package top.yekongle.qrcode.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class QrCodeConfig {
	@Value("${qrcode.info.charset}")
	private String configCharset;
	@Value("${qrcode.info.qr-width}")
	private Integer configWidth;
	@Value("${qrcode.info.qr-height}")
	private Integer configHeight;
	@Value("${qrcode.info.logo-width}")
	private Integer configLogoWidth;
	@Value("${qrcode.info.logo-height}")
	private Integer configLogoHeight;
	@Value("${qrcode.info.qr-pic-type}")
	private String configPicType;

	// 编码
	public static String charset;

	// 生成二维码的宽度
	public static Integer width;

	// 生成二维码的高度
	public static Integer height;

	// logo的宽度
	public static Integer logoWidth;

	// logo的高度
	public static Integer logoHeight;

	// 生成二维码图片的格式 png, jpg
	public static String picType;

	@PostConstruct
	public void setCharset() {
		charset = this.configCharset;
	}

	@PostConstruct
	public void setWidth() {
		width = this.configWidth;
	}

	@PostConstruct
	public void setHeight() {
		height = this.configHeight;
	}

	@PostConstruct
	public void setLogoWidth() {
		logoWidth = this.configLogoWidth;
	}

	@PostConstruct
	public void setLogoHeight() {
		logoHeight = this.configLogoHeight;
	}

	@PostConstruct
	public void setPicType() {
		picType = this.configPicType;
	}

}
