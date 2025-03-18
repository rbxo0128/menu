import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class CommandRegistrar {
    public static void registerCommands(JDA jda) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String guildId = dotenv.get("GUILD_ID");
        jda.getGuildById(guildId).updateCommands().addCommands(
                Commands.slash("메뉴추천", "오늘 먹을 메뉴를 추천해줍니다.")
                        .addOption(OptionType.STRING, "meal", "아침, 점심, 저녁", true)
                        .addOption(OptionType.STRING, "category", "어떤 종류의 음식인가요? (한식, 양식, 중식 등)", false)
                        .addOption(OptionType.STRING, "prompt", "추천 메뉴에 대한 설명을 입력하세요.", false)
        ).queue();
    }
}