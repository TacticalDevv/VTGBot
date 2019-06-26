package me.vortexgames.vtgbot.commands;

import me.vortexgames.vtgbot.Main;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.ChannelManager;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;

public class TicketCloseCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("!close")) {
            Member member = event.getMember();

            Channel chann = event.getTextChannel();

            ChannelManager channelManager = new ChannelManager(chann);

//            if(channelManager.get)

            channel.sendMessage("U hebt een **ticket** aangemaakt.").queue();
        }
    }
}
