package it.unicam.cs.gp.CarPooling.Job;

import it.unicam.cs.gp.CarPooling.Service.PrenotazioneService;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzSchedulerConfig {
    @Bean
    public JobDetail prenotazioniSvuotamentoJobDetail(PrenotazioneService prenotazioneService) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("prenotazioneService", prenotazioneService);

        return JobBuilder.newJob(RefreshBookings.class)
                .withIdentity("refreshBookings")
                .storeDurably()
                .usingJobData(jobDataMap)
                .build();
    }

    @Bean
    public Trigger prenotazioniSvuotamentoTrigger(JobDetail prenotazioniSvuotamentoJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(prenotazioniSvuotamentoJobDetail)
                .withIdentity("refreshBookingsTrigger")
                .withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(
                        DateBuilder.SATURDAY, 0, 0)) // Esegui il job ogni domenica alle 16:28
                .build();
    }
}
