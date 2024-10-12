package com.example.chat;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.core.Observable;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChatService {

    private final PublishSubject<String> chatMessages = PublishSubject.create();

    public ChatService() {
        chatMessages.subscribe(message -> {
            ChatEndpoint.broadcast(message);
        });
    }

    public void addMessage(String message) {
        chatMessages.onNext(message);
    }

    public Observable<String> getChatMessages() {
        return chatMessages;
    }

}