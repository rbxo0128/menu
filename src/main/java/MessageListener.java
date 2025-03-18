import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        System.out.println("message = " + message);
        // "!안녕" 입력 시 자동 응답
        if (message.equalsIgnoreCase("!안녕")) {
            event.getChannel().sendMessage("안녕하세요!").queue();
        }
    }
}
