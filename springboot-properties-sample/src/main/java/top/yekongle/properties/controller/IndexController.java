package top.yekongle.properties.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yekongle.properties.config.PersonConfig;
import top.yekongle.properties.config.SongConfig;

@RestController
public class IndexController {
    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;

    @Autowired
    private SongConfig songConfig;

    @Autowired
    private PersonConfig personConfig;

    // 返回用 @Value 注入的配置内容
    @RequestMapping("/")
    public String index() {
        return String.format("book author[%s], book name[%s]", bookAuthor, bookName);
    }

    // 返回用 @ConfigurationProperties 注入的配置内容
    @RequestMapping("/song")
    public String song() {
        return String.format("song author[%s], song name[%s]", songConfig.getAuthor(), songConfig.getName());
    }

    // 返回用 @PropertySource与ConfigurationProperties组合使用注入的配置内容
    @RequestMapping("/person")
    public String person() {
        return String.format("person name[%s], person age[%d]", personConfig.getName(), personConfig.getAge());
    }
}
