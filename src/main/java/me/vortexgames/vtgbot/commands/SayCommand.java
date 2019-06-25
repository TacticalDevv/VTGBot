package me.vortexgames.vtgbot.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SayCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("!say")) {
            Member member = event.getMember();

            if (!member.hasPermission(Permission.MESSAGE_MANAGE)) {
                channel.sendMessage("U hebt geen permissions om dit commando te kunnen gebruiken.").queue();
                return;
            }

            if (args.length < 2) {
                channel.sendMessage("**_Error:_** U hebt iets fouts gedaan, u moet een title mee geven.").queue();
                return;
            }

            if (args.length < 3) {
                channel.sendMessage("**_Error:_** U hebt iets fouts gedaan, u moet een message mee geven.").queue();
                return;
            }

            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setAuthor(args[1]);
            embedBuilder.setColor(Color.CYAN);
            embedBuilder.setDescription(event.getMessage().getContentRaw().substring(4));

            channel.sendMessage(embedBuilder.build()).queue();
            message.delete().queue();
        }
    }
}
