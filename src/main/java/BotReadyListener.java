import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        JDA jda = event.getJDA();
        CommandRegistrar.registerCommands(jda);  // 명령어 등록 호출
        System.out.println("봇이 준비되었습니다. 명령어 등록 완료!");
    }
}