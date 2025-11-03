package net.engineeringdigest.journalApp.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    @Autowired
    public AIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String improveContent(String content) {
        String promptText = """
        Improve and polish this journal entry while keeping its tone and emotion.
        Return only the improved version, without any explanation or commentary.
        Dont give a very long answer keep it small.
        
        
        """ + content;

        return chatClient
                .prompt()
                .user(promptText)
                .call()
                .content();
    }

}
