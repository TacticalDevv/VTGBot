package me.vortexgames.vtgbot.listeners;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class LeadMentionBlock extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Member selfMember = event.getGuild().getSelfMember();

        List<Member> mentionedMembers = event.getMessage().getMentionedMembers();
        Member target = mentionedMembers.get(0);

        if (target == null) return;

        if (event.getMessage().getContentRaw().equals(target.getUser().getId().equalsIgnoreCase("590599447822467115"))) {
            event.getChannel().sendMessage("Test").queue();
        } else {
            return;
        }

        if (mentionedMembers.isEmpty()) return;
    }
}
