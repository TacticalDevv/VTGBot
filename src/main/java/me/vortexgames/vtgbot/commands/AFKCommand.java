package me.vortexgames.vtgbot.commands;

import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AFKCommand implements ICommand {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        Map<Member, String> nick = new HashMap<>();

        if (!event.getMember().getNickname().contains("AFK")) {
            nick.put(event.getMember(), event.getMember().getNickname());
            event.getGuild().getController().setNickname(event.getMember(), "AFK | " + event.getMember().getNickname()).queue();
            event.getChannel().sendMessage("U bent nu afk!").queue();
            event.getChannel().sendMessage(nick.get(event.getMember())).queue();
        }


        if (event.getMember().getNickname().contains("AFK")) {
            event.getGuild().getController().setNickname(event.getMember(), nick.get(event.getMember())).queue();
            event.getChannel().sendMessage("U bent nu niet meer afk!").queue();
            nick.remove(event.getMember());
        }
    }

    @Override
    public String getHelp() {
        return "Go to afk";
    }

    @Override
    public String getInvoke() {
        return "afk";
    }
}
