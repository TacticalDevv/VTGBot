package me.vortexgames.vtgbot.listeners;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class AddReaction extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Member selfMember = event.getGuild().getSelfMember();

        if (message.getChannel().getId().equals("593083893372944385")) {
            event.getMessage().addReaction("\uD83D\uDC4D").queue();
            event.getMessage().addReaction("\uD83D\uDC4E").queue();
        }
    }
}
