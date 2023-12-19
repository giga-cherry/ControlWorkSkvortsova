package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, List<Long> ticketId) {
        this.username = username;
        this.password = password;
        this.ticketId = ticketId;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getMangaId() {
        return ticketId;
    }

    public void setMangaId(List<Long> mangaId) {
        this.ticketId = mangaId;
    }

    private String username;

    private String password;

    private String role;

    private List<Long> ticketId = new ArrayList<>();

    public void add (Long id) {
        ticketId.add(id);
    }

    public void removeManga(Long id) {
        ticketId.remove(id);
    }

}

