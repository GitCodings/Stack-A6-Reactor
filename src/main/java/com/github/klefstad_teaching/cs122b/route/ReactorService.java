package com.github.klefstad_teaching.cs122b.route;

import com.github.klefstad_teaching.cs122b.route.sandbox.MonoSandbox;
import com.github.klefstad_teaching.cs122b.route.sandbox.StreamsSandbox;
import com.github.klefstad_teaching.cs122b.route.sandbox.WebClientSandbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ReactorService
{
    @EventListener(ApplicationReadyEvent.class)
    public void writeYourSandboxCodeHere()
    {
        MonoSandbox.run();
        StreamsSandbox.run();
        WebClientSandbox.run();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ReactorService.class, args);
    }
}
