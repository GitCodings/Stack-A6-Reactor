package com.gitcodings.stack.reactor.sandbox.models;

public class Todos
{
    Integer userId;
    Integer id;
    String  title;
    Boolean completed;

    public Integer getUserId()
    {
        return userId;
    }

    public Todos setUserId(Integer userId)
    {
        this.userId = userId;
        return this;
    }

    public Integer getId()
    {
        return id;
    }

    public Todos setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public Todos setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public Boolean getCompleted()
    {
        return completed;
    }

    public Todos setCompleted(Boolean completed)
    {
        this.completed = completed;
        return this;
    }

    @Override public String toString()
    {
        return "Todos{" +
               "userId=" + userId +
               ", id=" + id +
               ", title='" + title + '\'' +
               ", completed=" + completed +
               '}';
    }
}
