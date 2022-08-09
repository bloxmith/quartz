package com.bloxmith.siwgb.quartz;

import com.bloxmith.siwgb.LineNotifyUtils;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author p@bloxmith.com
 */
@Component
public class TournamentJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(TournamentJob.class);

	private ZonedDateTime toTaiwan(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());

		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(
			instant,
			ZoneId.of("Asia/Taipei")
		);

		return zonedDateTime.withHour(7);
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LineNotifyUtils.notify(
			String.format(
				"%n觸發了 %s 任務",
				toTaiwan(new Date()).
					format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
			)
		);
	}
}
