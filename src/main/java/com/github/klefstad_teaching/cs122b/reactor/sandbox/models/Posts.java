package com.github.klefstad_teaching.cs122b.reactor.sandbox.models;

public class Posts
{
    private String  title;
    private String  body;
    private Integer userId;

    public String getTitle()
    {
        return title;
    }

    public Posts setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public String getBody()
    {
        return body;
    }

    public Posts setBody(String body)
    {
        this.body = body;
        return this;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public Posts setUserId(Integer userId)
    {
        this.userId = userId;
        return this;
    }

    @Override public String toString()
    {
        return "Posts{" +
               "title='" + title + '\'' +
               ", body='" + body + '\'' +
               ", userId=" + userId +
               '}';
    }
}
