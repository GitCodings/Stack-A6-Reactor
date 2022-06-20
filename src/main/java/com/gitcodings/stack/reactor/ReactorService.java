package com.gitcodings.stack.reactor;

import com.gitcodings.stack.reactor.sandbox.MonoSandbox;
import com.gitcodings.stack.reactor.sandbox.StreamsSandbox;
import com.gitcodings.stack.reactor.sandbox.WebClientSandbox;
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
