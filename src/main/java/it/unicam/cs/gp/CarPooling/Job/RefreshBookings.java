package it.unicam.cs.gp.CarPooling.Job;

import it.unicam.cs.gp.CarPooling.Service.PrenotazioneService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RefreshBookings implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Esecuzione del job RefreshBookings...");
        PrenotazioneService prenotazioneService = (PrenotazioneService) context.getJobDetail().getJobDataMap().get("prenotazioneService");
        prenotazioneService.deleteAllPrenotazioni();
        System.out.println("Job RefreshBookings eseguito con successo.");
    }
}
