package kyle.phoenix.admin;

import java.util.concurrent.TimeUnit;

import kyle.phoenix.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reload extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.PREFIX + "reload")) {

            if (event.getMember().isOwner()) {
                event.getChannel().sendTyping().complete();
                event.getMessage().delete().queue();

                if (args.length < 2) {
                    // Shuts down the bot (start.sh scrips restarts it)
                    System.exit(0);
                }

            } else {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(0xf97272);
                error.setTitle(":red_circle: Error");
                error.setDescription("You need to be owner to use this.");
                event.getChannel().sendMessage(error.build()).queue((message) -> {
                    message.delete().queueAfter(5, TimeUnit.SECONDS);
                });
            }
        }
    }
}