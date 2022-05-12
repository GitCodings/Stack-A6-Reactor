package com.github.klefstad_teaching.cs122b.route.sandbox;

import com.github.klefstad_teaching.cs122b.route.sandbox.models.Posts;
import com.github.klefstad_teaching.cs122b.route.sandbox.models.Todos;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientSandbox
{
    private static final WebClient WEB_CLIENT = WebClient.builder().build();

    public static void run()
    {
        getExample();
        postExample();
        chainingMonosExample();
    }

    private static void getExample()
    {
        Mono<Todos> getMono =
            WEB_CLIENT.get()
                      .uri("https://jsonplaceholder.typicode.com/todos/1")
                      .retrieve()
                      .bodyToMono(Todos.class);

        // At this point the request has NOT been made
        // Since this is a mono we have to call subscribe or block
        // for it to actually start

        Todos todos = getMono.block();

        System.out.println(todos);
    }

    private static void postExample()
    {
        Posts postsBody = new Posts()
                          .setBody("Body")
                          .setTitle("Title")
                          .setUserId(1);

        Mono<Posts> postMono =
            WEB_CLIENT.post()
                      .uri("https://jsonplaceholder.typicode.com/posts")
                      .bodyValue(postsBody)
                      .retrieve()
                      .bodyToMono(Posts.class);

        Posts posts = postMono.block();

        System.out.println(posts);
    }

    private static void chainingMonosExample()
    {
        Posts postsBody = new Posts()
                              .setBody("Body")
                              .setTitle("Title")
                              .setUserId(10);

        Mono<Todos> todosMono =
            WEB_CLIENT.post()
                      .uri("https://jsonplaceholder.typicode.com/posts")
                      .bodyValue(postsBody)
                      .retrieve()
                      .bodyToMono(Posts.class)
                      .flatMap(posts -> getTodos(posts.getUserId()));

        Todos posts = todosMono.block();

        System.out.println(posts);
    }

    private static Mono<Todos> getTodos(int postId)
    {
        return Mono.just(postId)
                   .flatMap(id ->
                                WEB_CLIENT.get()
                                          .uri("https://jsonplaceholder.typicode.com/todos/" + id)
                                          .retrieve()
                                          .bodyToMono(Todos.class)
                   );
    }
}
