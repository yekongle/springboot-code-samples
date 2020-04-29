package top.yekongle.fileupload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/** 
* @Author: Yekongle 
* @Date: 2020年4月28日
*/

@Data
@AllArgsConstructor
public class FileInfo {
	private String name;
	private String contentType;
	private long size;
    private String uploadDate;
}
