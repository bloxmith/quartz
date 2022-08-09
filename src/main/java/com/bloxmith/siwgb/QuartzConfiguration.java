package com.bloxmith.siwgb;

import com.bloxmith.siwgb.quartz.TournamentJob;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.TimeZone;
import org.quartz.CronExpression;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author p@bloxmith.com
 */
@Configuration
public class QuartzConfiguration {

	@Bean
	public JobDetail tournamentJobDetail() {
		return JobBuilder.
			newJob(TournamentJob.class).
			storeDurably().
			withIdentity("tournament").
			build();
	}

	@Bean
	public Trigger tournamentTrigger() throws ParseException {
		CronExpression cronExpression = new CronExpression("0 * * ? * *");
		cronExpression.setTimeZone(
			TimeZone.getTimeZone(
				ZoneId.of("Asia/Taipei")
			)
		);

		return newTrigger().
			withIdentity("tournament").
			withSchedule(
				cronSchedule(cronExpression)
			).
			forJob(
				tournamentJobDetail()
			).
			build();
	}
}
