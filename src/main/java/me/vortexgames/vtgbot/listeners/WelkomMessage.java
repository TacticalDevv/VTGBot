package me.vortexgames.vtgbot.listeners;

import me.vortexgames.vtgbot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class WelkomMessage extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        User user = event.getUser();
        Guild guild = event.getGuild();

        TextChannel textChannel = Main.getJda().getTextChannelById("593074096917577736");

        EmbedBuilder welkomEmbed = new EmbedBuilder();

        welkomEmbed.setAuthor("Er is een gebruiker gejoined");
        welkomEmbed.setColor(Color.BLUE);
        welkomEmbed.setDescription("Welkom " + user.getName() + " op de VortexGames discord! Check even onze channels voor informatie!\nEn neem een kijkje op de server: **play.vortexgames.nl**");
        welkomEmbed.setFooter("Member joined", guild.getIconUrl());

        textChannel.sendTyping().queue();
        textChannel.sendMessage(welkomEmbed.build()).queue();
    }
}
