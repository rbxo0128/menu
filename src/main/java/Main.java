import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String token = dotenv.get("TOKEN"); // 디스코드 봇 토큰 입력

        JDABuilder.createDefault(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT) // MESSAGE_CONTENT 인텐트 활성화
                .setActivity(Activity.playing("자바 디스코드 봇!"))
                .addEventListeners(new BotReadyListener() ,new MessageListener(), new SlashCommandListener())
                .build();
    }
}
