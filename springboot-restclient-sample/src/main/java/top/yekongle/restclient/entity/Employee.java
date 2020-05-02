package top.yekongle.restclient.entity;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;

@Data
public class Employee {
	private Long id;
	private String name;
	private String role;
	private String uri;

	// 构建资源定位符
	public String getUri() {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(this.getId()).toUri().toString();
	}
}
