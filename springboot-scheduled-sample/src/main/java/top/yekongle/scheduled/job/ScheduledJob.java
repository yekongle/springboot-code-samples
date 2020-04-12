package top.yekongle.scheduled.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledJob {
	// 时间格式化
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 每5秒执行一次，无论上次任务执行结束与否，参数单位是毫秒
	@Scheduled(fixedRate = 5000)
	public void fixedRate() {
		log.info("fixedRate>>>：{}", format.format(new Date()));
	}

	// 当上次任务执行结束后，间隔5秒再执行下次任务，参数单位是毫秒
	@Scheduled(fixedDelay = 5000)
	public void fixedDelay() {
		log.info("fixedDelay>>>：{}", format.format(new Date()));
	}

	// initialDelay 表示首次任务启动的延迟时间，参数单位是毫秒
	@Scheduled(initialDelay = 5000, fixedDelay = 5000)
	public void initialDelay() {
		log.info("initialDelay>>>：{}", format.format(new Date()));
	}

	// 使用cron表达式，可以按照cron的逻辑执行代码, 实行更精细的任务调度
	// 每隔10秒执行一次
	@Scheduled(cron = "0/10 * * * * *")
	public void startJob() {
		log.info("Cron job：{}", format.format(new Date()));
	}

}
