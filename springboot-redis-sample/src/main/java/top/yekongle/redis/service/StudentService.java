package top.yekongle.redis.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import top.yekongle.redis.entity.Student;

// @CacheConfig 用来描述该类中所有方法使用的缓存名
@CacheConfig(cacheNames = "student")
public interface StudentService {
    // @CachePut 一般加在更新方法上，可以将方法的返回值自动更新到已经存在的 key 上
    @CachePut(key = "#p0.sno")
    Student update(Student student);

    // @CacheEvict 一般加在删除方法上，数据库数据删除后，相关缓存也要删除
    // 可以配置按照某种条件删除（condition 属性）或者或者配置清除所有缓存（allEntries 属性）
    @CacheEvict(key = "#p0", allEntries = true)
    void deleteStudentBySno(String sno);

    // @Cacheable 一般加在缓存方法上，表示将方法的返回结果缓存起来
    // 默认 key 是 方法的参数，缓存的 value 就是方法的返回值
    @Cacheable(key = "#p0")
    Student queryStudentBySno(String sno);
}