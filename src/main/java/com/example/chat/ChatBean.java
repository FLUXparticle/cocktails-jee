package com.example.chat;

import io.reactivex.rxjava3.disposables.*;
import jakarta.annotation.*;
import jakarta.faces.push.*;
import jakarta.faces.view.*;
import jakarta.inject.*;

import java.io.*;

@Named
@ViewScoped
public class ChatBean implements Serializable {

    @Inject
    private ChatService chatService;

    @Inject
    @Push(channel = "chatChannel")
    private PushContext pushContext;

    private String message = "";

    private String chatLog = "";

    private Disposable subscription;

    @PostConstruct
    public void init() {
        // Debug: Initialisierung der Subscription überprüfen
        System.out.println("Subscription gestartet...");

        subscription = chatService.getChatMessages()
                .subscribe(msg -> {
                    System.out.println("Neue Nachricht: " + msg);
                    chatLog += msg + "\n"; // Optional: Server-seitiges Chat-Log aktualisieren
                    pushContext.send(msg); // Nur die neue Nachricht senden
                });
    }

    // Freigeben der Ressourcen, wenn der Bean-Zyklus beendet ist
    @PreDestroy
    public void dispose() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    public void sendMessage() {
        System.out.println("Sending message: " + message);
        if (message != null && !message.trim().isEmpty()) {
            chatService.addMessage(message);
            message = ""; // Eingabefeld nach dem Senden leeren
        }
    }

    // Getter und Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatLog() {
        return chatLog;
    }

}